package Persistencia.Test;

import Persistencia.Classes.GestorDocument;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGestorDocument {

    private static final String PATH_OUT = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/OutputFiles";
    private static final String PATH_IN = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/InputFiles";

    @Test
    public void testExportarDocumentTXTSimple() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";
        gDoc.exportarDocument(autor, titol, contingut, GestorDocument.FILETYPE.TXT, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".txt");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(contingut, result.nextLine());
        result.close();
        f.delete();
    }

    @Test
    public void testExportarDocumentTXTSenseContingut() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        gDoc.exportarDocument(autor, titol, "", GestorDocument.FILETYPE.TXT, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".txt");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertFalse(result.hasNextLine());
        result.close();
        f.delete();
    }

    @Test
    public void testExportarDocumentTXTContingutLlarg() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be\n";
        gDoc.exportarDocument(autor, titol, c1 + c2 + c3 + c4, GestorDocument.FILETYPE.TXT, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".txt");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(c1, result.nextLine() + "\n");
        Assert.assertEquals(c2, result.nextLine() + "\n");
        Assert.assertEquals(c3, result.nextLine() + "\n");
        Assert.assertEquals(c4, result.nextLine() + "\n");
        result.close();
        f.delete();
    }

    //TODO: FIX XML EXPORT I DESPRES FER BE EL TEST D'EXPORT
    /*
    @Test
    public void testExportarDocumentXMLSimple() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        gDoc.exportarDocument(autor, titol, "contingut de prova", GestorDocument.FILETYPE.XML, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(contingut, result.nextLine());
        result.close();
        f.delete();
    }

    @Test
    public void testExportarDocumentXMLSenseContingut() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        gDoc.exportarDocument(autor, titol, "", GestorDocument.FILETYPE.XML, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertFalse(result.hasNextLine());
        result.close();
        f.delete();
    }

    @Test
    public void testExportarDocumentXMLContingutLlarg() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be\n";
        gDoc.exportarDocument(autor, titol, c1 + c2 + c3 + c4, GestorDocument.FILETYPE.XML, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(c1, result.nextLine() + "\n");
        Assert.assertEquals(c2, result.nextLine() + "\n");
        Assert.assertEquals(c3, result.nextLine() + "\n");
        Assert.assertEquals(c4, result.nextLine() + "\n");
        result.close();
        f.delete();
    }

     */

    @Test
    public void testExportarDocumentPROPSimple() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";
        gDoc.exportarDocument(autor, titol, contingut, GestorDocument.FILETYPE.PROP, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".prop");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        String cmp = GestorDocument.PROP_TAG_AUTOR + "->" + autor + "<-" +
                     GestorDocument.PROP_TAG_TITOL + "->" + titol + "<-" +
                     GestorDocument.PROP_TAG_CONTINGUT + "->" + contingut + "<-";
        Assert.assertEquals(cmp, result.nextLine());
        result.close();
        f.delete();
    }


    @Test
    public void testExportarDocumentPROPSenseContingut() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        gDoc.exportarDocument(autor, titol, "", GestorDocument.FILETYPE.PROP, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".prop");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        String cmp = GestorDocument.PROP_TAG_AUTOR + "->" + autor + "<-" +
                GestorDocument.PROP_TAG_TITOL + "->" + titol + "<-" +
                GestorDocument.PROP_TAG_CONTINGUT + "->" + "<-";
        Assert.assertEquals(cmp, result.nextLine());
        result.close();
        f.delete();
    }

    @Test
    public void testExportarDocumentPROPContingutLlarg() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be\n";
        gDoc.exportarDocument(autor, titol, c1 + c2 + c3 + c4, GestorDocument.FILETYPE.PROP, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".prop");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertNotNull(result);
        String cmp = GestorDocument.PROP_TAG_AUTOR + autor +
                GestorDocument.PROP_TAG_TITOL + titol +
                GestorDocument.PROP_TAG_CONTINGUT + c1 + c2 + c3 + c4;
        result.useDelimiter("->|<-");
        StringBuilder res = new StringBuilder();
        for (Object token : result.tokens().toArray()) {
            res.append(token.toString());
        }
        Assert.assertEquals(cmp, res.toString());
        result.close();
        f.delete();
    }

    @Test
    public void testImportarDocumentFormatNoSuportat() {
        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/format_no_suportat.wtf");
        Assert.assertNull(result);
    }

    @Test
    public void testImportarDocumentTXTSimple() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova\n";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".txt");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentTXTSenseContingut() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".txt");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentTXTContingutLlarg() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be\n";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".txt");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(c1 + c2 + c3 + c4, result.get(2));
    }

    //TODO: FIX XML EXPORT I DESPRES FER BE EL TEST D'IMPORT

    @Test
    public void testImportarDocumentPROPSimple() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".prop");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentPROPSenseContingut() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".prop");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentPROPContingutLlarg() {
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be\n";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".prop");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(c1 + c2 + c3 + c4, result.get(2));
    }
}
