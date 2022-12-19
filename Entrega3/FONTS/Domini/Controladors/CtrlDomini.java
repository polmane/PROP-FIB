package Domini.Controladors;



import Domini.Classes.Document;
import Domini.Classes.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CtrlDomini {

    public CtrlDirectori _ctrlDirectori;
    private final CtrlExpressio _ctrlExpressio;

    public CtrlDomini() {
        _ctrlExpressio = new CtrlExpressio();
        _ctrlDirectori = new CtrlDirectori();
    }
    public CtrlDomini(CtrlDirectori _ctrlDirectori, CtrlExpressio _ctrlExpressio) {
        this._ctrlDirectori = _ctrlDirectori;
        this._ctrlExpressio = _ctrlExpressio;
    }

    public int afegirDocument(String autor, String titol, String contingut){
        return _ctrlDirectori.afegirDocument(autor, titol, contingut);
    }

    public int seleccionarDocument(int idDoc) {
        return _ctrlDirectori.seleccionarDocument(idDoc);
    }

    public int modificarAutor(String autor) {
        return _ctrlDirectori.modificarAutor(autor);
    }

    public int modificarTitol(String titol) {
        return _ctrlDirectori.modificarAutor(titol);
    }

    public int modificarContingut(String contingut) {
        return _ctrlDirectori.modificarContingut(contingut);
    }

    public List<Pair<String, String>> compararDocuments(String metodeComp, String sorting, Integer k, Integer IdDoc) {
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metodeComp);
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.compararDocuments(m, s, k, IdDoc);
    }

    public List<Pair<String, String>> compararQuery(String metodeComp, String sorting, Integer k, String paraules) {
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metodeComp);
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.compararQuery(m, s, k, paraules);
    }

    public int eliminarDocument(int idDoc){
        return _ctrlDirectori.eliminarDocument(idDoc);
    }

    public String cercaPerAutoriTitol(String autor, String titol) {
        return _ctrlDirectori.cercaPerAutoriTitol(autor, titol);
    }

    public List<String> llistaAutorsPerPrefix(String pre , String sorting) {
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.llistaAutorsPerPrefix(pre, s);
    }

    public List<String> llistaTitolsPerAutor(String autor, String sorting) {
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.llistaTitolsPerAutor(autor,s);
    }

    public int seleccionarExpressio (Integer idExp) {
        return _ctrlExpressio.seleccionarExpressio(idExp);
    }

    public int afegirExpressio(String expressio){
        return _ctrlExpressio.afegirExpressio(expressio);
    }

    public int modificarExpressio(String exp){
        return _ctrlExpressio.modificarExpressio(exp);
    }

    public int eliminarExpressio(int idExp){
        return _ctrlExpressio.eliminarExpressio(idExp);
    }

    public ArrayList<Document> selectPerExpressio(Integer idExp) {
        ArrayList<Document> resultat = new ArrayList<>();

        for (Document document : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            if(_ctrlExpressio.selectPerExpressio(idExp, document)) resultat.add(document);
        }
        return resultat;
    }

    public ArrayList<String> llistarDocuments() {
        return _ctrlDirectori.llistarDocuments();
    }
    public ArrayList<String> llistarExpressions() {
        return _ctrlExpressio.llistarExpressions();
    }

    public ArrayList<String> getIdDocSeleccionat() {
        return _ctrlDirectori.getIdDocSeleccionat();
    }

    public int getIdExp() {
        return _ctrlExpressio.getIdExp();
    }

    /*public static void main (String[] args){
        CtrlDirectori dir = new CtrlDirectori();
        CtrlExpressio exp = new CtrlExpressio();
        CtrlDomini cdom = new CtrlDomini(dir, exp);
        cdom._ctrlDirectori.crearDirectori(0);


        cdom.afegirDocument("Pol","Prova","A A A A A");
        cdom.afegirDocument("Manel","Prova","hola, el barri gotic de girona");
        cdom.afegirDocument("Isaac","Prova","fem un projecte de programació");
        cdom.afegirDocument("Juli","Prova","la nit es a molt llarga");
        cdom.afegirDocument("Pau","Prova","de de de de de de");
        cdom.afegirDocument("Joan","Prova","el programa em peta i no se per on");
        cdom.afegirDocument("Jordi","Prova","dema faig un viatge barcelona");
        cdom.afegirDocument("Pep","Prova",    "la meva casa es d'estil gotic");
        cdom.afegirDocument("Carles","Prova","A A A A A");
        cdom.afegirDocument("Anna","Prova","B barri");
        cdom.afegirDocument("Marta","Prova","B el");

        System.out.println(cdom.llistarDocuments());

        cdom.afegirExpressio("(hola & (\"barri gotic\"))");

        ArrayList<Document> docfinal = cdom.selectPerExpressio(0);

        for(Document d : docfinal) {
            System.out.println("El document: " + d.getAutor() + " i " + d.getTitol());
        }
        System.out.println(cdom.llistarExpressions());
    }*/
        /*public static void main(String[] args){
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDomini CtrlDom = new CtrlDomini(CtrlDir,null);
        CtrlDir.crearDirectori(0);

        CtrlDom.afegirDocument("Pol","Prova","A A A A A");
        CtrlDom.afegirDocument("Manel","Prova","el barri gotic de girona");
        CtrlDom.afegirDocument("Isaac","Prova","fem un projecte de programació");
        CtrlDom.afegirDocument("Juli","Prova","A A A A A");
        CtrlDom.afegirDocument("Pau","Prova","de de de de de de");
        CtrlDom.afegirDocument("Joan","Prova","el a a programa em peta i no se per on");
        CtrlDom.afegirDocument("Jordi","Prova","dema faig un viatge barcelona");
        CtrlDom.afegirDocument("Pep","Prova",    "la a a a meva casa es d'estil gotic");
        CtrlDom.afegirDocument("Carles","Prova","la nit es a molt llarga");

        System.out.println(CtrlDom.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.SIM_DESC,2,0));
    }*/

}
