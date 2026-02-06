/*O Problema

Dadas vários pares de matrículas em qualquer um dos quatro formatos possíveis, a tua tarefa é calcular e imprimir a distância entre cada par.
Input

Na primeira linha do input vem um inteiro T indicando o número de casos de teste, ou seja, o número de pares de matrículas a considerar.

Seguem-se N linhas, cada uma com duas matrículas num dos quatro formatos possíveis: XX-NN-NN (1ª geração), NN-NN-XX (2ª geração), NN-XX-NN (3ª geração) ou XX-NN-XX (4ª geração)
Output

O output deve conter T linhas. A i-ésima linha deve conter um único inteiro indicando a distância entre o par de matrículas respetivo. */

import java.util.Scanner;

public class matriculas{

	public static int gen(String mat){ // Função que determina a geração da matrícula (FUNCIONAL)
		if((mat.charAt(3)>='0' && mat.charAt(3)<='9') && (mat.charAt(6)>='0'&& mat.charAt(6)<='9')){
			return 1;
		}
		if((mat.charAt(0)>='0' && mat.charAt(0)<='9') && (mat.charAt(3)>='0'&& mat.charAt(3)<='9')){
			return 2;
		}
		if((mat.charAt(0)>='0' && mat.charAt(0)<='9') && (mat.charAt(6)>='0'&& mat.charAt(6)<='9')){
			return 3;
		}	
		return 4;
	}

	public static int char_to_int(char x){ //converter os chars nos inteiros correspondentes
      int val=0;
      if((int) x>=48 && (int) x<=57)
        val+= ((int) x) -48;
      if((int) x>=65 && (int) x<75)
        val+= ((int) x)- 65;
      if((int) x>75 && (int) x<=87)
        val+= ((int) x)- 66;
      if((int) x>87 && (int) x<=89)
        val+= ((int) x)- 67;
      if((int) x>89 && (int) x<=90)
        val+= ((int) x)- 68;

      return val;
    }

	public static int to_num(String mat, int gen){
		int num=0;
		int pos=10000;

      switch (gen) {
        case 1:
        for (int i=0;i<8;i++){
          if (i==2 || i==5) continue;
          else if (i==0) num = char_to_int(mat.charAt(i))*10000*23;
          else{
            num = char_to_int(mat.charAt(i))*pos;
            pos=pos/10;
          }
        }
        num++;
        break;

        case 2:
        for (int i=0;i<8;i++){
          if (i==2 || i==5) continue;
          if (i==0) num = char_to_int(mat.charAt(i))*1000;
          if (i==1) num = char_to_int(mat.charAt(i))*100;
          if (i==6) num = char_to_int(mat.charAt(i))*10000*23;
          if (i==7) num = char_to_int(mat.charAt(i))*10000;
          if (i==3) num = char_to_int(mat.charAt(i))*10;
          if (i==4) num = char_to_int(mat.charAt(i))*1;
        }
        num += 5290000; //nº de matrículas das gerações anteriores
        num++;
        break;

        case 3:
        for (int i=0;i<8;i++){
          if (i==2 || i==5) continue;
          if (i==0) num = char_to_int(mat.charAt(i))*1000;
          if (i==1) num = char_to_int(mat.charAt(i))*100;
          if (i==3) num = char_to_int(mat.charAt(i))*10000*23;
          if (i==4) num = char_to_int(mat.charAt(i))*10000;
          if (i==6) num = char_to_int(mat.charAt(i))*10;
          if (i==7) num = char_to_int(mat.charAt(i))*1;
        }
        num += 2*5290000; //nº de matrículas das gerações anteriores
        num++;
        break;

        case 4:
        for (int i=0;i<8;i++){
          if (i==2 || i==5) continue;
          if (i==0) num = char_to_int(mat.charAt(i))*100*23*23*23;
          if (i==1) num = char_to_int(mat.charAt(i))*100*23*23;
          if (i==3) num = char_to_int(mat.charAt(i))*10;
          if (i==4) num = char_to_int(mat.charAt(i))*1;
          if (i==6) num = char_to_int(mat.charAt(i))*100*23;
          if (i==7) num = char_to_int(mat.charAt(i))*100;
        }
        num+=3*5290000; //nº de matrículas das gerações anteriores
        num++;
        break;

      }
      return num;
    }


    public static void main(String[] args) {
	  Scanner in = new Scanner(System.in);

	  int T = in.nextInt();

	  String mat_1;
	  String mat_2;

	  int dif;

	  for (int i=0; i<T;i++){
	    mat_1= in.next();
	    mat_2= in.next();

	    dif= to_num(mat_1, gen(mat_1))- to_num(mat_2, gen(mat_2));
	    if (dif>=0)                //certeficar que o número que damos print é sempre positivo
	      System.out.println(dif);
	      else{
	        dif= dif*(-1);
	        System.out.println(dif);
	      }
	    }
	  }

}


