#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <pthread.h>
#include "scanner.h"
#include "utils.h"

extern pthread_mutex_t lock;

void grab_banner(int sock, char *banner) {
    send(sock, "HEAD / HTTP/1.0\r\n\r\n", 18, 0);
    recv(sock, banner, 1024, 0);
}

void scan_port(char *ip, int port) {
    int sock;
    struct sockaddr_in target;
    char banner[1024] = {0};
    char service[50];

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) return;

    struct timeval timeout;
    timeout.tv_sec = 1;
    timeout.tv_usec = 0;

    setsockopt(sock, SOL_SOCKET, SO_RCVTIMEO, &timeout, sizeof(timeout));
    setsockopt(sock, SOL_SOCKET, SO_SNDTIMEO, &timeout, sizeof(timeout));

    target.sin_family = AF_INET;
    target.sin_port = htons(port);
    target.sin_addr.s_addr = inet_addr(ip);

    if (connect(sock, (struct sockaddr*)&target, sizeof(target)) == 0) {

        get_service(port, service);
        grab_banner(sock, banner);

        pthread_mutex_lock(&lock);
        printf("[OPEN] Port %d (%s)\n", port, service);
        printf("Banner: %.50s\n\n", banner);

        save_result(port, service, banner);
        pthread_mutex_unlock(&lock);
    }

    close(sock);
}