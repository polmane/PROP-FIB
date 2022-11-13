package FONTS.Controladors;

import FONTS.Classes.Document;
import FONTS.Classes.Expressio;
import FONTS.Classes.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CtrlDomini {

    private final CtrlDirectori _ctrlDirectori;
    private final CtrlExpressio _ctrlExpressio;

    public CtrlDomini(CtrlDirectori _ctrlDirectori, CtrlExpressio _ctrlExpressio) {
        this._ctrlDirectori = _ctrlDirectori;
        this._ctrlExpressio = _ctrlExpressio;
    }

    public void afegirDocument(String autor, String titol, String contingut) throws Exception {
        _ctrlDirectori.afegirDocument(autor, titol, contingut);
    }

    public void modificarContingut(String contingut) {
        _ctrlDirectori.modificarContingut(contingut);
    }

    public List<Pair<String, String>> compararDocuments(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s,Integer k, Integer IdDoc) {
        return _ctrlDirectori.compararDocuments(m, s, k,IdDoc);
    }

    /*public static void main(String[] args) throws Exception {
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

    public void exportarDocument(CtrlDirectori.FILETYPE format, String path) {
        _ctrlDirectori.exportarDocument(format,path);
    }

    public void eliminarDocument(int idDoc) throws Exception {
        _ctrlDirectori.eliminarDocument(idDoc);
    }

    /*public ArrayList<Document> cercaPerAutor(String autor) {
        //Com hem de retornar aixo?
        return _ctrlDirectori.cercaPerAutor(autor);
    }

    public ArrayList<Document> cercaPerTitol(String titol) {
        return _ctrlDirectori.cercaPerTitol(titol);
    }*/

    public String cercaPerAutoriTitol(String autor, String titol) {
        return _ctrlDirectori.cercaPerAutoriTitol(autor, titol);
    }

    public List<String> llistaAutorsPerPrefix(String pre) {
        return _ctrlDirectori.llistaAutorsPerPrefix(pre);
    }

    public List<String> llistaTitolsPerAutor(String autor) {
        return _ctrlDirectori.llistaTitolsPerAutor(autor);
    }

    public void afegirExpressio(String expressio) throws Exception {
        _ctrlExpressio.afegirExpressio(expressio);
    }

    public void modificarExpressio(String exp) {
        _ctrlExpressio.modificarExpressio(exp);
    }

    public void eliminarexpressio(int idExp) throws Exception {
        _ctrlExpressio.eliminarexpressio(idExp);
    }

    public ArrayList<Document> selectPerExpressio(Integer idExp) {
        HashMap<Integer, Document> docs = _ctrlDirectori.getDirectoriObert().getDocs();
        return _ctrlExpressio.selectPerExpressio(idExp, docs);
    }
}
