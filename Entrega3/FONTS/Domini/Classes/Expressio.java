package Domini.Classes;

/**
 * Representa una expressió
 * @author pol.camprubi.prats
 * @author isaac.roma.granado
 */
public class Expressio {
    /**
     * Representa el número identificador d'una expressió
     */
    private final Integer idExp;
    /**
     * Representa l'expressió
     */
    private String expressio;
    /**
     * Representa el BinaryTree de l'expressió
     */
    public BinaryTree ExpressionTree;

    /**
     * Constructora d'expressió
     * @param idExp id de l'expressió
     * @param expressio expressió
     */
    public Expressio(Integer idExp, String expressio) {
        this.idExp = idExp;
        this.expressio = expressio;
        ExpressionTree = new BinaryTree(expressio);
    }

    /**
     * Getter de la Id
     * @return retorna la id
     */
    public int getIdExp() {
        return idExp;
    }

    /**
     * Getter de l'expressió
     * @return retorna l'expressió
     */
    public String getExpressio() {
        return expressio;
    }

    /**
     * Setter de l'expressió
     * @param expressio nou valor de la variable expressió
     */
    public void setExpressio(String expressio) {
        this.expressio = expressio;
    }

    /**
     * Getter de del BinnaryTree
     * @return retorna el binaryTree de l'expressió
     */
    public BinaryTree getExpressionTree() {
        return ExpressionTree;
    }

    /**
     * Setter del BinnaryTree
     * @param expressionTree nou valor de la variable BinaryTree
     */
    public void setExpressionTree(BinaryTree expressionTree) {
        ExpressionTree = expressionTree;
    }
}
