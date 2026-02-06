/*O Problema

Dado um conjunto de operações de adição e remoção de Bakugans, a tua tarefa é dizer quais os Bakugans que são retirados em cada operação de remoção. As operações de remoção possíveis correspondem a remover o Bakugan com maior ou menor energia de toda a colecção.
Input

Na primeira linha do input estão dois números inteiros A e R, separados por um espaço, indicando respectivamente a quantidade de adições de Bakugans (A) e a quantidade de remoções de mínimos ou máximos (R). Seguem-se exactamente A+R linhas, cada uma contendo um dos seguintes 3 formatos (sem as aspas):

    "BAK E", onde E é um número, indicando uma operação de adição à colecção de um Bakugan de energia E.
    "MIN", indicando uma remoção do Bakugan da colecção com menor energia;
    "MAX", indicando uma remoção do Bakugan da colecção com maior energia; 

Podem existir vários Bakugans com a mesma energia ao mesmo tempo na colecção, sendo que nesse caso é indiferente qual o Bakugan que é retirado. É garantido que existe sempre pelo menos um Bakugan na colecção quando uma operação de remoção é efectuada. Todas as operações são feitas pela ordem em que aparecem no input.
Output

O output deve ter exactamente R linhas. Em cada uma delas deve ser imprimido um único número inteiro indicando a energia do Bakugan retirado na operação de remoção respectiva, pela mesma ordem em que estas operações aparecem no input. */

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