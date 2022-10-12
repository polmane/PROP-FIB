package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class CtrlDirectori {
    /**
     * Representa el directori que s'està utilitzant
     */
    private Directori directoriObert;

    /**
     * Representa el document carregat en el directori
     */
    private Document documentActiu;

    /**
     * Constructora
     */
    public CtrlDirectori() {
        directoriObert = null;
        documentActiu = null;
    }

    /**
     * Getters de directoriObert i documentActiu
     */
    public Directori getDirectoriObert() {
        return directoriObert;
    }
    public Document getDocumentActiu() {
        return documentActiu;
    }

    /**
     * Operació per crear un directori en el nostre sistema
     */
    public void crearDirectori(int idDir) {
        if (directoriObert == null) {
            directoriObert = new Directori(idDir);
        }
    }

     /**
     * Operació per obrir un document que ja teniem precarregat dins el nostre sistema
     * @param idDoc és l'identificador del docuemnt que volem obrir
     */
    public void recuperarDocument(int idDoc) {
        //nose si fa falta aquest if pk amb una interfície ben feta podem assegurar que el document existeix
        if (directoriObert.docs.containsKey(idDoc)) {
            documentActiu = directoriObert.docs.get(idDoc);
        }
    }


    /**
     * Modifica l'autor del document actiu
     * @param autor és el nou nom d'autor que es vol utilitzar pel document
     */
    public void modificarAutor(String autor) {
        documentActiu.setAutor(autor);
    }

    /**
     * Modifica el títol del document actiu
     * @param titol és el nou nom del títol que es vol utilitzar pel document
     */
    public void modificarTitol(String titol) {
        documentActiu.setTitol(titol);
    }

    /**
     * Modifica el contingut del document actiu
     * @param contingut és el nou contingut que es vol utilitzar pel document
     */
    public void modificarContngut(String contingut) {
        documentActiu.setContingut(contingut);
    }

    /**
     * Afegir Document fa una alta d'un nou document dins el directori
     * @param autor representa l'autor del document que es vol afegir
     * @param titol representa el títol del document que es vol afegir
     * @param contingut representa el contingut del nou document
     */
    //Falta fer el test d'aquesta operació
    public void afegirDocument (String autor, String titol, String contingut) throws Exception {
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.docs.containsKey(i) && directoriObert.docs.get(i).getAutor().equals(autor) && directoriObert.docs.get(i).getTitol().equals(titol)) {
                throw new Exception("El document amb autor: " + autor + " i títol: "+ titol + " ja existeix");
            }
        }
        int id;
        //Comprovem que no tenim cap id per reciclar
        if(!directoriObert.deletedIds.isEmpty()) {
            //En cas d'entrar aquí assignem la id que tenim a la cua
            id = directoriObert.deletedIds.poll();
        }
        else {
            //Si no hi ha ids per reciclar assignem la nova id al document
            id = 1 + directoriObert.getIdNouDoc();
            directoriObert.setIdNouDoc(id + 1);
        }
        documentActiu = new Document(id, autor, titol, contingut);
        directoriObert.docs.put(id, documentActiu);

        //TODO: afegir document a la taula de pesos
        ArrayList<String> paraules = obteContingut();
    }

    private ArrayList<String> obteContingut() {
        String text = documentActiu.getContingut();
        ArrayList<String> paraules = new ArrayList<>();
        if (!text.isEmpty()) {
            int i = 0;
            while (i < text.length()) {
                String paraula = "";
                while (i < text.length() && esUnCharCorrecte(text.charAt(i))) {
                    paraula += text.charAt(i);
                    ++i;
                }
                ++i;
                if (!paraula.isEmpty()) paraules.add(paraula.toString());
            }
        }
        return paraules;
    }

    private boolean esUnCharCorrecte(char c) {
        String simbols = " .,-;:_´`+¨^*{[]}!$%&/()=~|@#€¬";
        for (int i = 0; i < simbols.length(); ++i) {
            if (simbols.charAt(i) == c) return false;
        }
        return true;
    }

    public static void main (String[] args) {
        CtrlDirectori dir = new CtrlDirectori();
        dir.documentActiu = new Document(0,"Pol","Prova","arhu erpgq39 ´lrgha´ra ergerágiah ñr´g`ra08gear´8 gaergárga urga´r9a`r´a+ r`gpiaa.v");
        System.out.println(dir.obteContingut());
    }



    /**
     * exportarDocument exporta el document del directori a un path elegit
     * @param format es correspon en quin format es desitja exportar el document
     * @param path es correspon al camí desitjat per tal de guardar el document
     */
    public void exportarDocument(String format, String path) {
         if (new String("txt").equals(format)) {
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
         }
         else {
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
         }
    }
/*
    public static void main (String[] args) {
        CtrlDirectori dir = new CtrlDirectori();
        dir.documentActiu = new Document(0,"Pol","Prova","AIXÒ ÉS UNA PROVA");
        dir.exportarDocument("xml","C:\\Users\\polca\\OneDrive\\Escritorio\\PROPDocuments");
    }
    */


    /**
     * Elimina un document del directori
     * @param idDoc és l'identificador del document que es vol eliminar del directori
     */
    public void eliminarDocument(int idDoc) {
        //Comprovem que idDoc sigui realment un identificador d'un document
        if (!directoriObert.docs.containsKey(idDoc)) {
            System.err.println("ERROR: No existeix el document amb identificador: " + idDoc + ".");
            return;
        }

        directoriObert.docs.remove(idDoc);

        //TODO: eliminar els pesos del document

        //Afegim l'id a la cua per poder ser reciclada
        directoriObert.deletedIds.add(idDoc);
    }
}
