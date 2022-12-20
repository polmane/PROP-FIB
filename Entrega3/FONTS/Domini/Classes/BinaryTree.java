package Domini.Classes;

import static java.lang.Character.isWhitespace;

/**
 * Representa un arbre binari
 * @author pol.camprubi.prats
 */
public class BinaryTree {
    /**
     * Representa un node
     * @author pol.camprubi.prats
     */
    static class Node {
        /**
         * Representa l'expressió
         */
        String expr;
        /**
         * Representa el node esquerra del node arrel
         */
        Node left;
        /**
         * Representa el node dret del node arrel
         */
        Node right;
        /**
         * Representa un iterador que passa sobre la variable expr
         */
        int pos = 0;

        /**
         * Constructora d'un node
         * @param expr expressió a avaluar
         */
        public Node(String expr) {
            this.expr = expr;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Representa el node arrel
     */
    public Node root;

    /**
     * Constructora del BinaryTree
     * @param exp expressió a avaluar
     */
    public BinaryTree(String exp) {
        exp = exp.toLowerCase();
        Node n = new Node(exp);
        this.root = addRecursive(n,exp);
    }

    /**
     * Funció per crear l'arbre binari de manera recuriva
     * @param current node actual en el que s'està treballant
     * @param exp expressió que té el node actual
     * @return retorna el nou node actual
     */
    private Node addRecursive(Node current, String exp) {
        if (current == null) current = new Node(exp);
        String first_part = "";
        String second_part = "";
        exp = eliminaEspais(exp);
        if (exp.charAt(0) == '(' && exp.charAt(exp.length()-1) == ')') {
            int currentPar = 1;     //quantitat parentesis sense tancar
            int help = current.pos;
            ++help;
            boolean correcte = true;
            for (int i = help; i < exp.length()-1; ++i) {
                if (exp.charAt(i) == '(') ++currentPar;
                if (exp.charAt(i) == ')') {
                    --currentPar;
                    if (currentPar == 0) {  //si en algun moment tanquem el primer parentesis, break i no correcte
                        correcte = false;
                        break;
                    }
                }
            }
            if (correcte) {
                exp = exp.substring(1, exp.length() - 1);
                current.expr = exp;
            }
        }
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

    /**
     * Funció que elimina els possibles espais que l'usuari hagi pogut posar a l'expressió
     * @param exp expressió a avalar
     * @return retorna l'expressió d'entrada sense espais en l'inici i final
     */
    private String eliminaEspais(String exp) {
        int inici = 0;
        int end = exp.length()-1;
        while (isWhitespace(exp.charAt(inici)))++inici;
        while (isWhitespace(exp.charAt(end)))--end;
        if (inici != 0 || end != exp.length()-1) exp = exp.substring(inici, end+1);
        return exp;
    }

    /**
     * Funció que parteix l'expressió en dos i l'assigna al node esquerra i dret la part corresponent
     * @param current node actual
     * @param exp expressió a avaluar
     */
    private void extracted(Node current, String exp) {
        String second_part;
        String first_part;
        first_part = exp.substring(0, current.pos-1);
        current.left = addRecursive(current.left, first_part);
        second_part = exp.substring(current.pos+2, exp.length());
        current.right = addRecursive(current.right, second_part);
    }

    /**
     * Funció que avalua per prioritats (parèntesis, ands i ors) si hi ha algun operador a l'expressió
     * @param exp expressió a avaluar
     * @param op operació que es busca
     * @param current node actaul
     * @return true en cas de trobar operant, false en cas contrari
     */
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

    /**
     * Funció que busca si hi han Ands
     * @param exp expressió a avaluar
     * @param current node actaul
     * @return true en cas de trobar operant, false en cas contrari
     */

    private boolean hiHaAnd(String exp, Node current) {
        return hiHaOperador(exp,'&', current);
    }
    /**
     * Funció que busca si hi han Ors
     * @param exp expressió a avaluar
     * @param current node actaul
     * @return true en cas de trobar operant, false en cas contrari
     */
    private boolean hiHaOr(String exp, Node current) {
        return hiHaOperador(exp,'|', current);
    }

    /**
     * Funció que evalua l'arbre
     * @param root node actual
     * @param d document que s'utilitza per fer la comparació amb l'expressió
     * @return 0 en cas de no complir l'expressió, 1 en cas contrari
     */
    public static int evalTree(Node root, Document d) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return evalLeaf(root, d);

        int leftEval = evalTree(root.left, d);

        int rightEval = evalTree(root.right, d);

        if (root.expr.equals("+")) return leftEval + rightEval;

        return leftEval * rightEval;
    }

    /**
     * Funció que evalua una fulla de l'arbre
     * @param root node actual
     * @param d document que s'utilitza per fer la comparació amb l'expressió
     * @return 0 en cas de no complir l'expressió, 1 en cas contrari
     */
    private static int evalLeaf(Node root, Document d) {
        int pos = 0;
        switch (root.expr.charAt(pos)) {
            case '{':
                ++pos;
                while (pos < root.expr.length() && root.expr.charAt(pos) != '}') {
                    int start = pos;
                    while (pos < root.expr.length() && !isWhitespace(root.expr.charAt(pos)) && root.expr.charAt(pos) != '}') ++pos;
                    String word = root.expr.substring(start, pos);
                    if (!d.getOcurrencies().containsKey(word)) {
                        return 0;
                    }
                    ++pos;
                }
                return 1;

            case '!':
                ++pos;
                if (root.expr.charAt(pos) == '(') root.expr = root.expr.substring(2, root.expr.length()-1);
                else root.expr = root.expr.substring(1, root.expr.length());
                Integer result = evalLeaf(root, d);
                if (result != 0) return 0;
                return 1;

            case '"':
                ++pos;
                int start2 = pos;
                while (pos < root.expr.length() && root.expr.charAt(pos) != '"') ++pos;
                String word = root.expr.substring(start2, pos);
                ++pos;
                return buscaEnContingut(word, d.getContingut());

            default:
                int start3 = pos;
                while (pos < root.expr.length() && !isWhitespace(root.expr.charAt(pos))) ++pos;
                String word2 = root.expr.substring(start3, pos);
                ++pos;
                if (d.getOcurrencies().containsKey(word2)) return 1;
                return 0;
        }
    }

    /**
     * Funció que busca si la variable word es troba en la variable contingut
     * @param word string a buscar
     * @param contingut string on es busca la variable word
     * @return 0 en cas negatiu, 1 en cas contrari
     */
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
