
import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score

# 1. Ler os dados
df = pd.read_csv("Clientes_Base.csv")

# 2. Selecionar as variáveis e aplicar one-hot encoding à região
num_cols = ["idade", "gasto_mensal", "visitas_mensais"]

X = pd.get_dummies(
    df[num_cols + ["regiao"]],
    columns=["regiao"],
    drop_first=False,
    dtype=int
)

# 3. Normalizar as variáveis numéricas
scaler = StandardScaler()
X[num_cols] = scaler.fit_transform(X[num_cols])

# 4. Testar K-Means com k = 2, 3 e 4
best_k = None
best_s = -1
best_labels = None

for k in [2, 3, 4]:
    km = KMeans(
        n_clusters=k,
        random_state=42,
        n_init=10
    )

    labels = km.fit_predict(X)
    score = silhouette_score(X, labels)

    print(f"k={k} | Silhouette score={score:.3f}")

    if score > best_s:
        best_k = k
        best_s = score
        best_labels = labels

# 5. Adicionar o cluster a cada cliente
df["cluster"] = best_labels

print(f"\nMelhor k: {best_k}")
print(f"Silhouette score: {best_s:.3f}")

# 6. Mostrar os clientes e os respetivos clusters
print("\nClientes segmentados:")
print(df[["id", "idade", "gasto_mensal",
          "visitas_mensais", "regiao", "cluster"]]
      .sort_values("cluster")
      .to_string(index=False))

# 7. Calcular as médias de cada segmento
print("\nMédias por segmento:")
print(
    df.groupby("cluster")[num_cols]
      .mean()
      .round(2)
)

# 8. Guardar os resultados
df.to_csv("Clientes_Base_segmentados.csv", index=False)
print("\nResultados guardados em Clientes_Base_segmentados.csv")
