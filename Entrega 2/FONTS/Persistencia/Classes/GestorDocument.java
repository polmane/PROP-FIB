package FONTS.Persistencia;

import FONTS.Classes.Document;
import FONTS.Classes.Directori;

/**
 * exportarDocument exporta el document del directori a un path elegit
 * @param format es correspon en quin format es desitja exportar el document
 * @param path es correspon al camí desitjat per tal de guardar el document
 */
//TODO: TEST (NO CAL PER ENTREGA 1)
public void exportarDocument(FILETYPE format, String path) {
        switch (format) {
        case TXT:
        try {
        String nom = documentActiu.getAutor()+documentActiu.getTitol()+".txt";
        File dir = new File (path);
        File docExp = new File(dir,nom);
        Writer output = new BufferedWriter(new FileWriter(docExp));
        output.write(documentActiu.getAutor() + "\n");
        output.write(documentActiu.getTitol()+"\n");
        output.write(documentActiu.getContingut());
        output.flush();
        } catch (Exception e) {
        System.err.println("El document txt no s'ha creat correctament");
        throw new RuntimeException(e);
        }
        break;
        case XML:
        try {
        String nom = documentActiu.getAutor()+documentActiu.getTitol()+".xml";
        FileOutputStream docExp = new FileOutputStream(path+"\\"+nom);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        org.w3c.dom.Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement(nom);
        doc.appendChild(rootElement);
        Element autor = doc.createElement("AUTOR");
        autor.appendChild(doc.createTextNode(documentActiu.getAutor()));
        rootElement.appendChild(autor);
        Element titol = doc.createElement("TITOL");
        titol.appendChild(doc.createTextNode(documentActiu.getTitol()));
        rootElement.appendChild(titol);
        Element contingut = doc.createElement("CONTINGUT");
        contingut.appendChild(doc.createTextNode(documentActiu.getContingut()));
        rootElement.appendChild(contingut);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(docExp);

        transformer.transform(source, result);
        }catch (Exception e) {
        System.err.println("El document xml no s'ha creat correctament");
        throw new RuntimeException(e);
        }
        break;
        }
        }