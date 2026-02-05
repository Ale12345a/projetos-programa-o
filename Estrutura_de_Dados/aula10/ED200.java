import java.util.Scanner;

public class ED200 {

    static int rows;
    static int cols;
    static char m[][];
    static boolean visited[][];

    // Vizinhança 8-direções
    static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int[] dx = {-1,0,1,-1,1,-1,0,1};

    // Tamanho do micróbio que inclui posição (y,x)
    static int floodFill(int y, int x) {
        if (y < 0 || y >= rows || x < 0 || x >= cols) return 0; // Fora da matriz
        if (visited[y][x]) return 0;   // Já visitada
        if (m[y][x] == '.') return 0;  // Vazio

        visited[y][x] = true;
        int count = 1;

        for (int dir = 0; dir < 8; dir++) {
            count += floodFill(y + dy[dir], x + dx[dir]);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();  // Número de casos

        for (int caso = 0; caso < N; caso++) {
            rows = in.nextInt();
            cols = in.nextInt();
            m = new char[rows][cols];
            visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                m[i] = in.next().toCharArray();
            }

            int maxSize = 0;

            // Iterar sobre todas as células
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!visited[i][j] && m[i][j] == '#') {
                        int size = floodFill(i, j);
                        if (size > maxSize) maxSize = size;
                    }
                }
            }

            System.out.println(maxSize);
        }

        in.close();
    }
}
