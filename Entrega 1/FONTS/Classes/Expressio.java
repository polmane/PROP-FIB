package FONTS.Classes;

public class Expressio {
    /**
     * Representa el número identificador d'una expressió
     */
    private Integer idExp;

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
    public int getIdEXp() {
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



}
