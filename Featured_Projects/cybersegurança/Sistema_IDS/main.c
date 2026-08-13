#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE 256
#define MAX_USERS 100
#define MAX_IPS 100

typedef struct {
    char name[50];
    int hits;
    int risk;
} User;

typedef struct {
    char ip[20];
    int hits;
} IP;

int findUser(User users[], int count, char *name) {
    for (int i = 0; i < count; i++)
        if (strcmp(users[i].name, name) == 0)
            return i;
    return -1;
}

int findIP(IP ips[], int count, char *ip) {
    for (int i = 0; i < count; i++)
        if (strcmp(ips[i].ip, ip) == 0)
            return i;
    return -1;
}

int main() {
    FILE *file = fopen("logs.txt", "r");
    if (!file) {
        perror("Cannot open logs.txt");
        return 1;
    }

    User users[MAX_USERS];
    IP ips[MAX_IPS];
    int userCount = 0, ipCount = 0;

    FILE *alertFile = fopen("alerts.txt", "w");
    if (!alertFile) {
        perror("Cannot create alerts.txt");
        return 1;
    }

    char line[MAX_LINE];
    char user[50], action[20], ip[20], status[20];
    char date[20], time[20], timestamp[50];

    while (fgets(line, MAX_LINE, file)) {
        if (sscanf(line, "%s %s %s %s %s %s", date, time, user, action, ip, status) == 6) {
            sprintf(timestamp, "%s %s", date, time); // timestamp completo

            // --- Estatísticas de Usuário ---
            int idx = findUser(users, userCount, user);
            if (idx == -1) {
                strcpy(users[userCount].name, user);
                users[userCount].hits = 1;
                users[userCount].risk = (strcmp(status, "fail") == 0) ? 5 : 1;
                idx = userCount;
                userCount++;
            } else {
                users[idx].hits++;
                users[idx].risk += (strcmp(status, "fail") == 0) ? 5 : 1;
            }

            // --- Estatísticas de IP ---
            int ipIdx = findIP(ips, ipCount, ip);
            if (ipIdx == -1) {
                strcpy(ips[ipCount].ip, ip);
                ips[ipCount].hits = 1;
                ipIdx = ipCount;
                ipCount++;
            } else {
                ips[ipIdx].hits++;
            }

            // --- Alertas ---
            if (strcmp(status, "fail") == 0) {
                fprintf(alertFile, "ALERT: Failed login by %s from %s at %s\n", user, ip, timestamp);
            }
        }
    }

    fclose(file);
    fclose(alertFile);

    // --- Mostrar Estatísticas ---
    printf("=== USER STATS ===\n");
    for (int i = 0; i < userCount; i++) {
        printf("User: %s | Total: %d | Risk: %d\n", users[i].name, users[i].hits, users[i].risk);
    }

    printf("\n=== IP STATS ===\n");
    for (int i = 0; i < ipCount; i++) {
        printf("IP: %s | Hits: %d\n", ips[i].ip, ips[i].hits);
    }

    printf("\n✅ Analysis complete! Alerts saved in alerts.txt\n");

    return 0;
}