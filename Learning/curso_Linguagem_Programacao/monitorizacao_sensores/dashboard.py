
import streamlit as st
import pandas as pd
import requests

API_URL = "http://127.0.0.1:8000"

st.set_page_config(
    page_title="Monitorização Industrial",
    page_icon="📊",
    layout="wide"
)

st.title("📊 Monitorização de Sensores Industriais")

st.write(
    "Dashboard para acompanhar temperatura, vibração e pressão."
)


# Botão para simular uma nova leitura
if st.button("Simular leitura de sensor"):

    try:
        resposta = requests.get(
            f"{API_URL}/simular",
            timeout=5
        )

        resposta.raise_for_status()

        resultado = resposta.json()

        st.success(
            f"Leitura recebida: {resultado['dados']['sensor']} "
            f"= {resultado['dados']['valor']}"
        )

        if resultado["alerta"]:
            st.warning(resultado["alerta"])
        else:
            st.info("Leitura dentro dos limites normais.")

    except requests.RequestException:
        st.error("Não foi possível contactar o servidor FastAPI.")


# Consultar histórico e alertas
try:

    resposta_leituras = requests.get(
        f"{API_URL}/leituras",
        timeout=5
    )

    resposta_alertas = requests.get(
        f"{API_URL}/alertas",
        timeout=5
    )

    resposta_leituras.raise_for_status()
    resposta_alertas.raise_for_status()

    leituras = resposta_leituras.json()
    alertas = resposta_alertas.json()

except requests.RequestException:

    st.error(
        "Não foi possível obter os dados. "
        "Confirma se o servidor FastAPI está ligado."
    )

    st.stop()


# Converter os dados em DataFrames
df = pd.DataFrame(leituras)
df_alertas = pd.DataFrame(alertas)


# Indicadores gerais
col1, col2, col3 = st.columns(3)

col1.metric("Total de leituras", len(leituras))

col2.metric("Total de alertas", len(alertas))

col3.metric(
    "Sensores monitorizados",
    df["sensor"].nunique() if not df.empty else 0
)


st.divider()


# Histórico de leituras
st.subheader("Histórico de leituras")

if not df.empty:

    df["timestamp"] = pd.to_datetime(df["timestamp"])

    st.dataframe(
        df[["sensor", "valor", "timestamp", "alerta"]]
        .sort_values("timestamp", ascending=False),
        use_container_width=True
    )

else:

    st.info("Ainda não existem leituras registadas.")


st.divider()


# Gráficos dos sensores
st.subheader("Gráficos dos sensores")

if not df.empty:

    sensores = df["sensor"].unique().tolist()

    sensor_selecionado = st.selectbox(
        "Seleciona um sensor:",
        sensores
    )

    dados_sensor = df[
        df["sensor"] == sensor_selecionado
    ].sort_values("timestamp")

    grafico = dados_sensor.set_index("timestamp")[["valor"]]

    st.line_chart(grafico)

else:

    st.info("Não existem dados suficientes para apresentar gráficos.")


st.divider()


# Apresentar alertas
st.subheader("⚠️ Alertas de segurança")

if not df_alertas.empty:

    st.dataframe(
        df_alertas[
            ["sensor", "valor", "timestamp", "alerta"]
        ],
        use_container_width=True
    )

else:

    st.success("Não existem alertas registados.")


# Atualizar o dashboard
if st.button("Atualizar dashboard"):
    st.rerun()
