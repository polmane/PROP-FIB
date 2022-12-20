package Persistencia.Test;

import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TestGestorBD {

    private final String BD_PATH = System.getProperty("user.dir") + "/directori";

    @Test
    public void testGuardarContingutDocumentCrearDirectori() {
        File dir = new File(BD_PATH);
        Assert.assertTrue(dir.delete());

        Integer id = 0;
        String contingut = "contingut";
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, contingut));

        File f = new File(BD_PATH + "/" + String.valueOf(id) + ".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Assert.assertEquals(contingut, br.readLine());
            br.close();
            fr.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        Assert.assertTrue(f.delete());
    }

    @Test
    public void testGuardarContingutDocumentContingutNull() {
        Integer id = 0;
        String contingut = null;
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, contingut));

        File f = new File(BD_PATH + "/" + String.valueOf(id) + ".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Assert.assertEquals(contingut, br.readLine());
            br.close();
            fr.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        Assert.assertTrue(f.delete());
    }

    @Test
    public void testGuardarContingutDocumentCorrecte() {
        Integer id = 0;
        String contingut = "contingut";
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, contingut));

        File f = new File(BD_PATH + "/" + String.valueOf(id) + ".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Assert.assertEquals(contingut, br.readLine());
            br.close();
            fr.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        Assert.assertTrue(f.delete());
    }

    @Test
    public void testCarregarContingutDocument() {
        Integer id = 0;
        String contingut = "contingut";

        File f = new File(BD_PATH + "/" + String.valueOf(id) + ".txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contingut);
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorBD gBD = new GestorBD();
        Assert.assertEquals(contingut, gBD.carregarContingutDocument(id));

        Assert.assertTrue(f.delete());
    }

    @Test
    public void testEliminarDocument() {
        Integer id = 0;
        String contingut = "contingut";

        File f = new File(BD_PATH + "/" + String.valueOf(id) + ".txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contingut);
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.eliminarDocument(id));
    }

    @Test
    public void testGuardarEstatBadArgument() {
        int idDir = 0;
        HashMap<Integer, HashMap<String,Double>> pesosDocs = new HashMap<>();
        PriorityQueue<Integer> deletedIds = new PriorityQueue<>();
        int idNouDoc = 0;
        HashMap<Integer, Pair<String,String>> docs = new HashMap<>();

        GestorBD gBD = new GestorBD();
        Assert.assertFalse(gBD.guardarEstat(-1, pesosDocs, deletedIds, idNouDoc, docs));
        Assert.assertFalse(gBD.guardarEstat(idDir, null, deletedIds, idNouDoc, docs));
        Assert.assertFalse(gBD.guardarEstat(idDir, pesosDocs, null, idNouDoc, docs));
        Assert.assertFalse(gBD.guardarEstat(idDir, pesosDocs, deletedIds, -1, docs));
        Assert.assertFalse(gBD.guardarEstat(idDir, pesosDocs, deletedIds, idNouDoc, null));
    }

    @Test
    public void testGuardarEstatCrearDirectori() {
        File dir = new File(BD_PATH);
        Assert.assertTrue(dir.delete());

        int idDir = 0;
        HashMap<Integer, HashMap<String,Double>> pesosDocs = new HashMap<>();
        HashMap<String,Double> pes = new HashMap<>();
        pes.put("pes", 23.45);
        pesosDocs.put(0, pes);
        PriorityQueue<Integer> deletedIds = new PriorityQueue<>();
        deletedIds.add(27);
        int idNouDoc = 23;
        HashMap<Integer, Pair<String,String>> docs = new HashMap<>();
        docs.put(0, new Pair<>("Juli", "document"));

        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarEstat(idDir, pesosDocs, deletedIds, idNouDoc, docs));

        try {
            FileInputStream estatFile = new FileInputStream(BD_PATH + "/estat.txt");
            ObjectInputStream estatFileStream = new ObjectInputStream(estatFile);

            GestorBD.Estat estat = (GestorBD.Estat)estatFileStream.readObject();
            Assert.assertEquals(idDir, estat.idDir);
            Assert.assertEquals(pesosDocs, estat.pesosDocs);
            Assert.assertEquals(deletedIds.size(), estat.deletedIds.size());
            Assert.assertEquals(deletedIds.poll(), estat.deletedIds.poll());
            Assert.assertEquals(idNouDoc, estat.idNouDoc);
            Assert.assertEquals(docs, estat.docs);

            estatFileStream.close();
            estatFile.close();
        }
        catch (IOException | ClassNotFoundException e) {
            Assert.fail("Exception during test");
        }

        File f = new File(BD_PATH + "/estat.txt");
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testGuardarEstatCorrecte() {
        int idDir = 0;
        HashMap<Integer, HashMap<String,Double>> pesosDocs = new HashMap<>();
        HashMap<String,Double> pes = new HashMap<>();
        pes.put("pes", 23.45);
        pesosDocs.put(0, pes);
        PriorityQueue<Integer> deletedIds = new PriorityQueue<>();
        deletedIds.add(27);
        int idNouDoc = 23;
        HashMap<Integer, Pair<String,String>> docs = new HashMap<>();
        docs.put(0, new Pair<>("Juli", "document"));

        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarEstat(idDir, pesosDocs, deletedIds, idNouDoc, docs));
        File f = new File(BD_PATH + "/estat.txt");

        try {
            FileInputStream estatFile = new FileInputStream(f);
            ObjectInputStream estatFileStream = new ObjectInputStream(estatFile);

            GestorBD.Estat estat = (GestorBD.Estat)estatFileStream.readObject();
            Assert.assertEquals(idDir, estat.idDir);
            Assert.assertEquals(pesosDocs, estat.pesosDocs);
            Assert.assertEquals(deletedIds.size(), estat.deletedIds.size());
            Assert.assertEquals(deletedIds.poll(), estat.deletedIds.poll());
            Assert.assertEquals(idNouDoc, estat.idNouDoc);
            Assert.assertEquals(docs, estat.docs);

            estatFileStream.close();
            estatFile.close();
        }
        catch (IOException | ClassNotFoundException e) {
            Assert.fail("Exception during test");
        }

        Assert.assertTrue(f.delete());
    }

    @Test
    public void testCarregarEstat() {
        int idDir = 0;
        HashMap<Integer, HashMap<String,Double>> pesosDocs = new HashMap<>();
        HashMap<String,Double> pes = new HashMap<>();
        pes.put("pes", 23.45);
        pesosDocs.put(0, pes);
        PriorityQueue<Integer> deletedIds = new PriorityQueue<>();
        deletedIds.add(27);
        int idNouDoc = 23;
        HashMap<Integer, Pair<String,String>> docs = new HashMap<>();
        docs.put(0, new Pair<>("Juli", "document"));

        GestorBD.Estat estat = new GestorBD.Estat(idDir,pesosDocs,deletedIds,idNouDoc,docs);
        File f = new File(BD_PATH + "/estat.txt");
        try {
            FileOutputStream file = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(estat);

            out.close();
            file.close();
        } catch (IOException e) {
            Assert.fail("Exception during test");
        }

        GestorBD gBD = new GestorBD();
        GestorBD.Estat result = gBD.carregarEstat();

        Assert.assertNotNull(result);
        Assert.assertEquals(estat.idDir, result.idDir);
        Assert.assertEquals(estat.pesosDocs, result.pesosDocs);
        Assert.assertEquals(estat.deletedIds.size(), result.deletedIds.size());
        Assert.assertEquals(estat.deletedIds.poll(), result.deletedIds.poll());
        Assert.assertEquals(estat.idNouDoc, result.idNouDoc);
        Assert.assertEquals(estat.docs, result.docs);

        Assert.assertTrue(f.delete());
    }
}
