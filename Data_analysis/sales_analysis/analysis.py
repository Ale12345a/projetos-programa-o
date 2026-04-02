import pandas as pd
import matplotlib.pyplot as plt
import sqlite3

# Ler dados
df = pd.read_csv("data.csv")

print("=== DATA ===")
print(df)

# Criar coluna revenue
df["revenue"] = df["price"] * df["quantity"]

# Total revenue
print("\n=== TOTAL REVENUE ===")
print(df["revenue"].sum())

# Vendas por produto
print("\n=== SALES PER PRODUCT ===")
sales = df.groupby("product")["quantity"].sum()
print(sales)

# -----------------------------
# BASE DE DADOS (SQLite)
# -----------------------------
conn = sqlite3.connect("database.db")

# Guardar dados na base de dados
df.to_sql("sales", conn, if_exists="replace", index=False)

print("\n=== DATA LOADED INTO DATABASE ===")

# -----------------------------
# GRÁFICO
# -----------------------------
sales.plot(kind="bar")

plt.title("Sales per Product")
plt.xlabel("Product")
plt.ylabel("Quantity Sold")

# Em vez de show (para evitar problemas no WSL)
plt.savefig("grafico.png")

print("\nGráfico guardado como grafico.png")