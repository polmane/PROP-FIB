package Persistencia.Classes;

import Domini.Classes.Document;

import org.junit.Assert;
import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class GestorDocument {

    public enum FILETYPE {
        TXT, XML, PROP
    }

    private static final String XML_TAG_DOCUMENT = "document";
    private static final String XML_TAG_AUTOR = "autor";
    private static final String XML_TAG_TITOL = "titol";
    private static final String XML_TAG_CONTINGUT = "contingut";
    private static final String PROP_TAG_AUTOR = "(autor)";
    private static final String PROP_TAG_TITOL = "(titol)";
    private static final String PROP_TAG_CONTINGUT = "(contingut)";



    public void exportarDocument(FILETYPE format, Document doc, String path) {
        String nom = doc.getAutor() + '_' + doc.getTitol();
        switch (format) {
            case TXT:
                try {
                    nom += ".txt";
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write(doc.getAutor() + "\n");
                    output.write(doc.getTitol() + "\n");
                    output.write(doc.getContingut());
                    output.flush();
                } catch (Exception e) {
                    System.err.println("El document en format .txt no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
            case XML:
                try {
                    nom += ".xml";
                    FileOutputStream docExp = new FileOutputStream(path + "/" + nom);
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    org.w3c.dom.Document document = docBuilder.newDocument();
                    Element rootElement = document.createElement(XML_TAG_DOCUMENT); //FIXME:
                    // Aqui petava per caracter ilegal a on ara posa FILE, abans hi havia la variable 'nom' (entenc q el "." la liava)
                    // Serveix aixi amb aquesta etiqueta?? cal que sigui el nom del fitxer?? Ara mateix es crea el document correctament ;-)
                    document.appendChild(rootElement);
                    Element autor = document.createElement(XML_TAG_AUTOR);
                    autor.appendChild(document.createTextNode(doc.getAutor()));
                    rootElement.appendChild(autor);
                    Element titol = document.createElement(XML_TAG_TITOL);
                    titol.appendChild(document.createTextNode(doc.getTitol()));
                    rootElement.appendChild(titol);
                    Element contingut = document.createElement(XML_TAG_CONTINGUT);
                    contingut.appendChild(document.createTextNode(doc.getContingut()));
                    rootElement.appendChild(contingut);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(docExp);

                    transformer.transform(source, result);
                } catch (Exception e) {
                    System.err.println("El document en format .xml no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
            case PROP:
                try {
                    nom += ".prop";
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write(PROP_TAG_AUTOR + "->" + doc.getAutor() + "<-");
                    output.write(PROP_TAG_TITOL + "->" + doc.getTitol() + "<-");
                    output.write(PROP_TAG_CONTINGUT + "->" + doc.getContingut() + "<-");
                    output.flush();
                } catch (Exception e) {
                    System.err.println("El document en format .prop no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
            default:
                Assert.fail("NOT REACHED");
        }
    }

    public Document importarDocument(int idDoc, String path){
        //NOTE: El path pot ser absolut o relatiu a la base del projecte (subgrup-prop11.1)
        //      P.E. per accedir a la carpeta exported el path haura de ser:
        //           "./Entrega3/FONTS/Persistencia/Exported"
        try {
            if (path.endsWith(".txt")) {
                return ParseTXT(idDoc, path);
            }
            else if (path.endsWith(".xml")) {
                return ParseXML(idDoc, path);
            }
            else if (path.endsWith(".prop")) {
                return ParsePROP(idDoc, path);
            }
        }
        catch (Exception e) {
            System.err.println("No s'ha pogut importar el document " + path);
            throw new RuntimeException(e);
        }
        Assert.fail("ERROR: Format del document situat a " + path + " no suportat");
        return null;
    }

    private Document ParseTXT(int idDoc, String path) {
        String autor = "";
        String titol = "";
        StringBuilder contingut = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(path));
            autor = scanner.nextLine();
            titol = scanner.nextLine();
            while (scanner.hasNextLine()) {
                contingut.append(scanner.nextLine()).append("\n");
            }
        } catch (Exception e) {
            System.err.println("No s'ha pogut importar el document " + path + " en format TXT");
            throw new RuntimeException(e);
        }
        return new Document(idDoc, autor, titol, contingut.toString());
    }

    private Document ParseXML(int idDoc, String path) {
        String autor = "", titol = "", contingut = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(new File(path));
            Element document = (Element) doc.getElementsByTagName(XML_TAG_DOCUMENT).item(0);

            autor = document.getElementsByTagName(XML_TAG_AUTOR).item(0).getTextContent();
            titol = document.getElementsByTagName(XML_TAG_TITOL).item(0).getTextContent();
            contingut = document.getElementsByTagName(XML_TAG_CONTINGUT).item(0).getTextContent();
        } catch (Exception e) {
            System.err.println("No s'ha pogut importar el document " + path + " en format XML");
            throw new RuntimeException(e);
        }
        return new Document(idDoc, autor, titol, contingut);
    }

    private Document ParsePROP(int idDoc, String path) {
        String autor = "", titol = "", contingut = "";
        try {
            Scanner scanner = new Scanner(new File(path));

            scanner.useDelimiter("->|<-");
            List<String> tokens = scanner.tokens().collect(Collectors.toList());

            int index = tokens.indexOf(PROP_TAG_AUTOR);
            if (index < 0)
                throw new RuntimeException("El document " + path + " no conte l'etiqueta " + PROP_TAG_AUTOR);
            autor = tokens.get(index + 1);

            index = tokens.indexOf(PROP_TAG_TITOL);
            if (index < 0)
                throw new RuntimeException("El document " + path + " no conte l'etiqueta " + PROP_TAG_TITOL);
            titol = tokens.get(index + 1);

            index = tokens.indexOf(PROP_TAG_CONTINGUT);
            if (index >= 0)
                contingut = tokens.get(tokens.indexOf(PROP_TAG_CONTINGUT) + 1);

        } catch (Exception e) {
            System.err.println("No s'ha pogut importar el document " + path + " en format PROP");
            throw new RuntimeException(e);
        }
        return new Document(idDoc, autor, titol, contingut);
    }


    public static void main(String[] args) {
        Document doc = new Document(0, "autor_document", "titol_document", "");
        GestorDocument gestor = new GestorDocument();
        gestor.exportarDocument(FILETYPE.XML, doc, "./Entrega3/FONTS/Persistencia/Exported/");
        Document d = gestor.importarDocument(0, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega3/FONTS/Persistencia/Exported/autor_document_titol_document.xml");
        System.out.println("Document 1");
        System.out.println(d.getAutor());
        System.out.println(d.getTitol());
        System.out.println(d.getContingut());
    }
}