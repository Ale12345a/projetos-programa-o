package algs;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.HashMap;

/* Classe Intervalo */
class Interval {
    int a, b;

    Interval(int m, int M) {
        a = m;
        b = M;
    }

    boolean Contains(int v) {
        return (v >= a && v < b);
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "[";
    }
}

/* Nó da árvore */
class Node {
    LinkedList<Node> childs;
    Boolean val;
    String attribute, attributeVi;
    int id;

    Node() {
        attribute = "";
        attributeVi = "";
        val = null;
        childs = new LinkedList<Node>();
    }

    Node(String attr, String vi) {
        attribute = attr;
        attributeVi = vi;
        val = null;
        childs = new LinkedList<Node>();
    }

    void AddChild(Node child) {
        childs.add(child);
    }

    int ChildNumber() {
        return childs.size();
    }

    void Print() {
        System.out.print("(");
        System.out.print("Number of children: " + ChildNumber());
        if (attribute != null && !attribute.isEmpty())
            System.out.print(" | Attribute-vi: " + attribute + "-" + attributeVi + " ");
        if (val != null)
            System.out.print("| Val: " + (val ? "Yes" : "No") + " ");
        System.out.print(" )");
    }
}

/* Classe principal */
public class Algs {

    static HashMap<String, Integer> indexAttributes;
    static Node tree;

    public static double log2(double N) {
        double result = (Math.log(N) / Math.log(2));
        return result;
    }

    /* Entropia para duas classes (yes/no) */
    private static double Entropia(int x, int y) {
        if (x == 0 || y == 0)
            return 0.0;
        double total = (double) (x + y);
        double px = x / total;
        double py = y / total;
        return -px * log2(px) - py * log2(py);
    }

    /* Entropia da coluna de decisão (última coluna) */
    private static double EntropyLast(LinkedList<LinkedList<String>> examples) {
        int nYes = 0, nNo = 0;
        if (examples.size() <= 1)
            return 0.0;
        int lastCol = examples.get(0).size() - 1;
        for (int i = 1; i < examples.size(); i++) {
            if (examples.get(i).get(lastCol).toLowerCase().equals("yes")) {
                nYes++;
            } else {
                nNo++;
            }
        }
        double nTotal = nYes + nNo;
        if (nYes == 0 || nNo == 0)
            return 0.0;
        return - (nYes / nTotal) * log2(nYes / nTotal) - (nNo / nTotal) * log2(nNo / nTotal);
    }

    /* Conjunto de valores de um atributo (col index, 0-based) */
    public static Set<String> GetAttributeSet(LinkedList<LinkedList<String>> examples, int col) {
        Set<String> vs = new HashSet<>();
        for (int i = 1; i < examples.size(); i++) {
            String entry = examples.get(i).get(col);
            vs.add(entry);
        }
        return vs;
    }

    /* Calcula o ganho de informação para cada atributo listado em attributes.
       Espera que indexAttributes mapeie nomes de atributos para colunas (0-based). */
    private static Map<String, Double> CalculateEntropy(LinkedList<LinkedList<String>> examples, LinkedList<String> attributes) {
        Map<String, Double> m = new HashMap<String, Double>();
        double decisionEntropy = EntropyLast(examples);
        int lastCol = examples.get(0).size() - 1;

        for (int a = 0; a < attributes.size(); a++) {
            String at = attributes.get(a);
            int col = indexAttributes.get(at); // agora usamos mapeamento real
            Set<String> vs = GetAttributeSet(examples, col);
            double totalEntr = 0.0;
            for (String v : vs) {
                int nYes = 0;
                int nNo = 0;
                for (int i = 1; i < examples.size(); i++) {
                    String entry = examples.get(i).get(col);
                    if (entry.equals(v)) {
                        if (examples.get(i).get(lastCol).toLowerCase().equals("yes")) {
                            nYes++;
                        } else {
                            nNo++;
                        }
                    }
                }
                totalEntr += ((nYes + nNo) / (double) (examples.size() - 1)) * Entropia(nYes, nNo);
            }
            m.put(at, decisionEntropy - totalEntr);
        }
        return m;
    }

    /* Ordena a lista attributes por ganho de informação (descendente) */
    private static void SortByEntropy(LinkedList<LinkedList<String>> examples, LinkedList<String> attributes) {
        Map<String, Double> entr = CalculateEntropy(examples, attributes);
        Collections.sort(attributes, new Comparator<String>() {
            public int compare(String s1, String s2) {
                Double v1 = entr.get(s1);
                Double v2 = entr.get(s2);
                if (v1 == null)
                    v1 = 0.0;
                if (v2 == null)
                    v2 = 0.0;
                return Double.compare(v2, v1); // descendente
            }
        });
    }

