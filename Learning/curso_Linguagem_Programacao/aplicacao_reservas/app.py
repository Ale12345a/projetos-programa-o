from flask import Flask, request, jsonify, render_template
from pathlib import Path
import json
from threading import Lock
from datetime import date

app = Flask(__name__)
DATA_FILE = Path(__file__).with_name("reservas.json")
file_lock = Lock()


def carregar_reservas():
    if not DATA_FILE.exists():
        return []
    try:
        with DATA_FILE.open("r", encoding="utf-8") as ficheiro:
            return json.load(ficheiro)
    except (json.JSONDecodeError, OSError):
        return []


def guardar_reservas(reservas):
    with DATA_FILE.open("w", encoding="utf-8") as ficheiro:
        json.dump(reservas, ficheiro, ensure_ascii=False, indent=2)


@app.get("/")
def inicio():
    return render_template("index.html")


@app.get("/api/reservas")
def listar_reservas():
    with file_lock:
        reservas = carregar_reservas()
    return jsonify(reservas)


@app.post("/api/reservas")
def criar_reserva():
    dados = request.get_json(silent=True) or {}
    nome = str(dados.get("nome", "")).strip()
    data_reserva = str(dados.get("data", "")).strip()
    hora = str(dados.get("hora", "")).strip()

    if not nome or not data_reserva or not hora:
        return jsonify({"erro": "Preenche o nome, a data e a hora."}), 400

    try:
        data_validada = date.fromisoformat(data_reserva)
    except ValueError:
        return jsonify({"erro": "A data indicada não é válida."}), 400

    if data_validada < date.today():
        return jsonify({"erro": "A data da reserva não pode estar no passado."}), 400

    # Validação simples do formato HH:MM
    try:
        hora_h, hora_m = map(int, hora.split(":"))
        if not (0 <= hora_h <= 23 and 0 <= hora_m <= 59):
            raise ValueError
    except ValueError:
        return jsonify({"erro": "A hora indicada não é válida."}), 400

    with file_lock:
        reservas = carregar_reservas()
        reserva = {
            "id": max((r.get("id", 0) for r in reservas), default=0) + 1,
            "nome": nome,
            "data": data_reserva,
            "hora": hora
        }
        reservas.append(reserva)
        guardar_reservas(reservas)

    return jsonify({
        "mensagem": "Reserva efetuada com sucesso!",
        "reserva": reserva
    }), 201


if __name__ == "__main__":
    app.run(debug=True)
