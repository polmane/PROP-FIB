package Domini.Controladors;



import Domini.Classes.Directori;
import Domini.Classes.Document;
import Domini.Classes.Pair;
import Persistencia.Classes.GestorDocument;
import Persistencia.Controladors.CtrlPersistencia;

import java.util.*;

public class CtrlDomini {

    private final CtrlDirectori _ctrlDirectori;
    private final CtrlExpressio _ctrlExpressio;
    private final CtrlPersistencia _ctrlPersistencia;

    public CtrlDomini(CtrlDirectori _ctrlDirectori, CtrlExpressio _ctrlExpressio, CtrlPersistencia ctrlPersistencia) {
        this._ctrlDirectori = _ctrlDirectori;
        this._ctrlExpressio = _ctrlExpressio;
        this._ctrlPersistencia = ctrlPersistencia;
    }

    public int afegirDocument(String autor, String titol, String contingut) {
        int i = _ctrlDirectori.afegirDocument(autor, titol, contingut);
        if (i > -1) {
            boolean b = _ctrlPersistencia.guardarContingutDocument(i, contingut);
            if (!b) return -50;
        }
        return i;
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
        int i = _ctrlDirectori.modificarContingut(contingut);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarContingutDocument(i,contingut);
            if (!b)return -50;
        }
        return i;
    }

    public List<Pair<String, String>> compararDocuments(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, Integer k, Integer IdDoc) {
        return _ctrlDirectori.compararDocuments(m, s, k, IdDoc);
    }

    public List<Pair<String, String>> compararQuery(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, Integer k, String paraules) {
        return _ctrlDirectori.compararQuery(m, s, k, paraules);
    }

    public int eliminarDocument(int idDoc) {
        int i = _ctrlDirectori.eliminarDocument(idDoc);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.eliminarDocument(idDoc);
            if (!b) return -50;
            }
        return i;
    }

    public String cercaPerAutoriTitol(String autor, String titol) {
        return _ctrlDirectori.cercaPerAutoriTitol(autor, titol);
    }

    public List<String> llistaAutorsPerPrefix(String pre, CtrlDirectori.SORTING s) {
        return _ctrlDirectori.llistaAutorsPerPrefix(pre, s);
    }

    public List<String> llistaTitolsPerAutor(String autor, CtrlDirectori.SORTING s) {
        return _ctrlDirectori.llistaTitolsPerAutor(autor, s);
    }

    public int seleccionarExpressio(Integer idExp) {
        return _ctrlExpressio.seleccionarExpressio(idExp);
    }

    public int afegirExpressio(String expressio) {
        return _ctrlExpressio.afegirExpressio(expressio);
    }

    public int modificarExpressio(String exp) {
        return _ctrlExpressio.modificarExpressio(exp);
    }

    public ArrayList<Document> selectPerExpressio(Integer idExp) {
        ArrayList<Document> resultat = new ArrayList<>();

        for (Document document : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            //TODO: carregar el contingut del document
            if (_ctrlExpressio.selectPerExpressio(idExp, document)) resultat.add(document);
            //TODO: eliminar el contingut del document
        }
        return resultat;
    }

    public int guardarEstat() {
        Directori dir = _ctrlDirectori.getDirectoriObert();
        HashMap<Integer, Pair<String, String>> docs = new HashMap<>();
        HashMap<Integer, Document> d = dir.getDocs();
        for (Map.Entry<Integer, Document> doc : d.entrySet()) {
            Pair p = new Pair(doc.getValue().getAutor(), doc.getValue().getTitol());
            docs.put(doc.getKey(), p);
        }
        Boolean i = _ctrlPersistencia.guardarEstat(dir.getIdDir(), dir.getPesosDocs(), dir.getDeletedIds(), dir.getIdNouDoc(), docs);
        if (!i) {
            return -50;
        }
        return 10;
    }
    public int exportarDocument(GestorDocument.FILETYPE format, String path) {
        Document d = _ctrlDirectori.getDocumentActiu();
        Boolean b = _ctrlPersistencia.exportarDocument(d.getAutor(),d.getTitol(),d.getContingut(),format,path);
        if (!b) return -50;
        return d.getIdDoc();
    }

    public int guardarExpressio (String expressio) {
        int i = _ctrlExpressio.afegirExpressio(expressio);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarExpressio(i,expressio);
            if (!b) return -50;
        }
        return i;
    }

    public int eliminarExpressio (int idExp) {
        int i = _ctrlExpressio.eliminarExpressio(idExp);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.eliminarExpressio(idExp);
            if (!b) return -50;
        }
        return i;
    }

    }