    /* Popula o mapa de índices (nomes -> col index 0-based) usando a primeira linha como header */
    public static void PopulateMap(LinkedList<LinkedList<String>> examples) {
        indexAttributes = new HashMap<String, Integer>();
        LinkedList<String> header = examples.get(0);
        for (int i = 0; i < header.size(); i++) {
            indexAttributes.put(header.get(i), i);
        }
    }

    /* Retorna atributos cujos valores são inteiros em todas as linhas (exceto header) */
    public static LinkedList<String> GetIntAttributes(LinkedList<LinkedList<String>> examples) {
        LinkedList<String> intAttributes = new LinkedList<String>();
        LinkedList<String> attributes = examples.get(0);

        for (String att : attributes) {
            int col = indexAttributes.get(att);
            boolean isInt = true;

            for (int r = 1; r < examples.size(); r++) {
                String entry = examples.get(r).get(col);
                if (entry == null || entry.isEmpty()) {
                    isInt = false;
                    break;
                }
                for (int i = 0; i < entry.length(); i++) {
                    char l = entry.charAt(i);
                    if (!Character.isDigit(l) && !(i == 0 && l == '-')) {
                        isInt = false;
                        break;
                    }
                }
                if (!isInt)
                    break;
            }
            if (isInt)
                intAttributes.add(att);
        }
        return intAttributes;
    }

