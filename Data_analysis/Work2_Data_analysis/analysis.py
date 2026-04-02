import pandas as pd
import sqlite3
import matplotlib.pyplot as plt
import seaborn as sns
import os

# Criar pasta para gráficos
os.makedirs("charts", exist_ok=True)

# 1️⃣ Ler os dados brutos
df = pd.read_csv("raw_data.csv")
print("=== RAW DATA ===")
print(df)

# 2️⃣ Limpeza de dados
df = df.dropna()                       # Remove linhas com valores faltantes
df = df[df["quantity"] > 0]            # Remove quantidades negativas
df["date"] = pd.to_datetime(df["date"], errors="coerce")
df = df.dropna()                        # Remove datas inválidas
df["revenue"] = df["price"] * df["quantity"]

df.to_csv("clean_data.csv", index=False)
print("\n=== CLEAN DATA ===")
print(df)

# 3️⃣ Guardar na base de dados SQLite
conn = sqlite3.connect("database.db")
df.to_sql("sales", conn, if_exists="replace", index=False)
print("\n=== DATA STORED IN DATABASE ===")

# 4️⃣ Insights
print("\n=== INSIGHTS ===")
total_revenue = df["revenue"].sum()
top_product = df.groupby("product")["revenue"].sum().idxmax()
top_category = df.groupby("category")["revenue"].sum().idxmax()
avg_price_category = df.groupby("category")["price"].mean()
monthly_revenue = df.groupby(df["date"].dt.to_period("M"))["revenue"].sum()
quarterly_revenue = df.groupby(df["date"].dt.to_period("Q"))["revenue"].sum()

print(f"Total Revenue: {total_revenue}")
print(f"Top Product: {top_product}")
print(f"Top Category: {top_category}")
print("\nAverage Price per Category:")
print(avg_price_category)

# Produto mais rentável por categoria
best_by_category = df.groupby(["category", "product"])["revenue"].sum().reset_index()
best_by_category = best_by_category.sort_values(["category", "revenue"], ascending=[True, False]).drop_duplicates("category")

# 5️⃣ Gerar gráficos
sns.set_theme(style="whitegrid")

# Receita mensal
plt.figure(figsize=(10,6))
sns.barplot(x=monthly_revenue.index.astype(str), y=monthly_revenue.values, color="skyblue")
plt.title("Monthly Revenue")
plt.xticks(rotation=45)
plt.ylabel("Revenue")
plt.tight_layout()
plt.savefig("charts/monthly_revenue.png")
plt.close()

# Receita trimestral
plt.figure(figsize=(10,6))
sns.barplot(x=quarterly_revenue.index.astype(str), y=quarterly_revenue.values, color="lightgreen")
plt.title("Quarterly Revenue")
plt.ylabel("Revenue")
plt.tight_layout()
plt.savefig("charts/quarterly_revenue.png")
plt.close()

# Top produto por categoria
plt.figure(figsize=(10,6))
sns.barplot(x=best_by_category["category"], y=best_by_category["revenue"], hue=best_by_category["product"], dodge=False)
plt.title("Top Product by Category")
plt.ylabel("Revenue")
plt.tight_layout()
plt.savefig("charts/top_product_by_category.png")
plt.close()

# 6️⃣ Criar relatório avançado
with open("report.txt", "w") as f:
    f.write(f"Total Revenue: {total_revenue}\n")
    f.write(f"Top Product Overall: {top_product}\n")
    f.write(f"Top Category Overall: {top_category}\n\n")
    f.write("Average Price per Category:\n")
    for cat, price in avg_price_category.items():
        f.write(f"- {cat}: ${price:.2f}\n")
    f.write("\nTop Product by Category:\n")
    for idx, row in best_by_category.iterrows():
        f.write(f"- {row['category']}: {row['product']} (${row['revenue']})\n")
    f.write("\nCharts:\n")
    f.write("- Monthly Revenue: charts/monthly_revenue.png\n")
    f.write("- Quarterly Revenue: charts/quarterly_revenue.png\n")
    f.write("- Top Product by Category: charts/top_product_by_category.png\n")

print("\n✅ Analysis complete. Charts saved in 'charts/' and report.txt generated.")