
from flask import Flask, request, jsonify, render_template
import json
import re
import os
import requests

app = Flask(__name__)

FICHEIRO = os.path.join(app.root_path, "participantes.json")


def validar_dados(dados):
    """Valida os campos obrigatórios do participante."""

    if not isinstance(dados, dict):
        return "Os dados enviados são inválidos."

    nome = dados.get("nome", "")
    email = dados.get("email", "")
    codigo_postal = dados.get("codigo_postal", "")

    if not all(isinstance(valor, str) for valor in [nome, email, codigo_postal]):
        return "Os campos devem ser texto."

    nome = nome.strip()
    email = email.strip()
    codigo_postal = codigo_postal.strip()

    if not nome or len(nome) > 100:
        return "O nome é obrigatório e deve ter até 100 caracteres."

    if not re.fullmatch(r"[^@\s]+@[^@\s]+\.[^@\s]+", email):
        return "O email não é válido."

    if not re.fullmatch(r"\d{4}-\d{3}", codigo_postal):
        return "O código postal deve ter o formato 0000-000."

    return None


def consultar_localidade(codigo_postal):
    """Consulta a localidade através da API Zippopotam.us."""

    url = f"https://api.zippopotam.us/pt/{codigo_postal}"

    resposta = requests.get(url, timeout=5)
    resposta.raise_for_status()

    dados = resposta.json()
    localidades = dados.get("places", [])

    if not localidades:
        return None

    return localidades[0].get("place name")


def carregar_participantes():
    """Lê os participantes guardados no ficheiro JSON."""

    try:
        with open(FICHEIRO, "r", encoding="utf-8") as ficheiro:
            return json.load(ficheiro)
    except FileNotFoundError:
        return []
    except json.JSONDecodeError:
        return []


def guardar_participantes(participantes):
    """Guarda os participantes no ficheiro JSON."""

    with open(FICHEIRO, "w", encoding="utf-8") as ficheiro:
        json.dump(
            participantes,
            ficheiro,
            indent=4,
            ensure_ascii=False
        )


@app.route("/")
def home():
    return render_template("index.html")


@app.route("/registar", methods=["POST"])
def registar():
    dados = request.get_json(silent=True)

    erro = validar_dados(dados)

    if erro:
        return jsonify({"erro": erro}), 400

    nome = dados["nome"].strip()
    email = dados["email"].strip()
    codigo_postal = dados["codigo_postal"].strip()

    try:
        localidade = consultar_localidade(codigo_postal)

        if not localidade:
            return jsonify({"erro": "Código postal não encontrado."}), 404

    except requests.RequestException:
        return jsonify({"erro": "Não foi possível consultar a API."}), 502

    participante = {
        "nome": nome,
        "email": email,
        "codigo_postal": codigo_postal,
        "localidade": localidade
    }

    participantes = carregar_participantes()
    participantes.append(participante)
    guardar_participantes(participantes)

    return jsonify({
        "mensagem": "Registo efetuado com sucesso!",
        "participante": participante
    }), 201


@app.route("/participantes", methods=["GET"])
def listar_participantes():
    participantes = carregar_participantes()
    return jsonify(participantes), 200


if __name__ == "__main__":
    app.run(debug=True)
