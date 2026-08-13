/*O Problema

Dado um conjunto de N segmentos de recta com coordenadas [Li, Ri] e um número M, a tua tarefa é descobrir qual a menor quantidade possível de segmentos que cobrem o segmento [0,M].
Input

Na primeira linha do input vem um número M indicando o tamanho do segmento que queremos cobrir, tal como atrás explicado.

Na segunda linha vem um número N, indicando a quantidade de segmentos a considerar. Seguem-se N linhas, cada uma com 2 inteiros indicando os pontos iniciais e finais de cada um dos segmentos, ou seja, Li e Ri.
Output

O output deve ser constituído por uma linha contendo um único número: a quantidade mínima de segmentos necessária para cobrir o segmento [0,M]. Para os casos de teste dados, é garantido que existe sempre maneira de cobrir o segmento [0,M]. */

import java.util.Scanner;
import java.util.Arrays;
import java.util.*; 
import java.lang.*; 
import java.io.*; 

class Point{
	int left,right;

	Point(int a, int b){
		left=a;
		right=b;
	}
	public String toString(){
		return left + " " + right;
	}
}
class Sortbyx implements Comparator<Point> {

    public int compare(Point a, Point b) 
    { 
        return a.left-b.left; 
    } 
} 

public class cobertura{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int n = in.nextInt();
        int counter=0;

		Point[] point_array = new Point[n+1];
		point_array[n] = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
		
		
		for(int i=0;i<n;i++){
			point_array[i] = new Point(in.nextInt(), in.nextInt());
		}

		Arrays.sort(point_array,new Sortbyx());    
		//System.out.println(Arrays.toString(point_array));

		int end=0;
		int cur_max=0;

		for(int j=0;j<n+1;j++){
			if(end >= point_array[j].left){
				cur_max = Math.max(point_array[j].right,cur_max);
			}
			else{
				counter++;
				end=cur_max;
				//System.out.println(end);
				//System.out.println(point_array[j].left + " " + point_array[j].right);
				cur_max=point_array[j].right;
			}
			if(end>=size) break;
		}

		System.out.println(counter);
	}
}