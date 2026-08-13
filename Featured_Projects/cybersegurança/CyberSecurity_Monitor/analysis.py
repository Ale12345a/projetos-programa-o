import pandas as pd
import sqlite3
import os
from utils import detect_suspicious, plot_graphs, calculate_risk

os.makedirs("charts", exist_ok=True)
os.makedirs("reports", exist_ok=True)

# 1. Ler dados
df = pd.read_csv("logs/log.csv", parse_dates=["timestamp"])

print("=== RAW DATA ===")
print(df)

# 2. Limpeza
df = df.dropna()

# 3. Alertas
alerts = detect_suspicious(df)

print("\n=== ALERTS ===")
print(alerts)

# 4. Risk Score
risk_df = calculate_risk(df, alerts)

print("\n=== RISK SCORES ===")
print(risk_df.sort_values("risk_score", ascending=False))

# 5. Base de dados
conn = sqlite3.connect("database.db")
df.to_sql("logs", conn, if_exists="replace", index=False)
alerts.to_sql("alerts", conn, if_exists="replace", index=False)
risk_df.to_sql("risk_scores", conn, if_exists="replace", index=False)
conn.close()

# 6. Gráficos
plot_graphs(df, risk_df)

# 7. Relatório profissional
with open("reports/report.txt", "w") as f:
    f.write("=== CYBERSECURITY REPORT ===\n\n")

    f.write(f"Total logs: {len(df)}\n")
    f.write(f"Total alerts: {len(alerts)}\n\n")

    f.write("=== ALERTS ===\n")
    f.write(alerts.to_string(index=False))

    f.write("\n\n=== RISK SCORES ===\n")
    f.write(risk_df.sort_values("risk_score", ascending=False).to_string(index=False))

print("\n✅ Analysis complete!")