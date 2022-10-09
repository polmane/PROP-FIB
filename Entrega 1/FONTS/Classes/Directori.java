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
     * Representa l'identificador més gran que s'ha donat mai a un document
     */
    private int maxIdDoc;

    /**
     * Representa els documents que nosaltres hem creat dins el sistema
     */
    public HashMap<Integer,Document> docs;

    /**
     * Representa les expressions que nosaltres hem creat dins el sistema
     */
    public HashMap<Integer, Expressio> expressions;

    /**
     *Constructora
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
     *Getter documents que tenim dins el directori
     */
    public HashMap<Integer, Document> getDocs() {
        return docs;
    }

    /**
     *Getter expressions que tenim dins el directori
     */
    public HashMap<Integer, Expressio> getExpressions() {
        return expressions;
    }

    /**
     *Getter de idNouDoc
     */
    public int getIdNouDoc() {
        return idNouDoc;
    }

    /**
     * Guarda un nou document al directori
     * @param document és el document que es vol guardar al directori
     */
    public void guardarDocument(@NotNull Document document) {

        //Comprovem que no tenim cap document amb el mateix autor i titol
        String autor = document.getAutor();
        String titol = document.getTitol();
        Set<Map.Entry<Integer, Document>> setDocuments = docs.entrySet();
        for (Map.Entry<Integer, Document> it_doc : setDocuments) {
            Document doc = it_doc.getValue();
            if (doc.getAutor().equals(autor) && doc.getTitol().equals(titol)) {
                System.err.println("ERROR: Ja existeix un document amb autor: " + autor + " i titol: " + titol + ".");
                return;
            }
        }

        //Aquesta comparació és fumada?? Com garantim que l'identificador de la
        // classe Document i l'índex que ens és útil (idNouDoc) siguin el mateix?
        // No passa res si no ho són??
        int idDoc = document.getIdDoc();
        if (idDoc != idNouDoc) {
            System.err.println("ERROR: La id del document no és l'esperada pel directori");
            return;
        }

        docs.put(idNouDoc, document);

        //Calculem la seguent id per al proper document
        if (!deletedIds.isEmpty()) {
            idNouDoc = deletedIds.poll();
        } else {
            ++maxIdDoc;
            idNouDoc = maxIdDoc;
        }
    }

}
