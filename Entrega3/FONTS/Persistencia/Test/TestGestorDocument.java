package Persistencia.Test;

import Persistencia.Classes.GestorDocument;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGestorDocument {

    private static final String PATH_OUT = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/OutputFiles";
    private static final String PATH_IN = System.getProperty("user.dir") + "/FONTS/Persistencia/Test/InputFiles";

    @Test
    public void testExportarDocumentTXTSimple() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentTXTSenseContingut() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentTXTContingutLlarg() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentXMLSimple() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentXMLSenseContingut() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentXMLContingutLlarg() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentPROPSimple() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentPROPSenseContingut() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testExportarDocumentPROPContingutLlarg() {
        File dir = new File(PATH_OUT);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());
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
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentFormatNoSuportat() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/format_no_suportat.wtf";

        try {
            FileWriter f = new FileWriter(path);
            Writer output = new BufferedWriter(f);
            output.write("Aquest document no es suportat\n");
            output.close();
            f.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertNull(result);

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentTXTSimple() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova1.txt";
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";

        try {
            FileWriter f = new FileWriter(path);
            Writer output = new BufferedWriter(f);
            output.write(autor + "\n");
            output.write(titol + "\n");
            output.write(contingut);
            output.close();
            f.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentTXTSenseContingut() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova2.txt";
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";

        try {
            FileWriter f = new FileWriter(path);
            Writer output = new BufferedWriter(f);
            output.write(autor + "\n");
            output.write(titol + "\n");
            output.write(contingut);
            output.close();
            f.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentTXTContingutLlarg() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova3.txt";
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        String contingut = c1 + c2 + c3 + c4;

        try {
            FileWriter f = new FileWriter(path);
            Writer output = new BufferedWriter(f);
            output.write(autor + "\n");
            output.write(titol + "\n");
            output.write(contingut);
            output.close();
            f.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentXMLSimple() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova1.xml";
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";

        try {
            FileOutputStream docExp = new FileOutputStream(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            org.w3c.dom.Document document = docBuilder.newDocument();
            Element rootElement = document.createElement(GestorDocument.XML_TAG_DOCUMENT);
            document.appendChild(rootElement);

            Element elementAutor = document.createElement(GestorDocument.XML_TAG_AUTOR);
            elementAutor.appendChild(document.createTextNode(autor));
            rootElement.appendChild(elementAutor);

            Element elementTitol = document.createElement(GestorDocument.XML_TAG_TITOL);
            elementTitol.appendChild(document.createTextNode(titol));
            rootElement.appendChild(elementTitol);

            Element elementContingut = document.createElement(GestorDocument.XML_TAG_CONTINGUT);
            elementContingut.appendChild(document.createTextNode(contingut));
            rootElement.appendChild(elementContingut);

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(docExp);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            docExp.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentXMLSenseContingut() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova2.xml";
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";

        try {
            FileOutputStream docExp = new FileOutputStream(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            org.w3c.dom.Document document = docBuilder.newDocument();
            Element rootElement = document.createElement(GestorDocument.XML_TAG_DOCUMENT);
            document.appendChild(rootElement);

            Element elementAutor = document.createElement(GestorDocument.XML_TAG_AUTOR);
            elementAutor.appendChild(document.createTextNode(autor));
            rootElement.appendChild(elementAutor);

            Element elementTitol = document.createElement(GestorDocument.XML_TAG_TITOL);
            elementTitol.appendChild(document.createTextNode(titol));
            rootElement.appendChild(elementTitol);

            Element elementContingut = document.createElement(GestorDocument.XML_TAG_CONTINGUT);
            elementContingut.appendChild(document.createTextNode(contingut));
            rootElement.appendChild(elementContingut);

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(docExp);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            docExp.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentXMLContingutLlarg() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova3.xml";
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        String contingut = c1 + c2 + c3 + c4;

        try {
            FileOutputStream docExp = new FileOutputStream(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            org.w3c.dom.Document document = docBuilder.newDocument();
            Element rootElement = document.createElement(GestorDocument.XML_TAG_DOCUMENT);
            document.appendChild(rootElement);

            Element elementAutor = document.createElement(GestorDocument.XML_TAG_AUTOR);
            elementAutor.appendChild(document.createTextNode(autor));
            rootElement.appendChild(elementAutor);

            Element elementTitol = document.createElement(GestorDocument.XML_TAG_TITOL);
            elementTitol.appendChild(document.createTextNode(titol));
            rootElement.appendChild(elementTitol);

            Element elementContingut = document.createElement(GestorDocument.XML_TAG_CONTINGUT);
            elementContingut.appendChild(document.createTextNode(contingut));
            rootElement.appendChild(elementContingut);

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(docExp);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            docExp.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentPROPSimple() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova1.prop";
        String autor = "Juli";
        String titol = "prova1";
        String contingut = "contingut de prova";

        try {
            FileWriter fw = new FileWriter(new File(path));
            Writer output = new BufferedWriter(fw);
            output.write(GestorDocument.PROP_TAG_AUTOR + "->" + autor + "<-");
            output.write(GestorDocument.PROP_TAG_TITOL + "->" + titol + "<-");
            output.write(GestorDocument.PROP_TAG_CONTINGUT + "->" + contingut + "<-");
            output.close();
            fw.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentPROPSenseContingut() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova2.prop";
        String autor = "Juli";
        String titol = "prova2";
        String contingut = "";

        try {
            FileWriter fw = new FileWriter(new File(path));
            Writer output = new BufferedWriter(fw);
            output.write(GestorDocument.PROP_TAG_AUTOR + "->" + autor + "<-");
            output.write(GestorDocument.PROP_TAG_TITOL + "->" + titol + "<-");
            output.write(GestorDocument.PROP_TAG_CONTINGUT + "->" + contingut + "<-");
            output.close();
            fw.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }

    @Test
    public void testImportarDocumentPROPContingutLlarg() {
        File dir = new File(PATH_IN);
        if(!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = PATH_IN + "/prova3.prop";
        String autor = "Juli";
        String titol = "prova3";
        String c1 = "contingut de prova\n";
        String c2 = "que conte salts de pagina\n";
        String c3 = "hem de veure\n";
        String c4 = "que els exporti be";
        String contingut = c1 + c2 + c3 + c4;

        try {
            FileWriter fw = new FileWriter(new File(path));
            Writer output = new BufferedWriter(fw);
            output.write(GestorDocument.PROP_TAG_AUTOR + "->" + autor + "<-");
            output.write(GestorDocument.PROP_TAG_TITOL + "->" + titol + "<-");
            output.write(GestorDocument.PROP_TAG_CONTINGUT + "->" + contingut + "<-");
            output.close();
            fw.close();
        } catch (Exception e) {
            Assert.fail("Exception during test");
        }

        GestorDocument gDoc = new GestorDocument();
        ArrayList<String> result = gDoc.importarDocument(path);
        Assert.assertEquals(autor, result.get(0));
        Assert.assertEquals(titol, result.get(1));
        Assert.assertEquals(contingut, result.get(2));

        File f = new File(path);
        Assert.assertTrue(f.delete());
        Assert.assertTrue(dir.delete());
    }
}
