package Persistencia.Classes;


import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class GestorDocument {

    public enum FILETYPE {
        TXT, XML, PROP
    }

    public static final String XML_TAG_DOCUMENT = "document";
    public static final String XML_TAG_AUTOR = "autor";
    public static final String XML_TAG_TITOL = "titol";
    public static final String XML_TAG_CONTINGUT = "contingut";
    public static final String PROP_TAG_AUTOR = "(autor)";
    public static final String PROP_TAG_TITOL = "(titol)";
    public static final String PROP_TAG_CONTINGUT = "(contingut)";

    public Boolean exportarDocument(String autor, String titol, String contingut, FILETYPE format, String path) {
        String nom = autor + '_' + titol;
        switch (format) {
            case TXT:
                nom += ".txt";
                try {
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write(autor + "\n");
                    output.write(titol + "\n");
                    output.write(contingut);
                    output.close();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            case XML:
                nom += ".xml";
                try {
                    FileOutputStream docExp = new FileOutputStream(path + "/" + nom);
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    org.w3c.dom.Document document = docBuilder.newDocument();
                    Element rootElement = document.createElement(XML_TAG_DOCUMENT);
                    document.appendChild(rootElement);

                    Element elementAutor = document.createElement(XML_TAG_AUTOR);
                    elementAutor.appendChild(document.createTextNode(autor));
                    rootElement.appendChild(elementAutor);

                    Element elementTitol = document.createElement(XML_TAG_TITOL);
                    elementTitol.appendChild(document.createTextNode(titol));
                    rootElement.appendChild(elementTitol);

                    Element elementContingut = document.createElement(XML_TAG_CONTINGUT);
                    elementContingut.appendChild(document.createTextNode(contingut));
                    rootElement.appendChild(elementContingut);

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(docExp);
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source, result);

                    docExp.close();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            case PROP:
                nom += ".prop";
                try {
                    File dir = new File(path);
                    File docExp = new File(dir, nom);
                    Writer output = new BufferedWriter(new FileWriter(docExp));
                    output.write(PROP_TAG_AUTOR + "->" + autor + "<-");
                    output.write(PROP_TAG_TITOL + "->" + titol + "<-");
                    output.write(PROP_TAG_CONTINGUT + "->" + contingut + "<-");
                    output.close();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            default:
                return false;
        }
    }

    public ArrayList<String> importarDocument(String path){
        //NOTE: El path pot ser absolut o relatiu a la base del projecte (subgrup-prop11.1)
        //      P.E. per accedir a la carpeta exported el path haura de ser:
        //           "./Entrega3/FONTS/Persistencia/Exported"
        try {
            if (path.endsWith(".txt")) {
                return parseTXT(path);
            }
            else if (path.endsWith(".xml")) {
                return parseXML(path);
            }
            else if (path.endsWith(".prop")) {
                return parsePROP(path);
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }

    private ArrayList<String> parseTXT(String path) {
        ArrayList<String> values = new ArrayList<>(3);
        try {
            Scanner scanner = new Scanner(new File(path));
            values.add(scanner.nextLine());
            values.add(scanner.nextLine());
            StringBuilder contingut = new StringBuilder();
            while (scanner.hasNextLine()) {
                contingut.append(scanner.nextLine());
                if (scanner.hasNextLine())
                    contingut.append("\n");
            }
            values.add(contingut.toString());
        } catch (Exception e) {
            return null;
        }
        return values;
    }

    private ArrayList<String> parseXML(String path) {
        ArrayList<String> values = new ArrayList<>(3);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(new File(path));

            values.add(doc.getElementsByTagName(XML_TAG_AUTOR).item(0).getTextContent());
            values.add(doc.getElementsByTagName(XML_TAG_TITOL).item(0).getTextContent());
            values.add(doc.getElementsByTagName(XML_TAG_CONTINGUT).item(0).getTextContent());
        } catch (Exception e) {
            return null;
        }
        return values;
    }

    private ArrayList<String> parsePROP(String path) {
        ArrayList<String> values = new ArrayList<>(3);
        try {
            Scanner scanner = new Scanner(new File(path));

            scanner.useDelimiter("->|<-");
            List<String> tokens = scanner.tokens().collect(Collectors.toList());

            int index = tokens.indexOf(PROP_TAG_AUTOR);
            if (index < 0)
                return null;
            values.add(tokens.get(index + 1));

            index = tokens.indexOf(PROP_TAG_TITOL);
            if (index < 0)
                return null;
            values.add(tokens.get(index + 1));

            index = tokens.indexOf(PROP_TAG_CONTINGUT);
            if (index >= 0) {
                values.add(tokens.get(tokens.indexOf(PROP_TAG_CONTINGUT) + 1));
            }
            else {
                values.add("");
            }

        } catch (Exception e) {
            return null;
        }
        return values;
    }
}