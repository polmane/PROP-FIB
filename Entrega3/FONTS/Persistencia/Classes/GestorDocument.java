package Persistencia.Classes;


import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import Domini.Classes.Document;

public class GestorDocument {

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
                    Element rootElement = document.createElement("DOCUMENT");
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
                    System.err.println("El document en format .xml no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    nom += ".prop";
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write("(autor)->" + doc.getAutor() + "<-");
                    output.write("(titol)->" + doc.getTitol() + "<-");
                    output.write("(contingut)->" + doc.getContingut() + "<-");
                    output.flush();
                } catch (Exception e) {
                    System.err.println("El document en format .prop no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public static void main(String[] args) {
        GestorDocument gestorDocument = new GestorDocument();
        Document doc = new Document(0,"prova","txt","avui fa bon dia");
        String path = "C:/Users/polca/OneDrive/Escritorio/PROPDocuments";
        gestorDocument.exportarDocument(GestorDirectori.FILETYPE.valueOf("PROP"),doc,path);
    }
}