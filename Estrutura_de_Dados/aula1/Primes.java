public class Primes {
    // Verifica se o número n é primo
    static boolean isPrime(int n) {
        if (n < 2) return false; // 0 e 1 não são primos

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false; // encontrou divisor → não é primo
            }
        }
        return true; // não encontrou divisor → é primo
    }
    
    public static void main(String[] args) {
        int n = 1000; // Limite dos números
        
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }
}
