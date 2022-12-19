package Domini.Classes;

import java.util.*;

public class Directori {
    /**
     * Representa el número identificador d'un directori
     */
    private final int idDir;

    /**
     * Representa la taula de pesos del nostre sistema
     */
    public HashMap<Integer, HashMap<String, Double>> pesosDocs;

    /**
     * Representa les ids dels documents eliminats
     */
    private PriorityQueue<Integer> deletedIds;

    /**
     * Representa un contador per atribuir la id al document corresponent
     */
    private int idNouDoc;


    /**
     * Representa els documents que nosaltres hem creat dins el sistema
     */
    public HashMap<Integer, Document> docs;

    public HashMap<String, Integer> paraulesDirectori;

    /**
     * Constructora
     */
    public Directori(int idDir) {
        this.idDir = idDir;
        pesosDocs = new HashMap<>();
        deletedIds = new PriorityQueue<>();
        docs = new HashMap<>();
        paraulesDirectori = new HashMap<>();
        idNouDoc = 0;
    }

    /**
     * Getter de idDir
     */
    public int getIdDir() {
        return idDir;
    }

    /**
     * Getter de matriu de pesos
     */
    public HashMap<Integer, HashMap<String, Double>> getPesosDocs() {
        return pesosDocs;
    }

    /**
     * Setter de matriu de pesos
     */
    public void setPesosDocs(HashMap<Integer, HashMap<String, Double>> pesosDocs) {this.pesosDocs = pesosDocs;}

    /**
     * Getter cua de ids eliminades
     */
    public PriorityQueue<Integer> getDeletedIds() {
        return deletedIds;
    }

    /**
     * Setter cua de ids eliminades
     */
    public void setDeletedIds(PriorityQueue<Integer> deletedIds) {this.deletedIds = deletedIds;}

    /**
     * Getter documents que tenim dins el directori
     */
    public HashMap<Integer, Document> getDocs() {
        return docs;
    }

    /**
     * Getter identificador del seguent document
     */
    public int getIdNouDoc() {
        return idNouDoc;
    }

    /**
     * Setter identificador del seguent document
     */
    public void setIdNouDoc(int idNouDoc) {
        this.idNouDoc = idNouDoc;
    }

    public HashMap<String, Integer> getParaulesDirectori() {
        return paraulesDirectori;
    }

    public void setParaulesDirectori(HashMap<String, Integer> paraulesDirectori) {
        this.paraulesDirectori = paraulesDirectori;
    }
}
