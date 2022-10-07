package FONTS.Classes;

public class Document {

    /**
     * Representa el número identificador d'un document
     */
    public int idDoc;

    /**
     * Representa l'autor d'un document
     */
    public String autor;

    /**
     * Representa el títol d'un document
     */
    public String titol;

    /**
     * Representa el contingut d'un document
     */
    public String contingut;

    /**
     *Constructores
     */
    public Document(int idDoc, String autor, String titol) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = null;
    }
    public Document(int idDoc, String autor, String titol, String contingut) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = contingut;
    }

    /**
     *Getter de IdDoc
     */
    public int getIdDoc() {
        return this.idDoc;
    }


    /**
     *Getter i setter d'autor
     */
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     *Getter i setter de titol
     */
    public String getTitol() {
        return titol;
    }
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     *Getter i setter de contingut
     */
    public String getContingut() {
        return contingut;
    }
    public void setContingut(String contingut) {
        this.contingut = contingut;
    }
}
