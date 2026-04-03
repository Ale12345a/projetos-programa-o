#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include "scanner.h"

#define MAX_THREADS 100

pthread_mutex_t lock;

typedef struct {
    char ip[100];
    int port;
} ScanData;

void *thread_scan(void *arg) {
    ScanData *data = (ScanData *)arg;
    scan_port(data->ip, data->port);
    free(data);
    return NULL;
}

int main() {
    char ip[100];
    int start_port, end_port;

    pthread_t threads[MAX_THREADS];
    pthread_mutex_init(&lock, NULL);

    printf("=== ADVANCED SECURITY SCANNER ===\n");

    printf("Enter IP: ");
    scanf("%s", ip);

    printf("Start port: ");
    scanf("%d", &start_port);

    printf("End port: ");
    scanf("%d", &end_port);

    // limpar ficheiro
    FILE *f = fopen("results.txt", "w");
    if (f != NULL) fclose(f);

    int count = 0;

    for (int port = start_port; port <= end_port; port++) {
        ScanData *data = malloc(sizeof(ScanData));
        strcpy(data->ip, ip);
        data->port = port;

        pthread_create(&threads[count], NULL, thread_scan, data);
        count++;

        if (count >= MAX_THREADS) {
            for (int i = 0; i < count; i++)
                pthread_join(threads[i], NULL);
            count = 0;
        }
    }

    for (int i = 0; i < count; i++)
        pthread_join(threads[i], NULL);

    pthread_mutex_destroy(&lock);

    printf("\n✅ Scan complete! Results saved in results.txt\n");
    return 0;
}