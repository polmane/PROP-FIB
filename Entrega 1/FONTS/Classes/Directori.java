package FONTS.Classes;

import org.jetbrains.annotations.NotNull;

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
    public Queue<Integer> deletedIds;

    /**
     * Representa un contador per atribuir la id al document corresponent
     */
    private int idNouDoc;

    /**
     * Representa l'identificador més gran que s'ha donat mai a un document
     */
    private int maxIdDoc;

    /**
     * Representa els documents que nosaltres hem creat dins el sistema
     */
    public HashMap<Integer, Document> docs;

    /**
     * Representa les expressions que nosaltres hem creat dins el sistema
     */
    public HashMap<Integer, Expressio> expressions;

    /**
     * Constructora
     */
    public Directori(int idDir) {
        this.idDir = idDir;
        pesosDocs = new HashMap<>();
        deletedIds = new LinkedList<>();
        docs = new HashMap<>();
        expressions = new HashMap<>();
        idNouDoc = 0;
        maxIdDoc = 0;
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
     * Getter cua de ids eliminades
     */
    public Queue<Integer> getDeletedIds() {
        return deletedIds;
    }

    /**
     * Getter documents que tenim dins el directori
     */
    public HashMap<Integer, Document> getDocs() {
        return docs;
    }

    /**
     * Getter expressions que tenim dins el directori
     */
    public HashMap<Integer, Expressio> getExpressions() {
        return expressions;
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

    /**
     * Getter identificador maxim que s'ha donat a un document
     */
    public int getMaxIdDoc() {
        return maxIdDoc;
    }

    /**
     * Augmenta identificador maxim que s'ha donat a un document
     */
    public void augmentaMaxIdDoc() {
        this.maxIdDoc++;
    }
}
