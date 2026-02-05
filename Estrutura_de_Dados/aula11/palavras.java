import java.util.Scanner;

public class palavras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BSTree<String> tree = new BSTree<String>();

        for (int i = 0; i < N; i++) {
            String word = sc.next();
            tree.insert(word);
        }

        System.out.println(tree.numberNodes());
        sc.close();
    }
}