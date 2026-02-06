/*Deve inverter, na pilha, os seus primeiros n elementos. Por exemplo, se s for {1,2,3,4,5} (com 1 no topo da pilha), então uma chamada a reverse(s, 3) deve transformar s em {3,2,1,4,5}.

Pode assumir que n é um valor entre 1 e o tamanho da pilha.  */

public class reverse {

    public static void reverse(MyStack<Integer> s, int n) {
        LinkedListStack<Integer> aux = new LinkedListStack<>();
        for (int i = 0; i < n; i++) aux.push(s.pop());
        for (int i = 0; i < n; i++) s.push(aux.pop());
    }

    // Classe main só para testar localmente
    public static void main(String[] args) {
        MyStack<Integer> s = new LinkedListStack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5); // Pilha = {5 topo, 4, 3, 2, 1}

        System.out.println("Antes: " + s);

        reverse(s, 3);

        System.out.println("Depois: " + s); // Esperado: {3 topo, 4, 5, 2, 1}? ou ajustar ordem conforme implementação
    }
}