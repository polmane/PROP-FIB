package Persistencia.Test;

import Persistencia.Classes.GestorBD;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

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



}
