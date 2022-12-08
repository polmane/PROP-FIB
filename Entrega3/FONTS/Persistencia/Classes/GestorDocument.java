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
import java.util.Scanner;

import Domini.Classes.Document;

public class GestorDocument {

    public enum FILETYPE {
        TXT, XML, PROP
    }

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
                    Element rootElement = document.createElement("DOCUMENT"); //FIXME:
                    // Aqui petava per caracter ilegal a on ara posa FILE, abans hi havia la variable 'nom' (entenc q el "." la liava)
                    // Serveix aixi amb aquesta etiqueta?? cal que sigui el nom del fitxer?? Ara mateix es crea el document correctament ;-)
                    document.appendChild(rootElement);
                    Element autor = document.createElement("AUTOR");
                    autor.appendChild(document.createTextNode(doc.getAutor()));
                    rootElement.appendChild(autor);
                    Element titol = document.createElement("TITOL");
                    titol.appendChild(document.createTextNode(doc.getTitol()));
                    rootElement.appendChild(titol);
                    Element contingut = document.createElement("CONTINGUT");
                    contingut.appendChild(document.createTextNode(doc.getContingut()));
                    rootElement.appendChild(contingut);

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
                Assert.fail("IMPORTAR XML NO IMPLEMENTAT");
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
        GestorDocument gestor = new GestorDocument();
        Document d = gestor.importarDocument(0, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega3/FONTS/Persistencia/Exported/Document1.txt").first();
        System.out.println("Document 1");
        System.out.println(d.getAutor());
        System.out.println(d.getTitol());
        System.out.println(d.getContingut());
    }
}