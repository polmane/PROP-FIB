package FONTS.Classes;

import java.util.*;

public class Directori {
    /**
     * Representa el número identificador d'un directori
     */
    private final int idDir;

    /**
     * Representa la taula de pesos del nostre sistema
     */
    public HashMap<Integer, HashMap<String,Double>> pesosDocs;

    /**
     * Representa les ids dels documents eliminats
     */
    public Queue<Integer> deletedIds;

    /**
     * Representa un contador per atribuir la id al document corresponent
     */
    private int idNouDoc;

    /**
     * Representa les expressions que tenim al directori
     */
    public HashMap<Integer, String> expressions;

    /**
     *Constructora
     */
    public Directori(int idDir) {
        this.idDir = idDir;
        pesosDocs = new HashMap<>();
        deletedIds = new LinkedList<>();
        idNouDoc = 0;
    }

    /**
     *Getter de idDir
     */
    public int getIdDir() {
        return idDir;
    }
    /**
     *Getter de matriu de pesos
     */
    public HashMap<Integer, HashMap<String, Double>> getPesosDocs() {
        return pesosDocs;
    }
    /**
     *Getter cua de ids eliminades
     */
    public Queue<Integer> getDeletedIds() {
        return deletedIds;
    }

    /**
     *Getter de idNouDoc
     */
    public int getIdNouDoc() {
        return idNouDoc;
    }
}
