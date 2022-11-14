package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

import FONTS.Classes.Pair;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

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
     * @param idDoc és l'identificador del document que volem obrir
     */
    public int seleccionarDocument(int idDoc) {
        if (directoriObert.getDocs().containsKey(idDoc)) {
            documentActiu = directoriObert.getDocs().get(idDoc);
            return 10;
        }
        return 20;
    }

    /**
     * Modifica l'autor del document actiu
     *
     * @param autor és el nou nom d'autor que es vol utilitzar pel document
     */
    public int modificarAutor(String autor) {
        if (autor == null)
            return 30;
        if (autor.isEmpty())
            return 30;
        for (Document document : directoriObert.getDocs().values()) {
            if (document.getIdDoc() == documentActiu.getIdDoc()) continue;
            if (documentActiu.getTitol().equals(document.getTitol()) && document.getAutor().equals(autor)) {
                return 20;
            }
        }
        documentActiu.setAutor(autor);
        return 10;
    }

    /**
     * Modifica el títol del document actiu
     *
     * @param titol és el nou nom del títol que es vol utilitzar pel document
     */
    public int modificarTitol(String titol) {
        if (titol == null)
            return 30;
        if (titol.isEmpty())
            return 30;
        for (Document document : directoriObert.getDocs().values()) {
            if (document.getIdDoc() == documentActiu.getIdDoc()) continue;
            if (documentActiu.getAutor().equals(document.getAutor()) && document.getTitol().equals(titol)) {
                return 20;
            }
        }
        documentActiu.setTitol(titol);
        return 10;
    }

    /**
     * Modifica el contingut del document actiu
     *
     * @param contingut és el nou contingut que es vol utilitzar pel document
     */
    public void modificarContingut(String contingut) {
        eliminarParaulesAlDir(documentActiu.getIdDoc());

        documentActiu.setContingut(contingut);

        documentActiu.setOcurrencies(obteContingut());
        documentActiu.setTfMap(tf(documentActiu.getOcurrencies()));

        afegeixParaulesAlDir();
        afegeixPesos();
    }

    /**
     * Afegir Document fa una alta d'un nou document dins el directori
     * @param autor representa l'autor del document que es vol afegir
     * @param titol representa el títol del document que es vol afegir
     * @param contingut representa el contingut del nou document
     */
    public int afegirDocument (String autor, String titol, String contingut) {
        for (int i = 0; i < directoriObert.getIdNouDoc(); ++i) {
            if (directoriObert.getDocs().containsKey(i) && directoriObert.getDocs().get(i).getAutor().equals(autor) && directoriObert.getDocs().get(i).getTitol().equals(titol)) {
                return 20;
            }
        }
        int id;
        //Comprovem que no tenim cap id per reciclar
        if(!directoriObert.getDeletedIds().isEmpty()) {
            //En cas d'entrar aquí assignem la id que tenim a la cua
            id = directoriObert.getDeletedIds().poll();
        }
        else {
            //Si no hi ha ids per reciclar assignem la nova id al document
            id = directoriObert.getIdNouDoc();
            directoriObert.setIdNouDoc(id+1);
        }

        documentActiu = new Document(id, autor, titol, contingut);
        directoriObert.getDocs().put(id, documentActiu);

        documentActiu.setOcurrencies(obteContingut());
        documentActiu.setTfMap(tf(documentActiu.getOcurrencies()));
        afegeixParaulesAlDir();
        afegeixPesos();

        return 10;
    }

    private void afegeixParaulesAlDir() {
        for (String paraula : documentActiu.getOcurrencies().keySet()) {
            if (directoriObert.getParaulesDirectori().containsKey(paraula))
                directoriObert.getParaulesDirectori().put(paraula, directoriObert.getParaulesDirectori().get(paraula) + documentActiu.getOcurrencies().get(paraula));
            else directoriObert.getParaulesDirectori().put(paraula, documentActiu.getOcurrencies().get(paraula));
        }
    }

    private void afegeixPesos() {
        HashMap<String,Double> idfMap = idf();
        for (Document doc : directoriObert.getDocs().values()) {
            double tfIdfValue = 0.0;
            double idfVal = 0.0;
            HashMap<String,Double> tfMapHelper = new HashMap<>();
            for (Map.Entry<String, Double> stringDoubleEntry : doc.getTfMap().entrySet()) {
                double tfVal = (Double) stringDoubleEntry.getValue();
                if (idfMap.containsKey((String) stringDoubleEntry.getKey())) {
                    idfVal = idfMap.get((String) stringDoubleEntry.getKey());
                }
                tfIdfValue = tfVal * idfVal;
                tfMapHelper.put((stringDoubleEntry.getKey().toString()), tfIdfValue);
            }
            directoriObert.getPesosDocs().put(doc.getIdDoc(), tfMapHelper);
        }
    }

    private HashMap<String, Double> tf(HashMap<String, Integer> paraules) {
        HashMap<String,Double> tfMap = new HashMap<>();
        Double sum = 0.0;
        for (float val : paraules.values()) {
            sum += val;
        }
        for (Map.Entry pair : paraules.entrySet()) {
            double tf = (Integer) pair.getValue() / sum;
            tfMap.put((pair.getKey().toString()), tf);
        }
        return tfMap;
    }

    private HashMap<String, Double> idf() {
        HashMap<String,Double> idfMap = new HashMap<>();
        int size = directoriObert.getDocs().size();
        for (String word : directoriObert.getParaulesDirectori().keySet()) {
            double wordCount = 0;
            for(Document docs :directoriObert.getDocs().values())
            {
                if (docs.getOcurrencies().containsKey(word)) wordCount++;
            }
            double temp = size/ wordCount;
            Double idf = Math.log(1+temp);
            idfMap.put(word,idf);
        }
        return idfMap;
    }

    private HashMap<String, Integer> obteContingut() {
        String text = documentActiu.getContingut();
        HashMap<String,Integer> paraules = new HashMap<>();
        if (text != null && !text.isEmpty()) {
            int i = 0;
            while (i < text.length()) {
                StringBuilder paraula = new StringBuilder();
                while (i < text.length() && esUnCharCorrecte(text.charAt(i))) {
                    paraula.append(text.charAt(i));
                    ++i;
                }
                ++i;
                if (paraula.length() > 0) {
                    paraula = new StringBuilder(paraula.toString().toLowerCase());
                    if (paraules.containsKey(paraula.toString())) paraules.put(paraula.toString(),paraules.get(paraula.toString())+1);
                    else paraules.put(paraula.toString(), 1);
                }
            }
        }
        return paraules;
    }

    private boolean esUnCharCorrecte(char c) {
        String simbols = " .,'-;:_´`+¨^*{[]}!$%&/()=~|@#€¬";
        for (int i = 0; i < simbols.length(); ++i) {
            if (simbols.charAt(i) == c) return false;
        }
        return true;
    }

    public enum METODE_COMPARACIO {
        TF_IDF,
        BOOL,
    }

    public enum SORTING {
        SIM_ASC,
        SIM_DESC,
        AUT_ASC,
        AUT_DESC,
        TIT_ASC,
        TIT_DESC,
    }

    public List<Pair<String, String>> compararDocuments(METODE_COMPARACIO m, SORTING s, Integer k, Integer IdDoc) {
        if (m == null | s == null | k <= 0 | !directoriObert.getDocs().containsKey(IdDoc) | directoriObert.getDocs().size() == 1)
            return null;
        ArrayList<Document> documentsSemblants = new ArrayList<>();
        ArrayList<Pair<Integer,Double>> helper = new ArrayList<>();
        for (int i = 0; i < directoriObert.getDocs().size();++i) {
            double sumAB = 0.0;
            double A2 = 0.0;
            double B2 = 0.0;
            if (i == IdDoc) continue;
            for (String word : directoriObert.getPesosDocs().get(IdDoc).keySet()) {
                double Aparaula;
                if (m == METODE_COMPARACIO.BOOL) Aparaula = 1.0;
                else Aparaula = directoriObert.getPesosDocs().get(IdDoc).get(word);
                double Bparaula = 0.0;
                if (directoriObert.getPesosDocs().get(i).containsKey(word)) {
                    switch (m) {
                        case TF_IDF:
                            Bparaula = directoriObert.getPesosDocs().get(i).get(word);
                            break;
                        case BOOL:
                            Bparaula = 1.0;
                            break;
                    };
                }
                sumAB += Aparaula * Bparaula;
                A2 += Math.pow(Aparaula,2);
                B2 += Math.pow(Bparaula,2);
            }
            for (String word : directoriObert.getPesosDocs().get(i).keySet()) {
                if (!directoriObert.getPesosDocs().get(IdDoc).containsKey(word)) {
                    double Bparaula;
                    if (m == METODE_COMPARACIO.BOOL) Bparaula = 1.0;
                    else {
                        Bparaula = directoriObert.getPesosDocs().get(i).get(word);
                    }
                    B2 += Math.pow(Bparaula,2);
                }
            }
            double similarity = 0.0;
            if (A2 != 0 && B2 != 0) {
                similarity = sumAB / (Math.sqrt(A2) * Math.sqrt(B2));
            }
            helper.add(new Pair<>(directoriObert.getDocs().get(i).getIdDoc(), similarity));
        }
        helper.sort(comparing(Pair::second));

        for (int i = 0; i < min(k, helper.size()); ++i) {
            documentsSemblants.add(directoriObert.getDocs().get(helper.get(i).first()));
        }

        List<Pair<String,String>> llistaSemblants= documentsSemblants.stream()
                .map(document -> new Pair<String, String>(document.getAutor(), document.getTitol()))
                .collect(Collectors.toList());
        sortLlista(llistaSemblants,s);
        return llistaSemblants;
    }

    private void sortLlista(List<Pair<String, String>> llistaSemblants, SORTING s) {
        switch(s) {
            case SIM_DESC:
                Collections.reverse(llistaSemblants);
                break;
            case SIM_ASC:
                break;
            case AUT_DESC:
                Comparator<Pair<String, String>> c1 = reverseOrder(comparing(Pair::first));
                llistaSemblants.sort(c1);
                break;
            case AUT_ASC:
                llistaSemblants.sort(comparing(Pair::first));
                break;
            case TIT_DESC:
                Comparator<Pair<String, String>> c2 = reverseOrder(comparing(Pair::second));
                llistaSemblants.sort(c2);
                break;
            case TIT_ASC:
                llistaSemblants.sort(comparing(Pair::second));
        }
    }

    //TODO: TEST
    public List<Pair<String, String>> compararQuery(METODE_COMPARACIO m, SORTING s, Integer k, ArrayList<String> paraules) {
        if (m == null | s == null | k <= 0 | paraules == null | directoriObert.getDocs().size() == 0)
            return null;
        if (paraules.isEmpty())
            return null;
        String result = "";
        for (String paraula: paraules) {
            result += paraula + " ";
        }
        afegirDocument("compararQuery","compararQuery", result);
        List<Pair<String, String>> equalQuery = compararDocuments(m,s,k, documentActiu.getIdDoc());
        eliminarDocument(documentActiu.getIdDoc());
        return equalQuery;
    }

    public enum FILETYPE {
        TXT, XML
    }

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

    /**
     * Elimina un document del directori
     * @param idDoc és l'identificador del document que es vol eliminar del directori
     */
    public int eliminarDocument(int idDoc) {
        //Comprovem que idDoc sigui realment un identificador d'un document
        if (!directoriObert.getDocs().containsKey(idDoc)) {
            return 20;
        }

        eliminarParaulesAlDir(idDoc);
        directoriObert.getPesosDocs().remove(idDoc);

        directoriObert.getDocs().remove(idDoc);

        afegeixPesos();

        //Afegim l'id a la cua per poder ser reciclada
        directoriObert.getDeletedIds().add(idDoc);

        return 10;
    }

    private void eliminarParaulesAlDir(int idDoc) {
        Document doc = directoriObert.getDocs().get(idDoc);

        for (Map.Entry<String,Integer> it : doc.getOcurrencies().entrySet()) {
            directoriObert.getParaulesDirectori().put(it.getKey(),directoriObert.getParaulesDirectori().get(it.getKey())-it.getValue());
            if (directoriObert.getParaulesDirectori().get(it.getKey()) == 0) directoriObert.getParaulesDirectori().remove(it.getKey());
        }
    }

    public String cercaPerAutoriTitol(String autor, String titol) {
        if (autor == null | titol == null)
            return null;
        if (autor.isEmpty() | titol.isEmpty())
            return null;
        for (int i = 0; i < directoriObert.getDocs().size(); ++i) {
            if (directoriObert.getDocs().containsKey(i) && directoriObert.getDocs().get(i).getTitol().equals(titol) && directoriObert.getDocs().get(i).getAutor().equals(autor)) {
                return directoriObert.getDocs().get(i).getContingut();
            }
        }
        return null;
    }

    public List<String> llistaAutorsPerPrefix(String pre, SORTING s) {
        if (pre == null)
            return null;
        List<String> autors = new ArrayList<String>();
        for (int i = 0; i < directoriObert.getDocs().size(); ++i) {
            if (directoriObert.getDocs().containsKey(i)) {
                String autor = directoriObert.getDocs().get(i).getAutor();
                if (autor.startsWith(pre)) {
                    autors.add(autor);
                }
            }
        }
        if (autors.isEmpty())
            return null;
        if (s == SORTING.AUT_ASC) Collections.sort(autors);
        else if (s == SORTING.AUT_DESC) Collections.sort(autors,Collections.reverseOrder());
        return autors;
    }

    public List<String> llistaTitolsPerAutor(String autor, SORTING s) {
        if (autor == null)
            return null;
        if (autor.isEmpty())
            return null;
        List<String> docs = new ArrayList<String>();
        for (int i = 0; i < directoriObert.getDocs().size(); ++i) {
            if (directoriObert.getDocs().containsKey(i) && directoriObert.getDocs().get(i).getAutor().equals(autor)) {
                docs.add(directoriObert.getDocs().get(i).getTitol());
            }
        }
        if (docs.isEmpty())
            return null;
        if (s == SORTING.TIT_ASC) Collections.sort(docs);
        else if (s == SORTING.TIT_DESC) Collections.sort(docs,Collections.reverseOrder());
        return docs;
    }
}
