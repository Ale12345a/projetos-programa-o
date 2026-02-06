// Codigo para o problema [DAA 022] Arvores Red-Black
// Base: Pedro Ribeiro (DCC/FCUP)

import java.util.Scanner;

// Estrutura para representar um no da arvore
class Node {
    boolean isBlack;  // No preto?
    boolean isNull;   // No nulo?
    int value;        // Valor
    Node left, right; // Filhos

    Node(int v) {
        isNull  = (v == 0);
        isBlack = (v >= 0);
        value   = Math.abs(v);
    }
}

public class redblack {

    static boolean valid;

    // Ler input em preorder
    static Node readPreOrder(Scanner in) {
        int v = in.nextInt();
        Node aux = new Node(v);
        if (!aux.isNull) {
            aux.left  = readPreOrder(in);
            aux.right = readPreOrder(in);
        }
        return aux;
    }

    // Verificar se e BST
    static boolean isBST(Node t, int min, int max) {
        if (t.isNull) return true;

        if (t.value <= min || t.value >= max)
            return false;

        return isBST(t.left, min, t.value) &&
               isBST(t.right, t.value, max);
    }

    // Propriedade: no vermelho nao pode ter filhos vermelhos
    static boolean noRedRed(Node t) {
        if (t.isNull) return true;

        if (!t.isBlack) {
            if (!t.left.isBlack || !t.right.isBlack)
                return false;
        }

        return noRedRed(t.left) && noRedRed(t.right);
    }

    // Propriedade black-height
    static int blackHeight(Node t) {
        if (t.isNull)
            return 1; // folhas nulas sao pretas

        int leftBH  = blackHeight(t.left);
        int rightBH = blackHeight(t.right);

        if (leftBH == -1 || rightBH == -1 || leftBH != rightBH)
            return -1;

        return leftBH + (t.isBlack ? 1 : 0);
    }

    // ---------------------------------------------------

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            Node root = readPreOrder(in);
            valid = true;

            // Root property
            if (!root.isBlack)
                valid = false;

            // BST property
            valid &= isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

            // Red property
            valid &= noRedRed(root);

            // Black property
            if (blackHeight(root) == -1)
                valid = false;

            if (valid) System.out.println("SIM");
            else       System.out.println("NAO");
        }

        in.close();
    }
}
