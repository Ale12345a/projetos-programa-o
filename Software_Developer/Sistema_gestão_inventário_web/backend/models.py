import sqlite3

DB_FILE = "database.db"

def get_db():
    conn = sqlite3.connect(DB_FILE)
    return conn

def init_db():
    db = get_db()
    db.execute("""CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    price REAL,
                    quantity INTEGER
                )""")
    db.commit()