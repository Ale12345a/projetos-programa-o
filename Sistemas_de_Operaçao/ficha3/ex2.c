/*Considere o seguinte programa que recebe duas strings na linha de comando (argv[1]
e argv[2]) e realiza opera¸c˜oes com elas com a API de strings da Biblioteca Standard do
C (clib). Compile-o e experimente-o.*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(int argc, char* argv[]) {
/* comparar argv[1] e argv[2] usando ordem lexicográfica */
int result = strcmp(argv[1], argv[2]);
if (result == 0)
printf("the strings are the same\n");
else if (result < 0)
printf("%s < %s\n", argv[1], argv[2]);
else
printf("%s > %s\n", argv[1], argv[2]);
/* crie uma cópia de argv[1] e outra de argv[2] */
char *p1 = strdup(argv[1]);
char *p2 = strdup(argv[2]);
printf("p1 holds:%s\n", p1);
printf("p2 holds:%s\n", p2);
/* * esta é outra maneira de fazer isso */
char* p3 = (char*)malloc((strlen(argv[1]) + 1) * sizeof(char));
char* p4 = (char*)malloc((strlen(argv[2]) + 1) * sizeof(char));
strcpy(p3, argv[1]);
strcpy(p4, argv[2]);
printf("p3 holds:%s\n", p3);
printf("p4 holds:%s\n", p4);
/* concatenar ambas as strings, alocando espaço para:
todos os caracteres de argv[1],
todos os caracteres de argv[2],
o '\0' final */
char* p5 = (char*)malloc((strlen(argv[1]) + strlen(argv[2]) + 1) * sizeof(char));
strcpy(p5, p1);
strcat(p5, p2);
printf("p5 holds:%s\n", p5);
exit(EXIT_SUCCESS);
}