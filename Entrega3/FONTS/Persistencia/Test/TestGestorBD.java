package Persistencia.Test;

import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TestGestorBD {

    /**
     * Adreça a la carpeta que representa el directori del nostre sistema
     */
    private final String BD_PATH = System.getProperty("user.dir") + "/directori";

    /**
     * Objecte de la prova: Test del mètode guardarContingutDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es crea la carpeta de directori en cas que no existeixi
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            S'elimina aquest directori (el creem i l'eliminem per poder estar segurs que sempre l'eliminarem)
     *            Es guarda un contingut amb un identificador al directori.
     *            Es comprova que s'ha guardat correctament el contingut.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarContingutDocumentCrearDirectori() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        Assert.assertTrue(dir.delete());

        int id = 0;
        String contingut = "contingut";
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, contingut));

        File f = new File(BD_PATH + "/" + id + ".txt");
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

    /**
     * Objecte de la prova: Test del mètode guardarContingutDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es comprova que es pot guardar un contingut null al directori
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es guarda un contingut null amb un identificador al directori.
     *            Es comprova que s'ha guardat correctament el contingut.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarContingutDocumentContingutNull() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 0;
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, null));

        File f = new File(BD_PATH + "/" + id + ".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Assert.assertNull(br.readLine());
            br.close();
            fr.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        Assert.assertTrue(f.delete());
    }

    /**
     * Objecte de la prova: Test del mètode guardarContingutDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es comprova que es pot guardar un contingut al directori
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es guarda un contingut amb un identificador al directori.
     *            Es comprova que s'ha guardat correctament el contingut.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarContingutDocumentCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 0;
        String contingut = "contingut";
        GestorBD gBD = new GestorBD();
        Assert.assertTrue(gBD.guardarContingutDocument(id, contingut));

        File f = new File(BD_PATH + "/" + id + ".txt");
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

    /**
     * Objecte de la prova: Test del mètode carregarContingutDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es comprova que es pot carregar el contingut d'un document guardat al directori
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es guarda un contingut amb un identificador al directori.
     *            Es comprova que es pot carregar correctament el contingut.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testCarregarContingutDocument() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 0;
        String contingut = "contingut";

        File f = new File(BD_PATH + "/" + id + ".txt");
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

    /**
     * Objecte de la prova: Test del mètode carregarContingutDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es comprova que es pot carregar un contingut buit d'un document guardat al directori
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es guarda un contingut null amb un identificador al directori.
     *            Es comprova que es pot carregar correctament el contingut, aquest és un string buit.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testCarregarContingutDocumentContingutBuit() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 0;

        File f = new File(BD_PATH + "/" + id + ".txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorBD gBD = new GestorBD();
        Assert.assertEquals("", gBD.carregarContingutDocument(id));

        Assert.assertTrue(f.delete());
    }

    /**
     * Objecte de la prova: Test de deteccions d'error en el mètode eliminarDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es detecta un identificador inexistent en intentar eliminar un document.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es comprova que, en intentar eliminar un document que no existeix, es detecta l'error.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testEliminarDocumentError() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        GestorBD gBD = new GestorBD();
        Boolean result = gBD.eliminarDocument(1234);
        Assert.assertFalse(result);
    }

    /**
     * Objecte de la prova: Test del mètode eliminarDocument.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es pot eliminar un document del directori.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es creen l'enter i l'string representant l'identificador i el contingut d'un document.
     *            Es creen un fitxer que representa aquest document al directori.
     *            Es comprova que, en intentar eliminar aquest document, es fa correctament.
     *            S'esborren els fitxers que hem creat.
     */
    @Test
    public void testEliminarDocumentCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 0;
        String contingut = "contingut";

        File f = new File(BD_PATH + "/" + id + ".txt");
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

    /**
     * Objecte de la prova: Test del control d'arguments del mètode guardarEstat.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es controlen els arguments quan es crida a guardarEstat.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es creen arguments vàlids.
     *            Es fan crides successives, a cada una fent un argument invàlid, es comprova que totes retornen fals.
     */
    @Test
    public void testGuardarEstatBadArgument() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

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

    /**
     * Objecte de la prova: Test del mètode guardarEstat.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es crea la carpeta de directori en cas que no existeixi.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            S'elimina aquest directori (el creem i l'eliminem per poder estar segurs que sempre l'eliminarem)
     *            Es creen arguments vàlids.
     *            Es fa una crida a guardarEstat amb aquests arguments.
     *            Es comprova que s'ha guardat correctament l'estat.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarEstatCrearDirectori() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

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

    /**
     * Objecte de la prova: Test del mètode guardarEstat.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es guarda l'estat correctament.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es creen arguments vàlids.
     *            Es fa una crida a guardarEstat amb aquests arguments.
     *            Es comprova que s'ha guardat correctament l'estat.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarEstatCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

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

    /**
     * Objecte de la prova: Test del mètode carregarEstat.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es comprova que sigui un estat vàlid abans de carregar.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es creen arguments d'estat invàlids.
     *            Es crea un fitxer al directori representant aquest estat.
     *            Es fa una crida a carregarEstat amb aquests arguments.
     *            Es comprova que, en carregar l'estat, s'han detectat com a invàlids i no s'ha fet la càrrega.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testCarregarEstatError() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        GestorBD.Estat estat = new GestorBD.Estat(-15,null,null,-23,null);
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
        Assert.assertNull(gBD.carregarEstat());

        Assert.assertTrue(f.delete());
    }

    /**
     * Objecte de la prova: Test del mètode carregarEstat.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es carrega l'estat correctament.
     * Operativa: Es crea el directori pels documents si aquest no existeix.
     *            Es creen arguments d'estat vàlids.
     *            Es crea un fitxer al directori representant aquest estat.
     *            Es fa una crida a carregarEstat amb aquests arguments.
     *            Es comprova que s'ha carregat correctament l'estat.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testCarregarEstatCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

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
