package Persistencia.Classes;


import Domini.Classes.Pair;
import org.junit.Assert;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Domini.Classes.Document;

public class GestorDocument {

    public enum FILETYPE {
        TXT, XML, PROP
    }

    private static final String XML_TAG_DOCUMENT = "document";
    private static final String XML_TAG_AUTOR = "autor";
    private static final String XML_TAG_TITOL = "titol";
    private static final String XML_TAG_CONTINGUT = "contingut";
    private static final String XML_FORMAT_TAG = "?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?";

    public void exportarDocument(GestorDirectori.FILETYPE format, Document doc, String path) {
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
                    System.err.println("El document txt no s'ha creat correctament");
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
                    if (!doc.getContingut().isBlank()) {
                        Element contingut = document.createElement(XML_TAG_CONTINGUT);
                        contingut.appendChild(document.createTextNode(doc.getContingut()));
                        rootElement.appendChild(contingut);
                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(docExp);

                    transformer.transform(source, result);
                } catch (Exception e) {
                    System.err.println("El document xml no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public Pair<Document, Boolean> importarDocument(int idDoc, String path){
        if (path.endsWith(".txt")) {
            return tryParse(idDoc, path, FILETYPE.TXT);
        }
        else if (path.endsWith(".xml")) {
            return tryParse(idDoc, path, FILETYPE.XML);
        }
        else if (path.endsWith(".prop")) {
            return tryParse(idDoc, path, FILETYPE.PROP);
        }
        System.err.println("ERROR: Format del document situat a " + path + " no suportat");
        return new Pair<Document, Boolean>(null, false);
    }

    private Pair<Document, Boolean> tryParse(int idDoc, String path, FILETYPE format) {
        String autor = "", titol = "", contingut = "";
        switch (format) {
            case TXT:
                try {
                    File f = new File(path);
                    Scanner scanner = new Scanner(f);
                    autor = scanner.nextLine();
                    titol = scanner.nextLine();
                    while (scanner.hasNextLine()) {
                        contingut += scanner.nextLine() + "\n";
                    }
                } catch (Exception e) {
                    System.err.println("No s'ha pogut importar el document " + path + " en format TXT");
                    throw new RuntimeException(e);
                }
                break;
            case XML:
                try {
                    File f = new File(path);
                    Scanner scanner = new Scanner(f);

                    scanner.useDelimiter("<|>");
                    List<String> tokens = scanner.tokens().collect(Collectors.toList());

                    if (!tokens.get(0).equals(XML_FORMAT_TAG))
                        throw new RuntimeException("El format XML del document " + path + " no és correcte");

                    if (!tokens.contains(XML_TAG_AUTOR))
                        throw new RuntimeException("El document " + path + " no conte l'etiqueta " + XML_TAG_AUTOR);
                    autor = tokens.get(tokens.indexOf(XML_TAG_AUTOR) + 1);

                    if (!tokens.contains(XML_TAG_TITOL))
                        throw new RuntimeException("El document " + path + " no conte l'etiqueta " + XML_TAG_TITOL);
                    titol = tokens.get(tokens.indexOf(XML_TAG_TITOL) + 1);

                    if (tokens.contains(XML_TAG_CONTINGUT))
                        contingut = tokens.get(tokens.indexOf(XML_TAG_CONTINGUT) + 1);

                } catch (Exception e) {
                    System.err.println("No s'ha pogut importar el document " + path + " en format XML");
                    throw new RuntimeException(e);
                }
                break;
            case PROP:
                Assert.fail("IMPORTAR PROP NO IMPLEMENTAT");
                break;
            default:
                Assert.fail("ASSERT NOT REACHED");
        }

        return new Pair<Document, Boolean>(new Document(idDoc, autor, titol, contingut), false);
    }

    public static void main(String args[]) {
        // Document doc = new Document(0, "autor_document", "titol_document", "");
        GestorDocument gestor = new GestorDocument();
        // gestor.exportarDocument(GestorDirectori.FILETYPE.XML, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega3/FONTS/Persistencia/Exported/");
        Document d = gestor.importarDocument(0, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega3/FONTS/Persistencia/Exported/autor_document_titol_document.xml").first();
        System.out.println("Document 1");
        System.out.println(d.getAutor());
        System.out.println(d.getTitol());
        System.out.println(d.getContingut());
    }
}