package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

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
    //TODO: Falta fer el test d'aquesta operació
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
            id = directoriObert.getIdNouDoc();
            directoriObert.setIdNouDoc(id+1);
        }
        documentActiu = new Document(id, autor, titol, contingut);
        directoriObert.docs.put(id, documentActiu);

        //TODO: afegir document a la taula de pesos
        HashMap<String,Integer> paraules = obteContingut();
        afegeixParaulesAlDir(paraules);
        afegeixPesos();
    }

    private void afegeixParaulesAlDir(HashMap<String, Integer> paraules) {
        for (String paraula : paraules.keySet()) {
            if (directoriObert.paraulesDirectori.containsKey(paraula))
                directoriObert.paraulesDirectori.put(paraula, directoriObert.paraulesDirectori.get(paraula) + paraules.get(paraula));
            else directoriObert.paraulesDirectori.put(paraula, paraules.get(paraula));
        }
    }

    private void afegeixPesos() {
        HashMap<String,Double> idfMap = idf();
        for (Document doc : directoriObert.docs.values()) {
            documentActiu = doc;
            HashMap<String,Double> tfMap = tf(obteContingut());
            double tfIdfValue = 0.0;
            double idfVal = 0.0;
            Iterator itTF = tfMap.entrySet().iterator();
            while (itTF.hasNext()) {
                Map.Entry pair = (Map.Entry)itTF.next();
                double tfVal  = (Double)pair.getValue() ;
                if(idfMap.containsKey((String)pair.getKey()))
                {
                    idfVal = idfMap.get((String)pair.getKey());
                }
                tfIdfValue = tfVal *idfVal;
                tfMap.put((pair.getKey().toString()),tfIdfValue);
                directoriObert.pesosDocs.put(doc.getIdDoc(),tfMap);
            }
        }
    }

    private HashMap<String, Double> tf(HashMap<String, Integer> paraules) {
        HashMap<String,Double> tfMap = new HashMap<>();
        double sum = 0.0;
        Iterator it = paraules.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            double tf = (Integer)pair.getValue()/ (double) directoriObert.paraulesDirectori.size();
            tfMap.put((pair.getKey().toString()),tf);
        }
        return tfMap;
    }

    private HashMap<String, Double> idf() {
        HashMap<String,Double> idfMap = new HashMap<>();
        int size = directoriObert.docs.size();
        for (String word : directoriObert.paraulesDirectori.keySet()) {
            Double temp = size/ Double.valueOf(directoriObert.paraulesDirectori.get(word));
            Double idf = 1 + Math.log(temp);
            idfMap.put(word,idf);
        }
        return idfMap;
    }

    private HashMap<String, Integer> obteContingut() {
        String text = documentActiu.getContingut();
        HashMap<String,Integer> paraules = new HashMap<>();
        if (!text.isEmpty()) {
            int i = 0;
            while (i < text.length()) {
                String paraula = "";
                while (i < text.length() && esUnCharCorrecte(text.charAt(i))) {
                    paraula += text.charAt(i);
                    ++i;
                }
                ++i;
                if (!paraula.isEmpty()) {
                    paraula = paraula.toLowerCase();
                    if (paraules.containsKey(paraula)) paraules.put(paraula,paraules.get(paraula)+1);
                    else paraules.put(paraula, 1);
                }
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

    public static void main (String[] args) throws Exception {
        CtrlDirectori dir = new CtrlDirectori();
        dir.directoriObert = new Directori(0);
        dir.afegirDocument("Pol","Prova","El cotxe blau");
        dir.afegirDocument("Manel","Prova","El cotxe negre");
        dir.afegirDocument("Isaac","Prova","El cotxe vermell");
        dir.afegirDocument("Juli","Prova","El cotxe lila");
        dir.afegirDocument("Pau","Prova","La casa gran");
        dir.afegirDocument("Joan","Prova","Avui fa fred");
        dir.afegirDocument("Jordi","Prova","La moto maca");
        dir.afegirDocument("Pep","Prova","Tinc molta gana");
        dir.afegirDocument("Carles","Prova","La bici gran");

        System.out.println(dir.directoriObert.paraulesDirectori);

        for (Integer num : dir.directoriObert.getPesosDocs().keySet()) {
            System.out.println(dir.directoriObert.getPesosDocs().get(num));
        }
    }

    public enum FILETYPE {
        TXT, XML, PROP
    }

    /**
     * exportarDocument exporta el document del directori a un path elegit
     * @param format es correspon en quin format es desitja exportar el document
     * @param path es correspon al camí desitjat per tal de guardar el document
     */
    //TODO: TEST
    public void exportarDocument(@NotNull FILETYPE format, @NotNull String path) {
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
            case PROP:
                //TODO farem servir aquesta funció per a la persistència?
        }
    }

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

    //TODO: TEST
    public ArrayList<Document> cercaPerAutor(@NotNull String autor) {
        ArrayList<Document> docs = new ArrayList<Document>();
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.docs.containsKey(i) && directoriObert.docs.get(i).getAutor().equals(autor)) {
                docs.add(directoriObert.docs.get(i));
            }
        }
        return docs;
    }

    //TODO: TEST
    public ArrayList<Document> cercaPerTitol(@NotNull String titol) {
        ArrayList<Document> docs = new ArrayList<Document>();
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.docs.containsKey(i) && directoriObert.docs.get(i).getTitol().equals(titol)) {
                docs.add(directoriObert.docs.get(i));
            }
        }
        return docs;
    }

    //TODO: TEST
    public List<String> llistaAutorsPerPrefix(@NotNull String pre) {
        List<String> autors = new ArrayList<String>();
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.docs.containsKey(i)) {
                String autor = directoriObert.docs.get(i).getAutor();
                if (autor.startsWith(pre)) {
                    autors.add(autor);
                }
            }
        }
        return autors;
    }

    //TODO: TEST
    public List<String> llistaTitolsPerAutor(@NotNull String autor) {
        List<String> docs = new ArrayList<String>();
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.docs.containsKey(i) && directoriObert.docs.get(i).getAutor().equals(autor)) {
                docs.add(directoriObert.docs.get(i).getTitol());
            }
        }
        Collections.sort(docs);
        return docs;
    }

}
