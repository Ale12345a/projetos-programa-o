package reader;

import java.util.LinkedList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    public static LinkedList<LinkedList<String>> ReadFile(String filename) {
        LinkedList<LinkedList<String>> records = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new LinkedList<>(Arrays.asList(values)));
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace(); // imprime o erro para debug
            return null;
        }
    }
}
