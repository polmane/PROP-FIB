package FONTS.Classes;

import static java.lang.Character.isWhitespace;

public class BinaryTree {
    static class Node {
        String expr;
        Node left;
        Node right;

        int pos = 0;

        public Node(String expr) {
            this.expr = expr;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public BinaryTree(String exp) {
        exp = exp.toLowerCase();
        Node n = new Node(exp);
        this.root = addRecursive(n,exp);
    }

    private Node addRecursive(Node current, String exp) {
        if (current == null) current = new Node(exp);
        String first_part = "";
        String second_part = "";
        exp = eliminaEspais(exp);
        if (exp.charAt(0) == '(' && exp.charAt(exp.length()-1) == ')') exp = exp.substring(1,exp.length()-1);
        if (hiHaOr(exp, current)) {
            extracted(current, exp);
            current.expr = "+";
        }
        else if (hiHaAnd(exp, current)) {
            extracted(current, exp);
            current.expr = "*";
        }
        return current;
    }

    private String eliminaEspais(String exp) {
        int inici = 0;
        int end = exp.length()-1;
        while (isWhitespace(exp.charAt(inici)))++inici;
        while (isWhitespace(exp.charAt(end)))--end;
        if (inici != 0 || end != exp.length()-1) exp = exp.substring(inici, end+1);
        return exp;
    }

    private void extracted(Node current, String exp) {
        String second_part;
        String first_part;
        first_part = exp.substring(0, current.pos-1);
        current.left = addRecursive(current.left, first_part);
        second_part = exp.substring(current.pos+2, exp.length());
        current.right = addRecursive(current.right, second_part);
    }

    private boolean hiHaOperador(String exp, char op, Node current) {
        current.pos = 0;
        int parentesi = 0;
        while (current.pos < exp.length()) {
            if (exp.charAt(current.pos) == '(') ++parentesi;
            else if (exp.charAt(current.pos) == ')') --parentesi;
            else if (exp.charAt(current.pos) == op && parentesi == 0) return true;
            ++current.pos;
        }
        return false;
    }

    private boolean hiHaAnd(String exp, Node current) {
        return hiHaOperador(exp,'&', current);
    }

    private boolean hiHaOr(String exp, Node current) {
        return hiHaOperador(exp,'|', current);
    }

    public static int evalTree(Node root, Document d) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return evalLeaf(root, d);

        int leftEval = evalTree(root.left, d);

        int rightEval = evalTree(root.right, d);

        if (root.expr.equals("+")) return leftEval + rightEval;

        return leftEval * rightEval;
    }

    private static int evalLeaf(Node root, Document d) {
        int pos = 0;
        switch (root.expr.charAt(pos)) {
            case '{':
                ++pos;
                while (pos < root.expr.length() && root.expr.charAt(pos) != '}') {
                    int start = pos;
                    while (pos < root.expr.length() && !isWhitespace(root.expr.charAt(pos)) && root.expr.charAt(pos) != '}') ++pos;
                    String word = root.expr.substring(start, pos);
                    if (!d.ocurrencies.containsKey(word)) {
                        while (root.expr.charAt(pos) != '}') ++pos;
                        return 0;
                    }
                    ++pos;
                }
                return 1;

            case '!':
                ++pos;
                int start = pos;
                while (pos < root.expr.length() && !isWhitespace(root.expr.charAt(pos))) ++pos;
                String word1 = root.expr.substring(start, pos);
                if (d.ocurrencies.containsKey(word1)) {
                    ++pos;
                    return 0;
                }
                ++pos;
                return 1;

            case '"':
                ++pos;
                int start2 = pos;
                while (pos < root.expr.length() && root.expr.charAt(pos) != '"') ++pos;
                String word = root.expr.substring(start2, pos);
                ++pos;
                return buscaEnContingut(word, d.contingut);

            default:
                int start3 = pos;
                while (pos < root.expr.length() && !isWhitespace(root.expr.charAt(pos))) ++pos;
                String word2 = root.expr.substring(start3, pos);
                ++pos;
                if (d.ocurrencies.containsKey(word2)) return 1;
                return 0;
        }
    }

    private static int buscaEnContingut(String word, String contingut) {
        int inici = 0;
        int end = word.length();
        while (end <= contingut.length()) {
            String wordContent = contingut.substring(inici,end);
            if (wordContent.equals(word)) return 1;
            ++inici;
            ++end;
        }
        return 0;
    }
}
