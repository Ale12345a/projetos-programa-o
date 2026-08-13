#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s file1.txt file2.txt ...\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    pid_t pids[argc-1];

    for (int i = 1; i < argc; i++) {
        pid_t pid = fork();
        if (pid < 0) {
            perror("fork");
            exit(EXIT_FAILURE);
        }
        if (pid == 0) {
            // Child process
            char epub_file[1024];
            // cria nome do ficheiro epub removendo .txt
            snprintf(epub_file, sizeof(epub_file), "%.*s.epub", (int)(strlen(argv[i])-4), argv[i]);

            printf("[pid:%d] converting %s ...\n", getpid(), argv[i]);
            execlp("pandoc", "pandoc", argv[i], "-o", epub_file, "--metadata", 
                   "title", argv[i], (char *)NULL);

            // Se execlp falhar
            perror("execlp");
            exit(EXIT_FAILURE);
        } else {
            // Parent process guarda PID
            pids[i-1] = pid;
        }
    }

    // Os pais esperam por todos os filhos
    for (int i = 0; i < argc-1; i++) {
        waitpid(pids[i], NULL, 0);
    }

    // Cria zip com todos os epubs
    char zip_cmd[4096] = "zip ebooks.zip";
    for (int i = 1; i < argc; i++) {
        char epub_file[1024];
        snprintf(epub_file, sizeof(epub_file), " %.*s.epub", (int)(strlen(argv[i])-4), argv[i]);
        strcat(zip_cmd, epub_file);
    }

    int ret = system(zip_cmd);
    if (ret == -1) {
        perror("system");
        exit(EXIT_FAILURE);
    }

    printf("All books converted.\n");
    return 0;
}
