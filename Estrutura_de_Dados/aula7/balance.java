/*Recebe uma string formada apenas por parenteses rectos e curvos, e devolve true caso a expressão esteja bem formada e false caso contrário.

Por exemplo, "([()])" e "[()()]" são expressões bem formadas, ao passo que "(()]", "[()[])" ou "[()[]" não estão balanceadas (faltam parenteses por fechar ou fecham os parenteses errados). */

public class balance {
    public static boolean balanced(String s) {
        MyStack<Character> stack = new LinkedListStack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[') {
                stack.push(c); // abre-parêntese → empilha
            } else if (c == ')' || c == ']') {
                if (stack.isEmpty()) return false; // fecha sem abrir
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[')) {
                    return false; // parêntese errado
                }
            }
        }

        return stack.isEmpty(); // true se todos os parênteses foram fechados
    }

    // Apenas para testar localmente
    public static void main(String[] args) {
        System.out.println(balanced("([()])"));  // true
        System.out.println(balanced("[()()]"));  // true
        System.out.println(balanced("(()]"));    // false
        System.out.println(balanced("[()[])"));  // false
        System.out.println(balanced("[()[]"));   // false
    }
}
