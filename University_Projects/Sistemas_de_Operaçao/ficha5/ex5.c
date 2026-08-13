#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX_ARGS 64  // máximo de argumentos

int main() {
    for (;;) {
        // Prompt
        printf("$ ");
        fflush(stdout);

        // Ler linha de comando
        char* line = NULL;
        size_t len = 0;
        ssize_t nread = getline(&line, &len, stdin);
        if (nread == -1) {
            free(line);
            exit(EXIT_FAILURE);
        }

        // Remover o '\n' final
        if (line[nread - 1] == '\n') {
            line[nread - 1] = '\0';
        }

        // Comando exit
        if (strcmp(line, "exit") == 0) {
            free(line);
            break;
        }

        // Separar linha em argumentos
        char* args[MAX_ARGS];
        int i = 0;
        args[i] = strtok(line, " ");
        while (args[i] != NULL && i < MAX_ARGS - 1) {
            i++;
            args[i] = strtok(NULL, " ");
        }

        // Fork para criar processo filho
        pid_t pid = fork();
        if (pid == -1) {
            perror("fork");
            free(line);
            exit(EXIT_FAILURE);
        }

        if (pid == 0) {
            // Filho executa comando
            execvp(args[0], args);
            perror("execvp");  // só executa se falhar
            exit(EXIT_FAILURE);
        } else {
            // Pai espera pelo filho
            int status;
            if (waitpid(pid, &status, 0) == -1) {
                perror("waitpid");
                free(line);
                exit(EXIT_FAILURE);
            }
        }

        free(line);
    }

    return 0;
}
