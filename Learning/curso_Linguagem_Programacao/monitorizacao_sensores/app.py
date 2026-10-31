
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from datetime import datetime
import random
import sqlite3

app = FastAPI(title="Sistema de Monitorização Industrial")

DB_NAME = "sensores.db"

# Limites de referência dos sensores
LIMITES = {
    "Temperatura": (0, 80),
    "Vibracao": (0, 10),
    "Pressao": (2, 8)
}


# Modelo dos dados recebidos
class SensorData(BaseModel):
    sensor: str
    valor: float
    timestamp: str | None = None


# Criar a base de dados
def criar_base_dados():
    conn = sqlite3.connect(DB_NAME)

    conn.execute("""
        CREATE TABLE IF NOT EXISTS leituras (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            sensor TEXT NOT NULL,
            valor REAL NOT NULL,
            timestamp TEXT NOT NULL,
            alerta TEXT
        )
    """)

    conn.commit()
    conn.close()


criar_base_dados()


# Verificar se uma leitura está fora dos limites
def verificar_alerta(sensor, valor):

    minimo, maximo = LIMITES[sensor]

    if valor < minimo or valor > maximo:
        return f"Valor anormal de {sensor}: {valor}"

    return None


# Guardar uma leitura na base de dados
def guardar_leitura(leitura):

    alerta = verificar_alerta(leitura.sensor, leitura.valor)

    timestamp = leitura.timestamp or datetime.now().isoformat(
        timespec="seconds"
    )

    conn = sqlite3.connect(DB_NAME)

    conn.execute("""
        INSERT INTO leituras (sensor, valor, timestamp, alerta)
        VALUES (?, ?, ?, ?)
    """, (
        leitura.sensor,
        leitura.valor,
        timestamp,
        alerta
    ))

    conn.commit()
    conn.close()

    return alerta


# Receber dados enviados por um sensor
@app.post("/leituras")
def receber_leitura(leitura: SensorData):

    if leitura.sensor not in LIMITES:
        raise HTTPException(
            status_code=400,
            detail="Sensor desconhecido."
        )

    alerta = guardar_leitura(leitura)

    return {
        "mensagem": "Leitura recebida com sucesso!",
        "dados": leitura.model_dump(),
        "alerta": alerta
    }


# Simular leituras dos sensores
@app.get("/simular")
def simular_dados():

    sensores = ["Temperatura", "Vibracao", "Pressao"]

    sensor = random.choice(sensores)

    # Gerar valores aleatórios
    if sensor == "Temperatura":
        valor = round(random.uniform(20, 100), 2)

    elif sensor == "Vibracao":
        valor = round(random.uniform(0, 15), 2)

    else:
        valor = round(random.uniform(0, 12), 2)

    leitura = SensorData(
        sensor=sensor,
        valor=valor,
        timestamp=datetime.now().isoformat(timespec="seconds")
    )

    alerta = guardar_leitura(leitura)

    return {
        "dados": leitura.model_dump(),
        "alerta": alerta
    }


# Consultar o histórico de leituras
@app.get("/leituras")
def listar_leituras():

    conn = sqlite3.connect(DB_NAME)
    conn.row_factory = sqlite3.Row

    resultado = conn.execute("""
        SELECT * FROM leituras
        ORDER BY id DESC
    """).fetchall()

    conn.close()

    return [dict(linha) for linha in resultado]


# Consultar apenas os alertas
@app.get("/alertas")
def listar_alertas():

    conn = sqlite3.connect(DB_NAME)
    conn.row_factory = sqlite3.Row

    resultado = conn.execute("""
        SELECT * FROM leituras
        WHERE alerta IS NOT NULL
        ORDER BY id DESC
    """).fetchall()

    conn.close()

    return [dict(linha) for linha in resultado]


# Página inicial da API
@app.get("/")
def inicio():

    return {
        "mensagem": "Sistema de Monitorização de Sensores Industriais",
        "sensores": list(LIMITES.keys()),
        "documentacao": "/docs"
    }
