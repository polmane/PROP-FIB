package Classes;

public class Expressio {
    /**
     * Representa el número identificador d'una expressió
     */
    private final Integer idExp;

    private String expressio;

    public BinaryTree ExpressionTree;

    /**
     * Constructora
     */
    public Expressio(Integer idExp, String expressio) {
        this.idExp = idExp;
        this.expressio = expressio;
        ExpressionTree = new BinaryTree(expressio);
    }

    /**
     * Getter de idExp
     */
    public int getIdExp() {
        return idExp;
    }

    /**
     * Getter i setter d'expressio
     */
    public String getExpressio() {
        return expressio;
    }

    public void setExpressio(String expressio) {
        this.expressio = expressio;
    }

    public BinaryTree getExpressionTree() {
        return ExpressionTree;
    }

    public void setExpressionTree(BinaryTree expressionTree) {
        ExpressionTree = expressionTree;
    }
}
