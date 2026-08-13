import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Set;

public class TestForEach {
   public static void main(String[] args) {

      // Criação de Lista Ligada usando a estrutura de dados do próprio Java
      
      // Exemplo de inicialização usando sintaxe "diamond" (<>). Se não
      // colocarmos o tipo no construtor, o Java consegue inferir qual é
      // olhando para a variável que estamos a inicializar      
      LinkedList<String> list = new LinkedList<>();

      // Adicionando algumas strings à lista ligada
      list.addLast("testing");
      list.addLast("just");
      list.addLast("for");
      list.addLast("fun");

      // Para percorrer a lista podemos usar um for-each
      for (String s : list)
         System.out.println(s);

      // Qualquer TAD iterável pode ser usado com um for each
      // Aqui fica um exemplo com... conjuntos (interface Set)
      // TreeSet implementa um conjunto usando árvores binárias de pesquisa
      Set<Integer> set = new TreeSet<>();
      set.add(42); 
      set.add(24);
      set.add(0);

      // Podemos usar int por causa do autounboxing
      for (int i : set)
         System.out.println(i);
   }
}