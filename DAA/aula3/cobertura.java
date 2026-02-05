/* Tive ajuda na parte final do Luís Leite(up201906750) */

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