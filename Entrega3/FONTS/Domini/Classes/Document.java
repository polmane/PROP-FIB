package Domini.Classes;

import java.util.HashMap;

/**
 * Representa un document
 * @author pol.camprubi.prats
 */
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
     * Representa la quantitat de vegades que la paraula surt en el document
     */
    public HashMap<String,Integer> ocurrencies;
    /**
     * Representa el tfMap del document
     */
    public HashMap<String, Double> tfMap;

    /**
     * Constructora del document sense contingut
     * @param idDoc id del document
     * @param autor autor del document
     * @param titol titol del document
     */
    public Document(int idDoc, String autor, String titol) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = "";
        this.ocurrencies = new HashMap<>();
        this.tfMap = new HashMap<>();
    }

    /**
     * Constructora del document amb els camps complets
     * @param idDoc id del document
     * @param autor autor del document
     * @param titol titol del document
     * @param contingut contingut del document
     */
    public Document(int idDoc, String autor, String titol, String contingut) {
        this.idDoc = idDoc;
        this.autor = autor;
        this.titol = titol;
        this.contingut = contingut;
        this.ocurrencies = new HashMap<>();
    }

    /**
     *Getter de IdDoc
     * @return retorna la id del document
     */
    public int getIdDoc() {
        return this.idDoc;
    }


    /**
     * Getter de l'autor
     * @return retorna l'autor del document
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Setter de l'autor
     * @param autor nou valor de la variable autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Getter del títol
     * @return retorna el títol del document
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Setter del titol
     * @param titol nou valor de la variable titol
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * Getter del contingut
     * @return retorna el contingut del document
     */
    public String getContingut() {
        return contingut;
    }

    /**
     * Setter del contingut
     * @param contingut nou valor de la variable contingut
     */
    public void setContingut(String contingut) {
        this.contingut = contingut;
    }

    /**
     * Getter de les ocurrencies
     * @return retorna les ocurrencies
     */
    public HashMap<String, Integer> getOcurrencies() {
        return ocurrencies;
    }

    /**
     * Setter de les ocurrencies
     * @param ocurrencies nou valor de la variable ocurrencies
     */
    public void setOcurrencies(HashMap<String, Integer> ocurrencies) {
        this.ocurrencies = ocurrencies;
    }

    /**
     * Setter del tfMao
     * @param tfMap nou valor de la variable tfMap
     */
    public void setTfMap(HashMap<String, Double> tfMap) {
        this.tfMap = tfMap;
    }

    /**
     * Getter del tfMap
     * @return retorna el tfMap
     */

    public HashMap<String, Double> getTfMap() {
        return tfMap;
    }
}
