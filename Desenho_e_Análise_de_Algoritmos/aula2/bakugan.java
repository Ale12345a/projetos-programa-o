import java.util.Scanner;
import java.util.Arrays;

public class bakugan{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int [] power;
		power = new int[n];

		power[0] = in.nextInt();

		for(int i=1;i<n;i++){ // Preencher o array com o poder dos Bakugans
			power[i] = in.nextInt()+power[i-1];
		}

        System.out.println(Arrays.toString(power));
		
		int pics = in.nextInt();
		int num1, num2;
		int sum=0;

		for(int i=0;i<pics;i++){
			num1 = in.nextInt()-2;
			num2 = in.nextInt()-1;
			sum = power[num2];
			if(num1 >=0 && num1<n){
				sum -= power[num1];
			}
		System.out.println(sum);
		sum=0;
		}
		System.out.close();
	}
}