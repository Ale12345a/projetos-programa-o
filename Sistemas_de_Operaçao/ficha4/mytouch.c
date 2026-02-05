#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <time.h>


int main(int argc, char* argv[]) {
    if (argc != 2) {
        fprintf(stderr, "usage: %s filename\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    const char* filename = argv[1];

    /* verifique se o arquivo existe */
    if (access(filename, F_OK) == 0) {
        /* arquivo existe: atualizar os últimos horários de acesso e modificação para a hora atual */
        struct timeval times[2];
        times[0].tv_sec = times[1].tv_sec = time(NULL);  // hora atual
        times[0].tv_usec = times[1].tv_usec = 0;
        if (utimes(filename, times) != 0) {
            perror("utimes");
            exit(EXIT_FAILURE);
        }
    } else {
        /* o arquivo não existe - crie-o com permissões 644

 */
        mode_t perms = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH; // rw-r--r--
        int fd = open(filename, O_CREAT | O_WRONLY, perms);
        if (fd == -1) {
            perror("open");
            exit(EXIT_FAILURE);
        }
        close(fd);
    }

    return 0;
}
