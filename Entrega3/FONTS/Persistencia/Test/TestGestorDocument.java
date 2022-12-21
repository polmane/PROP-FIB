package Persistencia.Test;

import Persistencia.Classes.GestorDocument;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGestorDocument {

    private static final String PATH_OUT = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/OutputFiles";
    private static final String PATH_IN = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/InputFiles";

    @Test
    public void testExportarDocumentTXTSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
            Assert.fail("Exception during test");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(contingut, result.nextLine());
        result.close();
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentTXTSenseContingut() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
            Assert.fail("Exception during test");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertFalse(result.hasNextLine());
        result.close();
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentTXTContingutLlarg() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        gDoc.exportarDocument(autor, titol, c1 + c2 + c3 + c4, GestorDocument.FILETYPE.TXT, PATH_OUT);
        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".txt");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("Exception during test");
            return;
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(autor, result.nextLine());
        Assert.assertEquals(titol, result.nextLine());
        Assert.assertEquals(c1, result.nextLine() + "\n");
        Assert.assertEquals(c2, result.nextLine() + "\n");
        Assert.assertEquals(c3, result.nextLine() + "\n");
        Assert.assertEquals(c4, result.nextLine());
        result.close();
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentXMLSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";

        Assert.assertTrue(gDoc.exportarDocument(autor, titol, contingut, GestorDocument.FILETYPE.XML, PATH_OUT));

        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(f);

            Assert.assertEquals(autor, doc.getElementsByTagName(GestorDocument.XML_TAG_AUTOR).item(0).getTextContent());
            Assert.assertEquals(titol, doc.getElementsByTagName(GestorDocument.XML_TAG_TITOL).item(0).getTextContent());
            Assert.assertEquals(contingut, doc.getElementsByTagName(GestorDocument.XML_TAG_CONTINGUT).item(0).getTextContent());
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            Assert.fail("Exception during test");
        }
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentXMLSenseContingut() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";

        Assert.assertTrue(gDoc.exportarDocument(autor, titol, contingut, GestorDocument.FILETYPE.XML, PATH_OUT));

        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(f);

            Assert.assertEquals(autor, doc.getElementsByTagName(GestorDocument.XML_TAG_AUTOR).item(0).getTextContent());
            Assert.assertEquals(titol, doc.getElementsByTagName(GestorDocument.XML_TAG_TITOL).item(0).getTextContent());
            Assert.assertEquals(contingut, doc.getElementsByTagName(GestorDocument.XML_TAG_CONTINGUT).item(0).getTextContent());
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            Assert.fail("Exception during test");
        }
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentXMLContingutLlarg() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";

        Assert.assertTrue(gDoc.exportarDocument(autor, titol, c1 + c2 + c3 + c4, GestorDocument.FILETYPE.XML, PATH_OUT));

        File f = new File(PATH_OUT + "/" + autor + "_" + titol + ".xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(f);

            Assert.assertEquals(autor, doc.getElementsByTagName(GestorDocument.XML_TAG_AUTOR).item(0).getTextContent());
            Assert.assertEquals(titol, doc.getElementsByTagName(GestorDocument.XML_TAG_TITOL).item(0).getTextContent());
            Assert.assertEquals(c1 + c2 + c3 + c4, doc.getElementsByTagName(GestorDocument.XML_TAG_CONTINGUT).item(0).getTextContent());
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            Assert.fail("File Not Found");
        }
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentPROPSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentPROPSenseContingut() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testExportarDocumentPROPContingutLlarg() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
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
        Assert.assertTrue(f.delete());
    }

    @Test
    public void testImportarDocumentFormatNoSuportat() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/format_no_suportat.wtf");
        Assert.assertNull(result);
    }

    @Test
    public void testImportarDocumentTXTSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".txt");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentTXTSenseContingut() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".txt");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(c1 + c2 + c3 + c4, result.get(2));
    }

    @Test
    public void testImportarDocumentXMLSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".xml");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentXMLSenseContingut() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".xml");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));
    }

    @Test
    public void testImportarDocumentXMLContingutLlarg() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".xml");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(c1 + c2 + c3 + c4, result.get(2));
    }

    @Test
    public void testImportarDocumentPROPSimple() {
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
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
        File output = new File(PATH_OUT);
        if(!output.exists()) output.mkdir();
        GestorDocument gDoc = new GestorDocument();
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        ArrayList<String> result = gDoc.importarDocument(PATH_IN + "/" + autor + "_" + titol + ".prop");
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(c1 + c2 + c3 + c4, result.get(2));
    }
}
