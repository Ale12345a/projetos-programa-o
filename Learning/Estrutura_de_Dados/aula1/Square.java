public class Square {
    // Desenha uma linha de n caracteres com 'a' nas extremidades e 'b' no meio
    static void line(char a, char b, int n) {
        System.out.print(a);
        for (int i=1; i<=n-2; i++)
            System.out.print(b);
        System.out.println(a);
    }

    // Desenha um quadrado de tamanho n
    static void square(int n) {
        line('+','-',n);           // primeira linha
        for (int i=1; i<=n-2; i++) // linhas do meio
            line('|','.',n);
        line('+','-',n);           // última linha
    }

    public static void main(String[] args) {
        square(6);
    }
}
