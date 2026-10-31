import sqlite3

conexao = sqlite3.connect("users.db")
cursor = conexao.cursor()

cursor.execute("""
    CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        email TEXT NOT NULL,
        password TEXT NOT NULL
    )
""")

cursor.execute("""
    INSERT INTO users (email, password)
    VALUES (?, ?)
""", ("teste@email.com", "123456"))

conexao.commit()
conexao.close()

print("Base de dados criada com sucesso!")