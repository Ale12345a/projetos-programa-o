from flask import Flask, request, jsonify
import sqlite3

app = Flask(__name__)

def obter_conexao():
    return sqlite3.connect("users.db")

@app.route("/login", methods=["GET"])
def login():
    email = request.args.get("email")
    password = request.args.get("password")

    if not email or not password:
        return jsonify({"erro": "Email e password são obrigatórios"}), 400

    conexao = obter_conexao()
    cursor = conexao.cursor()

    query = """
        SELECT id, email
        FROM users
        WHERE email = ? AND password = ?
    """

    cursor.execute(query, (email, password))
    utilizador = cursor.fetchone()

    conexao.close()

    if utilizador:
        return jsonify({"mensagem": "Login efetuado com sucesso"}), 200

    return jsonify({"erro": "Credenciais inválidas"}), 401


if __name__ == "__main__":
    app.run(debug=True)