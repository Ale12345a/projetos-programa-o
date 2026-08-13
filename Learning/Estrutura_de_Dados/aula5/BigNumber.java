import java.util.*;

public class BigNumber {
    private ArrayList<Integer> digits; // lista de dígitos em ordem inversa (LSB primeiro)

    // Construtor: inicializa a partir de uma string
    public BigNumber(String n) {
        digits = new ArrayList<>();
        for (int i = n.length() - 1; i >= 0; i--) {
            digits.add(n.charAt(i) - '0');
        }
        normalize();
    }

    // Remove zeros à esquerda (internamente: finais da lista)
    private void normalize() {
        while (digits.size() > 1 && digits.get(digits.size() - 1) == 0) {
            digits.remove(digits.size() - 1);
        }
    }

    // equals
    public boolean equals(BigNumber n) {
        if (this.digits.size() != n.digits.size()) return false;
        for (int i = 0; i < digits.size(); i++) {
            if (!this.digits.get(i).equals(n.digits.get(i))) return false;
        }
        return true;
    }

    // toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }

    // add
    public BigNumber add(BigNumber n) {
        ArrayList<Integer> result = new ArrayList<>();
        int carry = 0;
        int maxLen = Math.max(this.digits.size(), n.digits.size());
        for (int i = 0; i < maxLen; i++) {
            int d1 = (i < this.digits.size() ? this.digits.get(i) : 0);
            int d2 = (i < n.digits.size() ? n.digits.get(i) : 0);
            int sum = d1 + d2 + carry;
            result.add(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) result.add(carry);
        return new BigNumber(listToString(result));
    }

    // multiply
    public BigNumber multiply(BigNumber n) {
        int[] result = new int[this.digits.size() + n.digits.size()];
        for (int i = 0; i < this.digits.size(); i++) {
            int carry = 0;
            for (int j = 0; j < n.digits.size(); j++) {
                int sum = result[i + j] + this.digits.get(i) * n.digits.get(j) + carry;
                result[i + j] = sum % 10;
                carry = sum / 10;
            }
            if (carry > 0) {
                result[i + n.digits.size()] += carry;
            }
        }
        ArrayList<Integer> resList = new ArrayList<>();
        for (int d : result) resList.add(d);
        BigNumber res = new BigNumber(listToString(resList));
        res.normalize();
        return res;
    }

    // utilitário: converte lista de dígitos invertidos para string
    private static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        int i = list.size() - 1;
        // ignorar zeros à esquerda
        while (i > 0 && list.get(i) == 0) i--;
        for (; i >= 0; i--) sb.append(list.get(i));
        return sb.toString();
    }
}