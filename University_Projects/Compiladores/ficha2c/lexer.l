%{
#include <stdio.h>
#include <stdlib.h>

enum TokenKind {
    TK_NUMBER,
    TK_IDENT,
    TK_PLUS,
    TK_MINUS,
    TK_STAR,
    TK_SLASH,
    TK_ASSIGN,
    TK_SEMI,
    TK_LPAREN,
    TK_RPAREN,
    TK_INC,
    TK_EOF
};

void print_token(enum TokenKind kind, const char *text) {
    switch(kind) {
        case TK_NUMBER: printf("NUMBER(%s)\n", text); break;
        case TK_IDENT:  printf("IDENT(%s)\n", text); break;
        case TK_PLUS:   printf("PLUS\n"); break;
        case TK_MINUS:  printf("MINUS\n"); break;
        case TK_STAR:   printf("STAR\n"); break;
        case TK_SLASH:  printf("SLASH\n"); break;
        case TK_ASSIGN: printf("ASSIGN\n"); break;
        case TK_SEMI:   printf("SEMI\n"); break;
        case TK_LPAREN: printf("LPAREN\n"); break;
        case TK_RPAREN: printf("RPAREN\n"); break;
        case TK_INC:    printf("INC\n"); break;
        case TK_EOF:    printf("EOF\n"); break;
    }
}
%}

DIGIT   [0-9]
IDSTART [a-zA-Z_]
IDCONT  [a-zA-Z0-9_]

%%

{DIGIT}+            { print_token(TK_NUMBER, yytext); }
{IDSTART}{IDCONT}*  { print_token(TK_IDENT, yytext); }

"++"                { print_token(TK_INC, yytext); }
"+"                 { print_token(TK_PLUS, yytext); }
"-"                 { print_token(TK_MINUS, yytext); }
"*"                 { print_token(TK_STAR, yytext); }
"/"                 { print_token(TK_SLASH, yytext); }
"="                 { print_token(TK_ASSIGN, yytext); }
";"                 { print_token(TK_SEMI, yytext); }
"("                 { print_token(TK_LPAREN, yytext); }
")"                 { print_token(TK_RPAREN, yytext); }

[ \t\n]+            { /* ignora espaços em branco */ }

.                   { fprintf(stderr, "Carácter desconhecido: %s\n", yytext); exit(1); }

%%

int main(int argc, char **argv) {
    yylex();
    print_token(TK_EOF, "");
    return 0;
}

int yywrap(void) {
    return 1; // indica fim do input
}
