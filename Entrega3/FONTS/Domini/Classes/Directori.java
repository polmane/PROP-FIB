package Domini.Classes;

import java.util.*;

/**
 * Representa un directori
 * @author pol.camprubi.prats
 * @author juli.serra.balaguer
 */
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
    /**
     * Representa les paraules que tenim en el directori
     */
    public HashMap<String, Integer> paraulesDirectori;

    /**
     * Constructora de directori
     * @param idDir identificador del directori
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
     * @return retorna la id del directori
     */
    public int getIdDir() {
        return idDir;
    }

    /**
     * Getter de matriu de pesos
     * @return retorna els pesos dels documents
     */
    public HashMap<Integer, HashMap<String, Double>> getPesosDocs() {
        return pesosDocs;
    }

    /**
     * Setter dels pesos
     * @param pesosDocs nou valor de la variable pesosDocs
     */
    public void setPesosDocs(HashMap<Integer, HashMap<String, Double>> pesosDocs) {this.pesosDocs = pesosDocs;}

    /**
     * Getter cua de ids eliminades
     * @return retorna la cua de ids eliminades
     */
    public PriorityQueue<Integer> getDeletedIds() {
        return deletedIds;
    }

    /**
     * Setter de les ids
     * @param deletedIds nou valor de la variable deletedIds
     */
    public void setDeletedIds(PriorityQueue<Integer> deletedIds) {this.deletedIds = deletedIds;}

    /**
     * Getter documents que tenim dins el directori
     * @return retorna els docs
     */
    public HashMap<Integer, Document> getDocs() {
        return docs;
    }

    /**
     * Getter identificador del seguent document
     * @return retorna la id del nou document
     */
    public int getIdNouDoc() {
        return idNouDoc;
    }

    /**
     * Setter de la id del nou document
     * @param idNouDoc nou valor de la variable idNouDoc
     */
    public void setIdNouDoc(int idNouDoc) {
        this.idNouDoc = idNouDoc;
    }

    /**
     * Getter de les paraules del directori
     * @return retorna les paraules del directori
     */
    public HashMap<String, Integer> getParaulesDirectori() {
        return paraulesDirectori;
    }
}
