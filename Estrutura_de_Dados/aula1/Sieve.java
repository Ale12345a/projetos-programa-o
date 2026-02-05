public class Sieve {

    // Procedimento para usar o algoritmo do Crivo de Eratóstenes
    public static void sieve(int n, boolean prime[]) {
        // 1. Inicializar todos os números como candidatos a primos
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }

        // 2. Percorrer os números até à raiz de n
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                // 3. Marcar todos os múltiplos de i como não primos
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 1000; // Limite dos números

        // Cria um array de boolean com tamanho n+1
        boolean[] prime = new boolean[n+1];

        // Chama o procedimento sieve
        sieve(n, prime);

        // Escreve todos os números que ficaram marcados a true
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                System.out.println(i);
            }
        }
    }
}
