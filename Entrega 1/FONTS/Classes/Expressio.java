package FONTS.Classes;

public class Expressio {
    /**
     * Representa el número identificador d'una expressió
     */
    private int idExp;

    private String expressio;

    /**
     *Constructores
     */

    public Expressio() {
        this.idExp = null;
        this.expressio = null;
    }
    public Expressio(int idExp) {
        this.idExp = idExp;
        this.expressio = null;
    }

    public Expressio(int idExp, String expressio) {
        this.idExp = idExp;
        this.expressio = expressio;
    }

    /**
     *Getter de idExp
     */
    public int getIdExp() {
        return idExp;
    }

    /**
     * Getter i setter de expressio
     */
    public String getExpressio() {
        return expressio;
    }

    public void setExpressio(String expressio) {
        this.expressio = expressio;
    }
}
