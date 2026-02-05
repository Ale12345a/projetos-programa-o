import reader.*;
import algs.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void GreetCustomer() {
        System.out.println("Choose your file:");
        System.out.println("\t1: Restaurant");
        System.out.println("\t2: Weather");
        System.out.println("\t3: Iris");
        System.out.println("");
    }

    public static String GetFilenameFromCustomer(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (args.length == 2) return args[0];

        if (args.length != 1) {
            System.out.println("Wrong usage. Please read the README file");
            System.exit(0);
        }

        GreetCustomer();
        int escolha = sc.nextInt();
        switch (escolha) {
            case 1: return "data/restaurant.csv";
            case 2: return "data/weather.csv";
            case 3: return "data/iris.csv";
            default:
                System.out.println("Stop messing with me!");
                System.exit(1);
        }
        return "";
    }

    public static void PrintList(LinkedList<String> l) {
        for (String asd : l) {
            System.out.println(asd);
        }
    }

    public static String GetInputFile(String[] args) {
        if (args.length == 1) return args[0];
        if (args.length == 2) return args[1];
        else {
            System.out.println("Something went horribly wrong.");
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) {
        String filename = GetFilenameFromCustomer(args);
        LinkedList<LinkedList<String>> records = CSVReader.ReadFile(filename);

        if (records == null || records.isEmpty()) {
            System.out.println("Error: could not read training file.");
            System.exit(1);
        }

        LinkedList<String> atributos = new LinkedList<>(records.get(0));
        atributos.removeFirst();  // remove primeira coluna
        atributos.removeLast();   // remove última coluna

        Algs.ID3(records, records.size() - 1, atributos);
        Algs.PrintTree();

        String inputfilename = GetInputFile(args);
        LinkedList<LinkedList<String>> testdata = CSVReader.ReadFile(inputfilename);

        if (testdata == null || testdata.isEmpty()) {
            System.out.println("Error: could not read test file.");
            System.exit(1);
        }

        LinkedList<String> attributesTest = new LinkedList<>(testdata.get(0));
        attributesTest.removeFirst();

        LinkedList<String> results = Algs.Decide(testdata);

        PrintList(results);
    }
}
