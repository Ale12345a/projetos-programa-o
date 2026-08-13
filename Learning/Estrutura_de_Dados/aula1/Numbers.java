public class Numbers {
    public static void main(String[] args) {
        int n = 10; // limite dos números
        int soma = 0; // variável para acumular a soma (tipo int suficiente para n ≤ 65535)

        System.out.println("Números de 1 a " + n + ":");
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
            soma += i; // acumula a soma
        }

        System.out.println("Soma = " + soma);
    }
}
