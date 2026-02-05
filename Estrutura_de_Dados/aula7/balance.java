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
