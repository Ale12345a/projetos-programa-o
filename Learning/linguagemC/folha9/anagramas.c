/*Escreva uma função int anagramas(char str1[], char str2[]) que determina se
duas cadeias de carateres são anagramas, isto é, se se escrevem com os mesmos carateres.
O resultado deve ser 1 em caso armativo e 0 caso contrário. Por exemplo, deposit e
topside são anagramas:
char str1[] = "deposit";
char str2[] = "topside";
int r = anagramas(str1,str2); // resultado 1
Sugestão: use uma função auxiliar como no exercício anterior para ordenar ambas as
cadeias; as cadeias originais são anagramas se e só se as cadeias ordenadas são exatamente
iguais carater-a-carater.*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Função auxiliar para normalizar uma string
// Remove espaços, sinais de pontuação e converte tudo para minúsculas
void normalizar(char str[]) {
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (isalpha((unsigned char)str[i])) { // mantém apenas letras
            str[j++] = tolower((unsigned char)str[i]); // converte para minúscula
        }
    }
    str[j] = '\0';
}

// Função para ordenar caracteres de uma string (ordenação por seleção)
void ordenar(char str[]) {
    int n = strlen(str);
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            if (str[j] < str[min_idx]) {
                min_idx = j;
            }
        }
        char temp = str[i];
        str[i] = str[min_idx];
        str[min_idx] = temp;
    }
}

// Função para verificar se duas strings são anagramas no sentido lato
int anagramas(char str1[], char str2[]) {
    char copia1[200], copia2[200];
    strcpy(copia1, str1);
    strcpy(copia2, str2);

    // Normalizar as strings
    normalizar(copia1);
    normalizar(copia2);

    // Se os comprimentos forem diferentes, não são anagramas
    if (strlen(copia1) != strlen(copia2)) return 0;

    // Ordenar as strings normalizadas
    ordenar(copia1);
    ordenar(copia2);

    // Comparar caracter a caracter
    for (int i = 0; i < strlen(copia1); i++) {
        if (copia1[i] != copia2[i]) return 0;
    }

    return 1; // são anagramas
}

int main(void) {
    char frase1[] = "Quid est veritas?";
    char frase2[] = "Est vir qui adest";

    if (anagramas(frase1, frase2)) {
        printf("São anagramas!\n");
    } else {
        printf("Não são anagramas.\n");
    }

    return 0;
}
