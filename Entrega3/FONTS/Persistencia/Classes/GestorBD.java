package Persistencia.Classes;


import Domini.Classes.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class GestorBD {

    private final String BD_PATH = System.getProperty("user.dir") + "/directori";

    public static class Estat implements java.io.Serializable {
        public int idDir;
        public HashMap<Integer, HashMap<String,Double>> pesosDocs;
        public PriorityQueue<Integer> deletedIds;
        public int idNouDoc;
        public HashMap<Integer, Pair<String,String>> docs;
        public Estat(int idDir, HashMap<Integer, HashMap<String, Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String, String>> docs) {
            this.idDir = idDir;
            this.pesosDocs = pesosDocs;
            this.deletedIds = deletedIds;
            this.idNouDoc = idNouDoc;
            this.docs = docs;
        }
    }

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
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

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

    public boolean eliminarDocument(int idDoc) {
        File document = new File (BD_PATH + "/" + String.valueOf(idDoc) + ".txt");
        return document.delete();
    }

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
