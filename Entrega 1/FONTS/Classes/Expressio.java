package FONTS.Classes;

public class Expressio {
    /**
     * Representa el número identificador d'una expressió
     */
    private int idEXp;

    private String expressio;

    /**
     * Constructora
     */
    public Expressio(int idEXp, String expressio) {
        this.idEXp = idEXp;
        this.expressio = expressio;
    }

    /**
     * Getter de idExp
     */
    public int getIdEXp() {
        return idEXp;
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
