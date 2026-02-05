#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>      // <- open(), O_WRONLY, etc
#include <sys/types.h>  // <- tipos como mode_t
#include <sys/stat.h>   // <- mkfifo()

#define BUF_SIZE 128

int main(int argc, char* argv[]) {
    char* myfifo = "/tmp/myfifo";

    /* cria a named pipe (FIFO) com permissões rw-rw-rw- */
    int rv = mkfifo(myfifo, 0666);
    if (rv == -1) {
        perror("mkfifo");
        // Se já existir, apenas avisa mas continua
    }

    /* abre a FIFO para escrita */
    int fd = open(myfifo, O_WRONLY);
    if (fd == -1) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /* ciclo: lê do stdin e escreve no pipe */
    while (1) {
        char text[BUF_SIZE];

        if (fgets(text, BUF_SIZE, stdin) == NULL) {
            break; // EOF ou erro
        }

        write(fd, text, strlen(text) + 1);
    }

    close(fd);
    exit(EXIT_SUCCESS);
}
