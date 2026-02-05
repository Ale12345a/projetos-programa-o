import java.util.Scanner;

public class somas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        BSTree<Integer> tree = new BSTree<>();

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            tree.insert(nums[i]);
        }

        int P = sc.nextInt();
        int[] queries = new int[P];
        for (int i = 0; i < P; i++) {
            queries[i] = sc.nextInt();
        }

        for (int i = 0; i < P; i++) {
            int X = queries[i];
            boolean found = false;

            for (int j = 0; j < N; j++) {
                int a = nums[j];
                int b = X - a;
                if (tree.contains(b)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println(X + ": sim");
            } else {
                System.out.println(X + ": nao");
            }
        }

        sc.close();
    }
}
