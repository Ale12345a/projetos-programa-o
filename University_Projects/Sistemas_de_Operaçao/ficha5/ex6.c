#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX_ARGS 64  // número máximo de argumentos

int main() {
    for (;;) {
        // Prompt
        printf("$ ");
        fflush(stdout);

        // Ler linha de comando
        char *command = NULL;
        size_t len = 0;
        ssize_t nread = getline(&command, &len, stdin);
        if (nread == -1) {
            free(command);
            exit(EXIT_FAILURE);
        }

        // Remover o '\n' final
        if (command[nread - 1] == '\n') {
            command[nread - 1] = '\0';
        }

        // Comando exit
        if (strcmp(command, "exit") == 0) {
            free(command);
            break;
        }

        // Separar comando e argumentos usando strsep
        char *args[MAX_ARGS];
        int i = 0;
        char *token;
        char *rest = command;
        while ((token = strsep(&rest, " \t")) != NULL && i < MAX_ARGS - 1) {
            if (*token == '\0') continue; // ignorar strings vazias
            args[i++] = token;
        }
        args[i] = NULL; // terminar o array de argumentos com NULL

        if (i == 0) {
            free(command);
            continue; // linha vazia
        }

        // Criar processo filho
        pid_t pid = fork();
        if (pid == -1) {
            perror("fork");
            free(command);
            exit(EXIT_FAILURE);
        }

        if (pid == 0) {
            // Filho executa comando
            execvp(args[0], args);
            perror("execvp"); // executa somente se houver erro
            exit(EXIT_FAILURE);
        } else {
            // Pai espera o filho terminar
            int status;
            if (waitpid(pid, &status, 0) == -1) {
                perror("waitpid");
                free(command);
                exit(EXIT_FAILURE);
            }
        }

        free(command);
    }

    return 0;
}
