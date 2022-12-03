package Entrega2.Persistencia.FONTS.Classes;

import Entrega1.FONTS.Classes.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class GestorDocument {

    public enum FILETYPE {
        TXT, XML, PROP
    }

    /**
     * exportarDocument exporta el document del directori a un path elegit
     *
     * @param format es correspon en quin format es desitja exportar el document
     * @param path   es correspon al camí desitjat per tal de guardar el document
     */
    //TODO: TEST
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
                    Element rootElement = document.createElement("FILE"); //FIXME:
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
}