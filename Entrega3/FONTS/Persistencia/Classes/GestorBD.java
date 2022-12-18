package Persistencia.Classes;


import Domini.Classes.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class GestorBD {

    public enum FILETYPE {
        TXT, XML, PROP
    }
    private final String path = System.getProperty("user.dir") + "/" + "directori";

    static class Estat implements java.io.Serializable {
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

    public Boolean guardarContingutDocument (int idDoc, String contingut) {
        File directori = new File (path);
        if (!directori.exists()) {
            directori.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(path + "/" + String.valueOf(idDoc) + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contingut);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean guardarEstat (int idDir, HashMap<Integer, HashMap<String,Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String,String>> docs) {
        File directori = new File (path);
        if (!directori.exists()) {
            directori.mkdir();
        }
        try {
            Estat estat = new Estat(idDir,pesosDocs,deletedIds,idNouDoc,docs);
            FileOutputStream file = new FileOutputStream(path + "/" + "estat.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(estat);

            out.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminarDocument (int idDoc) {
        File document = new File (path + "/" + String.valueOf(idDoc) + ".txt");
        if (document.delete()) {
            return true;
        }
        else return false;
    }
}
