import java.util.TreeSet; // Conjuntos
import java.util.TreeMap; // Mapas (associar valores a chaves)
import java.util.Map;     // Para o tipo Map.Entry
import java.util.Scanner;

public class bakugans {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int add = in.nextInt();
        int rem = in.nextInt();
        String a;
        int value = 0;
        int small=0,big=0;

        TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();

        for(int i=0;i<add+rem;i++){
            a = in.next();
            if(a.charAt(0) == 'B'){ // Se a palvra for BAK 
                value = in.nextInt();
                if(m.containsKey(value)) m.replace(value,m.get(value)+1);
                else m.put(value,1);
            }
            if(a.charAt(0) == 'M' && a.charAt(1) == 'I'){
                small = m.firstKey(); // Menor
                System.out.println(small);
                if(m.get(small)-1 <=0) m.remove(small);
                else m.replace(small, m.get(small)-1);
            }
            if(a.charAt(0) == 'M' && a.charAt(1) == 'A'){
                big = m.lastKey(); // Maior
                System.out.println(big);	
                if(m.get(big)-1<=0) m.remove(big);
                else m.replace(big, m.get(big)-1);
            }
        }
       // FastPrint.out.close();
    }
}