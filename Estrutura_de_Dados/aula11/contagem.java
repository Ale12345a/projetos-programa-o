import java.util.Scanner;
import java.util.LinkedList;

public class contagem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTMap<String,Integer> map = new BSTMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) continue; // ignorar linhas vazias
            String[] words = line.split(" ");
            for (String word : words) {
                Integer count = map.get(word);
                if (count == null) {
                    map.put(word, 1);
                } else {
                    map.put(word, count + 1);
                }
            }
        }

        LinkedList<String> keys = map.keys(); // já em ordem alfabética
        for (String key : keys) {
            System.out.println(key + ": " + map.get(key));
        }

        sc.close();
    }
}
