#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE 1024

int main(int argc, char* argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <string> <file>\n", argv[0]);
        return EXIT_FAILURE;
    }

    char* search = argv[1];
    char* filename = argv[2];

    FILE* file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Error: could not open file %s\n", filename);
        return EXIT_FAILURE;
    }

    char line[MAX_LINE];
    int line_number = 0;
    int first = 1;

    printf("[");
    while (fgets(line, MAX_LINE, file)) {
        line_number++;
        char* p = line;

        while ((p = strstr(p, search)) != NULL) {
            int col_number = (int)(p - line) + 1; // coluna começa em 1
            if (!first) {
                printf(", ");
            }
            printf("%d:%d", line_number, col_number);
            first = 0;
            p++; // avançar para próxima ocorrência
        }
    }
    printf("]\n");

    fclose(file);
    return EXIT_SUCCESS;
}