    /* Obtém min e max (inclusive) de uma coluna assumindo valores inteiros (col 0-based) */
    public static LinkedList<Integer> GetMinAndMax(LinkedList<LinkedList<String>> examples, int col) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int r = 1; r < examples.size(); r++) {
            String entry = examples.get(r).get(col);
            int num = Integer.decode(entry);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return new LinkedList<Integer>(Arrays.asList(min, max));
    }

    /* Cria 4 intervalos (quartis aproximados) */
    public static LinkedList<Interval> GetIntervals(LinkedList<LinkedList<String>> examples, int col) {
        LinkedList<Integer> min_max = GetMinAndMax(examples, col);

        int min = min_max.get(0), max = min_max.get(1);
        int len = max - min + 1;
        int q1 = min + len / 4;
        int q2 = min + len / 2;
        int q3 = q2 + len / 4;

        Interval i1 = new Interval(min, q1);
        Interval i2 = new Interval(q1, q2);
        Interval i3 = new Interval(q2, q3);
        Interval i4 = new Interval(q3, max + 1);
        return new LinkedList<Interval>(Arrays.asList(i1, i2, i3, i4));
    }

    /* Normaliza atributos inteiros transformando-os em intervalos (string) */
    public static void Normalize(LinkedList<LinkedList<String>> examples) {
        LinkedList<String> intAttributes = GetIntAttributes(examples);
        for (String att : intAttributes) {
            int col = indexAttributes.get(att);

            LinkedList<Interval> intervalList = GetIntervals(examples, col);

            for (int r = 1; r < examples.size(); r++) {
                String entry = examples.get(r).get(col);
                int num = Integer.decode(entry);
                for (Interval i : intervalList) {
                    if (i.Contains(num)) {
                        examples.get(r).set(col, i.toString());
                        break;
                    }
                }
            }
        }
    }

    /* ID3 público: prepara e chama auxiliar */
    public static void ID3(LinkedList<LinkedList<String>> examples, int target_attribute_index, LinkedList<String> attributes) {
        tree = new Node();
        PopulateMap(examples);
        Normalize(examples);
        // clonamos attributes porque ID3_aux usa poll()
        LinkedList<String> attrsCopy = new LinkedList<String>(attributes);
        ID3_aux(examples, target_attribute_index, attrsCopy, tree);
    }

    /* Subconjunto de exemplos onde coluna col tem valor vi (col 0-based) */
    public static LinkedList<LinkedList<String>> SubSet(LinkedList<LinkedList<String>> examples, String vi, int col) {
        LinkedList<LinkedList<String>> subsetvi = new LinkedList<LinkedList<String>>();
        LinkedList<String> firstRow = new LinkedList<String>();
        for (int i = 0; i < examples.get(0).size(); i++) {
            firstRow.add(examples.get(0).get(i));
        }
        subsetvi.add(firstRow);
        for (int i = 1; i < examples.size(); i++) {
            if (!examples.get(i).get(col).equals(vi)) {
                continue;
            }
            LinkedList<String> row = new LinkedList<String>();
            for (int j = 0; j < examples.get(i).size(); j++) {
                row.add(examples.get(i).get(j));
            }
            subsetvi.add(row);
        }

        return subsetvi;
    }

    /* Função auxiliar recursiva do ID3 */
    public static void ID3_aux(LinkedList<LinkedList<String>> examples, int target_attribute_index, LinkedList<String> attributes, Node curr) {
        if (examples == null || examples.size() <= 1) {
            curr.val = false;
            return;
        }

        SortByEntropy(examples, attributes);

        int nYes = 0, nNo = 0;
        int lastCol = examples.get(0).size() - 1;
        for (int i = 1; i < examples.size(); i++) {
            if (examples.get(i).get(lastCol).toLowerCase().equals("yes")) {
                nYes++;
            } else {
                nNo++;
            }
        }

        if (attributes.size() == 0) {
            curr.val = (nYes > nNo);
            return;
        }

        if (nNo == 0) {
            curr.val = true;
            return;
        }

        if (nYes == 0) {
            curr.val = false;
            return;
        }

        String bestAttribute = attributes.poll(); // remove primeira, já ordenada por ganho
        int col = indexAttributes.get(bestAttribute);

        Set<String> possibleVals = GetAttributeSet(examples, col);

        for (String vi : possibleVals) {
            Node n = new Node(bestAttribute, vi);

            LinkedList<LinkedList<String>> examplesVi = SubSet(examples, vi, col);

            if (examplesVi.size() <= 1) {
                // subconjunto vazio (ou só header) -> folha com maioria
                if (nYes > nNo) {
                    n.val = true;
                } else {
                    n.val = false;
                }
                curr.AddChild(n);
            } else {
                curr.AddChild(n);
                // para a recursão criamos uma cópia dos atributos sem o bestAttribute
                LinkedList<String> attrsCopy = new LinkedList<String>(attributes);
                ID3_aux(examplesVi, target_attribute_index, attrsCopy, n);
            }
        }
        return;
    }

    /* Interpreta um intervalo string do formato "[min,max[" e testa se value pertence (value é número em string) */
    public static boolean isInInterval(String inter, String value) {
        // inter ex: "[1,5["
        int min, max;
        String[] values = inter.split(",");
        min = Integer.parseInt(values[0].substring(1));
        int len = values[1].length();
        max = Integer.parseInt(values[1].substring(0, len - 1));
        int v = Integer.parseInt(value);

        return (min <= v && v < max);
    }

    /* Percorre a árvore para uma única entrada (entry) e devolve "Yes" ou "No" */
    public static String GoWildOnTree(LinkedList<String> entry, LinkedList<String> intAttributes) {
        LinkedList<Node> nexts = new LinkedList<Node>();
        for (Node child : tree.childs)
            nexts.add(child);
        Node result = tree;
        while (!nexts.isEmpty()) {
            String attribute = nexts.get(0).attribute;
            int col = indexAttributes.get(attribute);
            String vi = entry.get(col);
            boolean found = false;

            for (Node n : nexts) {
                if (intAttributes.contains(attribute)) {
                    if (isInInterval(n.attributeVi, vi)) {
                        nexts = new LinkedList<Node>();
                        for (Node child : n.childs)
                            nexts.add(child);
                        result = n;
                        found = true;
                        break;
                    }
                } else {
                    if (n.attributeVi.equals(vi)) {
                        nexts = new LinkedList<Node>();
                        for (Node child : n.childs)
                            nexts.add(child);
                        result = n;
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Wrong attribute match for attribute '" + attribute + "' and value '" + vi + "'.");
                // em vez de exit forçoso, devolvemos a classificação da última nodal conhecida
                return (result.val != null && result.val) ? "Yes" : "No";
            }
        }
        return (result.val != null && result.val) ? "Yes" : "No";
    }

    /* Classifica o conjunto de teste (testdata), esperando que a primeira coluna seja um identificador para output */
    public static LinkedList<String> Decide(LinkedList<LinkedList<String>> testdata) {
        LinkedList<String> results = new LinkedList<String>();
        for (int r = 1; r < testdata.size(); r++) {
            LinkedList<String> entry = testdata.get(r);
            LinkedList<String> intAttributes = GetIntAttributes(testdata);
            String yesorno = GoWildOnTree(entry, intAttributes);
            results.add(entry.get(0) + ": " + yesorno);
        }

        return results;
    }

    /* Imprime árvore por níveis */
    public static void PrintTree() {

        System.out.println("Tree --------------\n");
        Queue<Node> q = new LinkedList<Node>();
        Queue<Node> nextq = new LinkedList<Node>();
        q.add(tree);
        int d = 0;
        while (!q.isEmpty()) {
            System.out.println("Depth " + d);
            d++;
            while (!q.isEmpty()) {
                Node curr = q.poll();

                curr.Print();
                for (Node child : curr.childs)
                    nextq.add(child);
                System.out.println();

            }
            System.out.println();
            q = new LinkedList<Node>(nextq);
            nextq = new LinkedList<Node>();
        }
        System.out.println("-------------------\n");

    }
}
