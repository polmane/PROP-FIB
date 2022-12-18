package Domini.Controladors;



import Domini.Classes.Document;
import Domini.Classes.Pair;
import Persistencia.Controladors.CtrlPersistencia;

import java.util.ArrayList;
import java.util.List;

public class CtrlDomini {

    private final CtrlDirectori _ctrlDirectori;
    private final CtrlExpressio _ctrlExpressio;
    private final CtrlPersistencia _ctrlPersistencia;

    public CtrlDomini(CtrlDirectori _ctrlDirectori, CtrlExpressio _ctrlExpressio, CtrlPersistencia ctrlPersistencia) {
        this._ctrlDirectori = _ctrlDirectori;
        this._ctrlExpressio = _ctrlExpressio;
        this._ctrlPersistencia = ctrlPersistencia;
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

    public List<Pair<String, String>> compararDocuments(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, Integer k, Integer IdDoc) {
        return _ctrlDirectori.compararDocuments(m, s, k, IdDoc);
    }

    public List<Pair<String, String>> compararQuery(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, Integer k, String paraules) {
        return _ctrlDirectori.compararQuery(m, s, k, paraules);
    }

    /*
    public void exportarDocument(CtrlDirectori.FILETYPE format, String path) {
        _ctrlDirectori.exportarDocument(format,path);
    } */

    public int eliminarDocument(int idDoc){
        return _ctrlDirectori.eliminarDocument(idDoc);
    }

    public String cercaPerAutoriTitol(String autor, String titol) {
        return _ctrlDirectori.cercaPerAutoriTitol(autor, titol);
    }

    public List<String> llistaAutorsPerPrefix(String pre , CtrlDirectori.SORTING s) {
        return _ctrlDirectori.llistaAutorsPerPrefix(pre, s);
    }

    public List<String> llistaTitolsPerAutor(String autor, CtrlDirectori.SORTING s) {
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
}
