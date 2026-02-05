#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX_ARGS 64
#define MAX_HISTORY 100  // número máximo de comandos armazenados

int main() {
    char* history[MAX_HISTORY];
    int history_count = 0;

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

        // Remover '\n'
        if (command[nread - 1] == '\n') {
            command[nread - 1] = '\0';
        }

        // Ignorar linha vazia
        if (strlen(command) == 0) {
            free(command);
            continue;
        }

        // Salvar no histórico
        if (history_count < MAX_HISTORY) {
            history[history_count++] = strdup(command);
        } else {
            // se passar de MAX_HISTORY, descartar o mais antigo
            free(history[0]);
            for (int i = 1; i < MAX_HISTORY; i++)
                history[i-1] = history[i];
            history[MAX_HISTORY-1] = strdup(command);
        }

        // Comando exit
        if (strcmp(command, "exit") == 0) {
            free(command);
            break;
        }

        // Comando myhistory
        if (strncmp(command, "myhistory", 9) == 0) {
            int n = 10; // default: mostrar últimos 10
            char *arg = command + 9;
            while (*arg == ' ' || *arg == '\t') arg++; // pular espaços
            if (*arg != '\0') n = atoi(arg);

            if (n > history_count) n = history_count;
            for (int i = history_count - n; i < history_count; i++) {
                printf("%d: %s\n", i+1, history[i]);
            }
            free(command);
            continue;
        }

        // Separar comando e argumentos
        char *args[MAX_ARGS];
        int i = 0;
        char *token;
        char *rest = command;
        while ((token = strsep(&rest, " \t")) != NULL && i < MAX_ARGS - 1) {
            if (*token == '\0') continue;
            args[i++] = token;
        }
        args[i] = NULL;

        // Criar processo filho
        pid_t pid = fork();
        if (pid == -1) {
            perror("fork");
            free(command);
            exit(EXIT_FAILURE);
        }

        if (pid == 0) {
            execvp(args[0], args);
            perror("execvp");
            exit(EXIT_FAILURE);
        } else {
            int status;
            if (waitpid(pid, &status, 0) == -1) {
                perror("waitpid");
                free(command);
                exit(EXIT_FAILURE);
            }
        }

        free(command);
    }

    // liberar memória do histórico
    for (int i = 0; i < history_count; i++)
        free(history[i]);

    return 0;
}
