
import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error

# 1. Ler os dados
df = pd.read_csv("Procura_Semanal.csv")

# 2. Criar variáveis temporais
df["lag_1"] = df["procura"].shift(1)
df["lag_2"] = df["procura"].shift(2)

# Média das 3 semanas anteriores (sem fuga de informação)
df["mm3_prev"] = df["procura"].shift(1).rolling(3).mean()

# Remover linhas sem histórico suficiente
df = df.dropna().reset_index(drop=True)

# 3. Separar treino e teste
# Treino: semanas 4 a 9
# Teste: semanas 10 a 12
train = df.iloc[:6]
test = df.iloc[6:]

features = ["lag_1", "lag_2", "mm3_prev"]

X_train = train[features]
y_train = train["procura"]

X_test = test[features]
y_test = test["procura"]

# 4. Baseline ingénuo: previsão igual à semana anterior
y_pred_naive = test["lag_1"].to_numpy()

# 5. Modelo de regressão linear
modelo = LinearRegression()
modelo.fit(X_train, y_train)
y_pred = modelo.predict(X_test)

# 6. Métricas de avaliação
def calcular_mape(real, previsto):
    real = np.asarray(real, dtype=float)
    previsto = np.asarray(previsto, dtype=float)
    return np.mean(np.abs((real - previsto) / real)) * 100

mae_naive = mean_absolute_error(y_test, y_pred_naive)
mape_naive = calcular_mape(y_test, y_pred_naive)

mae_modelo = mean_absolute_error(y_test, y_pred)
mape_modelo = calcular_mape(y_test, y_pred)

# 7. Comparar previsões
resultados = pd.DataFrame({
    "Semana": test["semana"].to_numpy(),
    "Procura real": y_test.to_numpy(),
    "Baseline": y_pred_naive,
    "Regressao linear": y_pred
})

print("=== PREVISÕES NO CONJUNTO DE TESTE ===")
print(resultados.round(2).to_string(index=False))

print("\n=== MÉTRICAS ===")
print(f"Baseline -> MAE: {mae_naive:.2f} | MAPE: {mape_naive:.2f}%")
print(f"Modelo   -> MAE: {mae_modelo:.2f} | MAPE: {mape_modelo:.2f}%")

# 8. Prever a próxima semana, após avaliar
modelo_final = LinearRegression().fit(df[features], df["procura"])

ultima_procura = df["procura"].iloc[-1]
penultima_procura = df["procura"].iloc[-2]
media_ultimas_3 = df["procura"].iloc[-3:].mean()

proxima_semana = pd.DataFrame([{
    "lag_1": ultima_procura,
    "lag_2": penultima_procura,
    "mm3_prev": media_ultimas_3
}])

previsao_futura = modelo_final.predict(proxima_semana)[0]

print("\n=== PREVISÃO DA SEMANA 13 ===")
print(f"Baseline ingénuo: {ultima_procura:.2f}")
print(f"Regressão linear: {previsao_futura:.2f}")
