package Persistencia.Classes;


import Domini.Classes.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Representa un gestor de Base de Dades
 * @author pol.camprubi.prats
 * @author juli.serra.balaguer
 */
public class GestorBD {
    /**
     * Representa el path on es troba la base de dades
     */
    private final String BD_PATH = System.getProperty("user.dir") + "/directori";

    /**
     * Representa una classe per guardar la informació a la base de dades
     */
    public static class Estat implements java.io.Serializable {
        /**
         * Representa el número identificador d'un directori
         */
        public int idDir;
        /**
         * Representa la taula de pesos del nostre sistema
         */
        public HashMap<Integer, HashMap<String,Double>> pesosDocs;
        /**
         * Representa les ids dels documents eliminats
         */
        public PriorityQueue<Integer> deletedIds;
        /**
         * Representa un contador per atribuir la id al document corresponent
         */
        public int idNouDoc;
        /**
         * Representa els documents que nosaltres hem creat dins el sistema
         */
        public HashMap<Integer, Pair<String,String>> docs;

        /**
         * Constructora de l'Estat
         * @param idDir id del directori
         * @param pesosDocs matriu de pesos
         * @param deletedIds cua de ids
         * @param idNouDoc id del nou document
         * @param docs documents dins el nostre sistema
         */
        public Estat(int idDir, HashMap<Integer, HashMap<String, Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String, String>> docs) {
            this.idDir = idDir;
            this.pesosDocs = pesosDocs;
            this.deletedIds = deletedIds;
            this.idNouDoc = idNouDoc;
            this.docs = docs;
        }
    }

    /**
     * Funció que permet guardar el contingut d'un document a la base de dades
     * @param idDoc id del document a guardar la informació
     * @param contingut contingut a guardar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarContingutDocument(int idDoc, String contingut) {
        File directori = new File (BD_PATH);
        if (!directori.exists()) {
            directori.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(BD_PATH + "/" + String.valueOf(idDoc) + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            if (contingut != null) {
                bw.write(contingut);
            }
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Funció que permet carregar el contingut d'un document
     * @param idDoc id del document a carregar el contingut
     * @return retorna el contingut del docuement en cas de funcionament correcte, null en cas contrari
     */
    public String carregarContingutDocument(int idDoc){
        File file = new File(BD_PATH + "/" + String.valueOf(idDoc) + ".txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder result = new StringBuilder();
            String helper;
            while ((helper = br.readLine()) != null) result.append(helper);
            br.close();
            fileReader.close();
            return result.toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Funció per eliminar el document de la base de dades
     * @param idDoc identificador del document a eliminar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public boolean eliminarDocument(int idDoc) {
        File document = new File (BD_PATH + "/" + String.valueOf(idDoc) + ".txt");
        return document.delete();
    }

    /**
     * Funció que permet guardar l'estat del programa a la base de dades
     * @param idDir id del directori
     * @param pesosDocs matriu de pesos
     * @param deletedIds cua de ids
     * @param idNouDoc id del nou docuent
     * @param docs documents del nostre sistema
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarEstat(int idDir, HashMap<Integer, HashMap<String,Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String,String>> docs) {
        File directori = new File (BD_PATH);
        if (!directori.exists()) {
            directori.mkdir();
        }
        try {
            Estat estat = new Estat(idDir,pesosDocs,deletedIds,idNouDoc,docs);
            FileOutputStream file = new FileOutputStream(BD_PATH + "/estat.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(estat);

            out.close();
            file.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Funció que permet carregar l'estat del programa a MP
     * @return retorna un object Estat en cas de funcionament correcte, null en cas contrari
     */
    public Estat carregarEstat() {
        Estat estatObject;
        try {
            FileInputStream estatFile = new FileInputStream(BD_PATH + "/estat.txt");
            ObjectInputStream estatFileStream = new ObjectInputStream(estatFile);

            estatObject = (Estat)estatFileStream.readObject();

            estatFileStream.close();
            estatFile.close();
        }
        catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return estatObject;
    }

}
