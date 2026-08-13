import java.util.Arrays;

// Classe para representar uma equipa
class Team implements Comparable<Team>{
   String name;
   int points;

   // Construtor
   Team(String n, int p) {
      name = n;
      points = p;
   }

   // Método para dar representação em String de um objecto da classe
   public String toString() {
      return "(" + name + "," + points + ")";
   }
   public int compareTo(Team t) {
        if (this.points != t.points) {
            return t.points - this.points; // ordem decrescente
        } else {
            return this.name.compareTo(t.name); // empate → ordem alfabética
        }
    }
}


// Testando o método Arrays.sort()
class TestTeams {
   public static void main(String[] args) {
      // Inicializar array v
      Team[] v = new Team[7];

      v[0] = new Team("Leicester", 20);
      v[1] = new Team("Chelsea", 31);
      v[2] = new Team("Arsenal", 20);
      v[3] = new Team("Liverpool", 42);
      v[4] = new Team("Man United", 37);
      v[5] = new Team("Man City", 20);
      v[6] = new Team("Tottenham", 39);
      
      // Ordenar e escrever array v
      Arrays.sort(v);
      System.out.println(Arrays.toString(v));
   }
}