/*O Problema

Dada um conjunto S de N números inteiros, e uma sequência de Q perguntas (queries), cada uma indicando um número Pi, a tua tarefa é descobrir qual é a soma de dois números diferentes de S que está mais próxima do número Pi de cada pergunta.
Input

Na primeira linha do input vem um único número indicando N, o tamanho do conjunto S de números. Na segunda linha vêm os números Si do conjunto (é garantido que são todos números distintos).

Na terceira linha vem um número Q, indicando quantidade de perguntas, seguindo-se na quarta linha os números Pi de cada pergunta.
Output

O output deve ser constituído por Q linhas, uma por cada pergunta, na mesma ordem em que vinham no input. Cada uma das linhas deve indicar a soma mais próxima da respectiva pergunta. No caso de existirem várias somas à mesma distância mínima, devem vir todas, por ordem crescente e separadas por um espaço. */

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class soma {

    public static int bsearch(int[] sum, int min, int max,int ans){
        int mid=0;
        int verf_min, verf_max, verf_mid;
        while(min<max){
            mid= min + (max-min)/2;
            if (ans <= sum[mid]) max = mid;
            else{
                min = mid+1;
            }
        }   
        //System.out.println(min + " " + " " + mid + " " + max);
        //System.out.println(sum[min]);
        return min;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int starting_pos = 0;
        int n = in.nextInt();

        int numbers[]; // Array que guarda numeros dados no input
        numbers = new int[n];

        int soma[]; // Array que guarda opçoes de soma com os numeros do input
        soma = new int[(n*(n-1))/2]; // Tamanho do array é nC2

        for(int i=0;i<n;i++){ // preencher o array com os numeros do input
            numbers[i] = in.nextInt();
        }

        for(int m=0;m<n;m++){
            for(int j=m+1;j<n;j++){
                soma[starting_pos]=numbers[m] + numbers[j];
                starting_pos++;
            }
        }

        Arrays.sort(soma); 

        int n2 = in.nextInt();
        int ans[]; // Array que contem as perguntas
        ans = new int[n2]; 

        for(int k=0;k<n2;k++){ // Preenche o array com as perguntas
            ans[k] = in.nextInt();
        }
        int pos, a,b;
        for(int k=0;k<n2;k++){ 
            pos = bsearch(soma,0,soma.length-1,ans[k]);
            if(pos == 0) System.out.println(soma[pos]); 
            else{
                a = Math.abs(soma[pos]-ans[k]);
                b = Math.abs(soma[pos-1]-ans[k]);
                if(a==b) System.out.println(soma[pos -1] + " " + soma[pos]);
                else if (a>b) System.out.println(soma[pos -1]);
                else System.out.println(soma[pos]);
            }
        }   
    }
}
