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

    public List<Pair<String, String>> compararDocuments(Integer k, Integer IdDoc) {
        List<Document> resultat = _ctrlDirectori.compararDocuments(k,IdDoc);
        return resultat.stream()
                .map(document -> new Pair<String, String>(document.autor, document.titol))
                .collect(Collectors.toList());
    }

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

    public static void main (String[] args) throws Exception {
        CtrlDirectori dir = new CtrlDirectori();
        CtrlExpressio exp = new CtrlExpressio();
        CtrlDomini cdom = new CtrlDomini(dir, exp);
        cdom._ctrlDirectori.crearDirectori(0);


        cdom.afegirDocument("Pol","Prova","A A A A A");
        cdom.afegirDocument("Manel","Prova","el barri gotic de girona");
        cdom.afegirDocument("Isaac","Prova","fem un projecte de programació");
        cdom.afegirDocument("Juli","Prova","la nit es a molt llarga");
        cdom.afegirDocument("Pau","Prova","de de de de de de");
        cdom.afegirDocument("Joan","Prova","el programa em peta i no se per on");
        cdom.afegirDocument("Jordi","Prova","dema faig un viatge barcelona");
        cdom.afegirDocument("Pep","Prova",    "la meva casa es d'estil gotic");
        cdom.afegirDocument("Carles","Prova","A A A A A");
        cdom.afegirDocument("Anna","Prova","B");
        cdom.afegirDocument("Marta","Prova","B el");

        cdom.afegirExpressio("B & (el | barri)");

        ArrayList<Document> docfinal = cdom.selectPerExpressio(0);

        for(Document d : docfinal) {
            System.out.println("El document: " + d.getAutor() + " i " + d.getTitol());
        }
    }
}
