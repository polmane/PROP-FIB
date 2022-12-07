package Persistencia.Classes;

import Domini.Classes.Expressio;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class GestorExpressio {

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
    public void exportarExpressio(GestorDirectori.FILETYPE format, Expressio expressio, String path) {
        String nom = "Expressio" + expressio.getIdExp();
        switch (format) {
            case TXT:
                try {
                    nom += ".txt";
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write(expressio.getExpressio() + "\n");
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
                    Element rootElement = document.createElement("EXPRESSIO");
                    rootElement.appendChild(document.createTextNode(expressio.getExpressio()));
                    document.appendChild(rootElement);

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