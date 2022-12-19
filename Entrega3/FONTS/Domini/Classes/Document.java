package Domini.Classes;

import java.util.HashMap;

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

    public HashMap<String,Integer> ocurrencies;

    public HashMap<String, Double> tfMap;

    /**
     *Constructores
     */
    public Document(int idDoc, String autor, String titol) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = null;
        this.ocurrencies = new HashMap<>();
        this.tfMap = new HashMap<>();
    }
    public Document(int idDoc, String autor, String titol, String contingut) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = contingut;
        this.ocurrencies = new HashMap<>();
    }
    public Document(int idDoc, String autor, String titol, HashMap<String,Integer> ocurrencies) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = null;
        this.ocurrencies = ocurrencies;
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

    public HashMap<String, Integer> getOcurrencies() {
        return ocurrencies;
    }

    public void setOcurrencies(HashMap<String, Integer> ocurrencies) {
        this.ocurrencies = ocurrencies;
    }

    public void setTfMap(HashMap<String, Double> tfMap) {
        this.tfMap = tfMap;
    }

    public HashMap<String, Double> getTfMap() {
        return tfMap;
    }
}
