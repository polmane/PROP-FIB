package Domini.Controladors;


import Domini.Classes.Directori;
import Domini.Classes.Document;
import Domini.Classes.Pair;

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
        if (documentActiu == null) return 31;
        if (autor == null || autor.isBlank()) {
            return 30;
        }
        for (Document document : directoriObert.getDocs().values()) {
            if (document.getIdDoc() == documentActiu.getIdDoc()) continue;
            if (documentActiu.getTitol().equalsIgnoreCase(document.getTitol()) && document.getAutor().equalsIgnoreCase(autor)) {
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
        if (documentActiu == null) return 31;
        if (titol == null || titol.isBlank()) {
            return 30;
        }
        for (Document document : directoriObert.getDocs().values()) {
            if (document.getIdDoc() == documentActiu.getIdDoc()) continue;
            if (documentActiu.getAutor().equalsIgnoreCase(document.getAutor()) && document.getTitol().equalsIgnoreCase(titol)) {
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
    public int modificarContingut(String contingut) {
        if (documentActiu == null) return 31;

        eliminarParaulesAlDir(documentActiu.getIdDoc());

        documentActiu.setContingut(contingut);

        documentActiu.setOcurrencies(obteContingut());
        documentActiu.setTfMap(tf(documentActiu.getOcurrencies()));

        afegeixParaulesAlDir();
        afegeixPesos();
        return 10;
    }

    /**
     * Afegir Document fa una alta d'un nou document dins el directori
     * @param autor representa l'autor del document que es vol afegir
     * @param titol representa el títol del document que es vol afegir
     * @param contingut representa el contingut del nou document
     */
    public int afegirDocument (String autor, String titol, String contingut) {
        if (autor == null || autor.isBlank() || titol == null || titol.isBlank()) return 30;
        for (Document doc : directoriObert.getDocs().values()) {
            if (doc.getAutor().equalsIgnoreCase(autor) && doc.getTitol().equalsIgnoreCase(titol)) {
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
            for (Document docs :directoriObert.getDocs().values())
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
        if (m == null || s == null || k <= 0 || !directoriObert.getDocs().containsKey(IdDoc) || directoriObert.getDocs().size() == 1)
            return null;
        ArrayList<Document> documentsSemblants = new ArrayList<>();
        ArrayList<Pair<Integer,Double>> helper = new ArrayList<>();
        for (Document doc : directoriObert.getDocs().values()) {
            double sumAB = 0.0;
            double A2 = 0.0;
            double B2 = 0.0;
            if (doc.getIdDoc() == IdDoc) continue;
            for (String word : directoriObert.getPesosDocs().get(IdDoc).keySet()) {
                double Aparaula;
                if (m == METODE_COMPARACIO.BOOL) Aparaula = 1.0;
                else Aparaula = directoriObert.getPesosDocs().get(IdDoc).get(word);
                double Bparaula = 0.0;
                if (directoriObert.getPesosDocs().get(doc.getIdDoc()).containsKey(word)) {
                    switch (m) {
                        case TF_IDF:
                            Bparaula = directoriObert.getPesosDocs().get(doc.getIdDoc()).get(word);
                            break;
                        case BOOL:
                            Bparaula = 1.0;
                            break;
                    }
                }
                sumAB += Aparaula * Bparaula;
                A2 += Math.pow(Aparaula,2);
                B2 += Math.pow(Bparaula,2);
            }
            for (String word : directoriObert.getPesosDocs().get(doc.getIdDoc()).keySet()) {
                if (!directoriObert.getPesosDocs().get(IdDoc).containsKey(word)) {
                    double Bparaula;
                    if (m == METODE_COMPARACIO.BOOL) Bparaula = 1.0;
                    else {
                        Bparaula = directoriObert.getPesosDocs().get(doc.getIdDoc()).get(word);
                    }
                    B2 += Math.pow(Bparaula,2);
                }
            }
            double similarity = 0.0;
            if (A2 != 0 && B2 != 0) {
                similarity = sumAB / (Math.sqrt(A2) * Math.sqrt(B2));
            }
            helper.add(new Pair<>(doc.getIdDoc(), similarity));
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

    public List<Pair<String, String>> compararQuery(METODE_COMPARACIO m, SORTING s, Integer k, String paraules) {
        if (m == null || s == null || k <= 0 || paraules == null || directoriObert.getDocs().size() == 0)
            return null;
        if (paraules.isEmpty())
            return null;

        afegirDocument("compararQuery","compararQuery", paraules);
        List<Pair<String, String>> equalQuery = compararDocuments(m,s,k, documentActiu.getIdDoc());
        eliminarDocument(documentActiu.getIdDoc());
        return equalQuery;
    }

    /**
     * Elimina un document del directori
     * @param idDoc és l'identificador del document que es vol eliminar del directori
     */
    public int eliminarDocument(int idDoc) {
        if (documentActiu == null) {
            return 31;
        }
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

        if (documentActiu != null && idDoc == documentActiu.getIdDoc()){
            documentActiu = null;
            return 11;
        }
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
        if (autor == null || titol == null || autor.isBlank() || titol.isBlank()) {
            return null;
        }
        for (Document doc : directoriObert.getDocs().values()) {
            if (doc.getTitol().equalsIgnoreCase(titol) && doc.getAutor().equalsIgnoreCase(autor)) {
                return doc.getContingut();
            }
        }
        return null;
    }

    public List<String> llistaAutorsPerPrefix(String pre, SORTING s) {
        if (pre == null) return null;
        List<String> autors = new ArrayList<String>();
        for (Document doc : directoriObert.getDocs().values()) {
            String autor = doc.getAutor();
            if (autor.toLowerCase().startsWith(pre.toLowerCase())) {
                autors.add(autor);
            }
        }
        if (autors.isEmpty())
            return null;
        if (s == SORTING.AUT_ASC) Collections.sort(autors);
        else if (s == SORTING.AUT_DESC) autors.sort(Collections.reverseOrder());
        return autors;
    }

    public List<String> llistaTitolsPerAutor(String autor, SORTING s) {
        if (autor == null || autor.isBlank()) return null;
        List<String> docs = new ArrayList<>();
        for (Document doc : directoriObert.getDocs().values()) {
            if (doc.getAutor().equalsIgnoreCase(autor)) {
                docs.add(doc.getTitol());
            }
        }
        if (docs.isEmpty())
            return null;
        if (s == SORTING.TIT_ASC) Collections.sort(docs);
        else if (s == SORTING.TIT_DESC) docs.sort(Collections.reverseOrder());
        return docs;
    }

    public ArrayList<String> llistarDocuments() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Document doc : directoriObert.getDocs().values()) {
            resultat.add(String.valueOf(doc.getIdDoc()));
            resultat.add(doc.getAutor());
            resultat.add(doc.getTitol());
        }
        return resultat;
    }

    public ArrayList<String>  toStringDocActiu() {
        ArrayList<String> resultat = new ArrayList<>();
        if (documentActiu != null) {
            resultat.add(String.valueOf(documentActiu.getIdDoc()));
            resultat.add(documentActiu.getAutor());
            resultat.add(documentActiu.getTitol());
            resultat.add(documentActiu.getContingut());
        }
        else {
            resultat.add("-31");
        }
        return resultat;
    }
}
