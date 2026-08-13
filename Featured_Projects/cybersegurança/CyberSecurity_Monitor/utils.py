import pandas as pd
import matplotlib.pyplot as plt
import os

def detect_suspicious(df):
    alerts = []

    # Brute force
    for user, group in df.groupby("user"):
        fails = group[group["status"] == "fail"]
        if len(fails) >= 3:
            alerts.append({
                "type": "Brute Force",
                "user": user,
                "details": f"{len(fails)} failed logins"
            })

    # Out of hours
    df["hour"] = df["timestamp"].dt.hour
    night = df[df["hour"] < 6]

    for _, row in night.iterrows():
        alerts.append({
            "type": "Out of Hours",
            "user": row["user"],
            "details": str(row["timestamp"])
        })

    # Suspicious IP (sem duplicados)
    suspicious_ips = df[~df["ip_address"].str.startswith(("192.", "10.", "172."))]

    for ip in suspicious_ips["ip_address"].unique():
        user = suspicious_ips[suspicious_ips["ip_address"] == ip]["user"].iloc[0]
        alerts.append({
            "type": "Suspicious IP",
            "user": user,
            "details": ip
        })

    return pd.DataFrame(alerts)


def calculate_risk(df, alerts):
    risk = {}

    for user in df["user"].unique():
        score = 0

        user_alerts = alerts[alerts["user"] == user]

        for _, row in user_alerts.iterrows():
            if row["type"] == "Brute Force":
                score += 50
            elif row["type"] == "Out of Hours":
                score += 20
            elif row["type"] == "Suspicious IP":
                score += 30

        risk[user] = score

    return pd.DataFrame(list(risk.items()), columns=["user", "risk_score"])


def plot_graphs(df, risk_df):
    os.makedirs("charts", exist_ok=True)

    # Login status
    df["status"].value_counts().plot(kind="bar")
    plt.title("Login Status")
    plt.savefig("charts/login_status.png")
    plt.clf()

    # Logins por hora
    df["hour"].value_counts().sort_index().plot(kind="bar")
    plt.title("Logins by Hour")
    plt.savefig("charts/logins_by_hour.png")
    plt.clf()

    # Top risky users
    risk_df.sort_values("risk_score", ascending=False).plot(
        x="user", y="risk_score", kind="bar"
    )
    plt.title("Risk Score by User")
    plt.savefig("charts/risk_scores.png")
    plt.clf()