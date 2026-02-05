// Exemplos de uso de Strings
public class Strings {

    // Metodo que verifica se uma string e um palindromo
    // Argumento: String
    // Retorno: boolean
    public static boolean isPalindrome(String s) {
        String invertida = "";

        // Construir a string invertida
        for (int i = s.length() - 1; i >= 0; i--) {
            invertida += s.charAt(i);
        }

        // Comparar conteudo das strings
        return s.equals(invertida);
    }

    public static void main(String[] args) {

        // ERRO DE COMPILACAO:
        // Uma String nao pode receber diretamente um inteiro
        // String a = 1007;

        // Forma correta:
        // String a = "1007";
        // ou
        // String a = Integer.toString(1007);

        String b = "1007";   // Colocar "1007" na variável b
        String c = "CC";     // Colocar "CC" na variável c
        String d = c + b;    // Concatenar "CC" com "1007"
        String e = c + 1007; // Concatenar "CC" com o número 1007

        // Escrever conteúdo das strings
        System.out.println("d = " + d);
        System.out.println("e = " + e);

        // Comparacao de strings
        System.out.println("d==e? " + (d == e));             // ERRADO: compara referencias
        System.out.println("d.equals(e)? " + d.equals(e));   // CERTO: compara conteudo

        // Tamanho da string
        int size = d.length();
        System.out.println("tamanho de d = " + size);

        // Aceder a caracteres individuais
        System.out.println("d.charAt(0) = " + d.charAt(0));
        System.out.println("d.charAt(2) = " + d.charAt(2));

        // ERRO DE EXECUCAO (IndexOutOfBoundsException)
        // A string "CC1007" tem tamanho 6 (indices 0 a 5)
        // System.out.println("d.charAt(10) = " + d.charAt(10));

        // Substring
        String sub = d.substring(1, 4);
        System.out.println("d.substring(1,4) = " + sub);

        // Procurar a primeira ocorrencia de um caracter
        char ch = '7';
        int pos = d.indexOf(ch);
        System.out.println("d.indexOf(" + ch + ") = " + pos);

        // Metodo da documentacao: converter para minusculas
        String minusculas = d.toLowerCase();
        System.out.println("d em minusculas = " + minusculas);

        // Testes da funcao palindromo
        System.out.println("isPalindrome(\"hello\") = " + isPalindrome("hello"));
        System.out.println("isPalindrome(\"bob\") = " + isPalindrome("bob"));
        System.out.println("isPalindrome(\"radar\") = " + isPalindrome("radar"));
    }
}
