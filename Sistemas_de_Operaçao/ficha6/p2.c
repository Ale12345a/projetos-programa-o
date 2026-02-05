#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>      // <- open(), O_RDONLY
#include <sys/types.h>
#include <sys/stat.h>

#define BUF_SIZE 128

int main(int argc, char* argv[]) {
    char* myfifo = "/tmp/myfifo";

    /* abre a FIFO para leitura */
    int fd = open(myfifo, O_RDONLY);
    if (fd == -1) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /* ciclo: lê do pipe e imprime no stdout */
    while (1) {
        char text[BUF_SIZE];
        ssize_t n = read(fd, text, BUF_SIZE);
        if (n > 0) {
            printf("%s", text);
            fflush(stdout);
        } else if (n == 0) {
            // EOF: writer fechou a FIFO
            break;
        } else {
            perror("read");
            break;
        }
    }

    close(fd);
    exit(EXIT_SUCCESS);
}
