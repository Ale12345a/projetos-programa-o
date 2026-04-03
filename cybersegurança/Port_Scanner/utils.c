#include <stdio.h>
#include <string.h>
#include "utils.h"

void get_service(int port, char *service) {
    if (port == 80) strcpy(service, "HTTP");
    else if (port == 22) strcpy(service, "SSH");
    else if (port == 21) strcpy(service, "FTP");
    else if (port == 443) strcpy(service, "HTTPS");
    else strcpy(service, "Unknown");
}

void save_result(int port, char *service, char *banner) {
    FILE *f = fopen("results.txt", "a");

    if (f != NULL) {
        fprintf(f, "Port %d (%s) - %s\n", port, service, banner);
        fclose(f);
    }
}