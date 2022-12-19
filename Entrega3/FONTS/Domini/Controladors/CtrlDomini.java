package Domini.Controladors;



import Domini.Classes.Directori;
import Domini.Classes.Document;
import Domini.Classes.Expressio;
import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Controladors.CtrlPersistencia;

import java.lang.reflect.Array;
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
        _ctrlDirectori.getDocumentActiu().setContingut(null);
        int i = _ctrlDirectori.seleccionarDocument(idDoc);
        if (i > -1) {
            String contingut = _ctrlPersistencia.carregarContingutDocument(idDoc);
            if (contingut.equals("$ERROR: no s'ha pogut llegir el contingut del document correctament")) return -50;
            _ctrlDirectori.getDocumentActiu().setContingut(contingut);
        }
        return i;
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

    public int modificarExpressio(String exp) {
        int i = _ctrlExpressio.modificarExpressio(exp);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarExpressio(i,exp);
            if (!b) return -50;
        }
        return i;
    }

    public ArrayList<Document> selectPerExpressio(Integer idExp) {
        ArrayList<Document> resultat = new ArrayList<>();

        for (Document document : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            document.setContingut(_ctrlPersistencia.carregarContingutDocument(document.getIdDoc()));
            if (_ctrlExpressio.selectPerExpressio(idExp, document)) resultat.add(document);
            document.setContingut(null);
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
        return -10;
    }

    public int carregarEstat() {
        Pair<GestorBD.Estat, ArrayList<Pair<Integer, String>>> resultat = _ctrlPersistencia.carregarEstat();
        GestorBD.Estat estat = resultat.first();
        ArrayList<Pair<Integer, String>> expressionsArray = resultat.second();

        if (estat == null | expressionsArray == null)
            return -50;

        HashMap<Integer, Expressio> expressionsHashMap = new HashMap<>();
        for (Pair<Integer, String> expPair : expressionsArray) {
            Expressio exp = new Expressio(expPair.first(), expPair.second());

            expressionsHashMap.put(expPair.first(), exp);
        }
        _ctrlExpressio.setExpressions(expressionsHashMap);

        _ctrlDirectori.crearDirectori(estat.idDir);
        _ctrlDirectori.getDirectoriObert().setDeletedIds(estat.deletedIds);
        _ctrlDirectori.getDirectoriObert().setIdNouDoc(estat.idNouDoc);
        HashMap<Integer, String> continguts = new HashMap<>();
        for (int i = 0; i < estat.idNouDoc; ++i) {
            if (estat.docs.containsKey(i)) {
                String contingut = _ctrlPersistencia.carregarContingutDocument(i);
                //FIXME: HAURIEM DE PASSAR L'ERROR AMB NULL
                if (contingut.equals("$ERROR: no s'ha pogut llegir el contingut del document correctament"))
                    return -50;
                continguts.put(i, contingut);
            }
        }
        _ctrlDirectori.carregarDocs(estat.pesosDocs, estat.docs, continguts);

        return -10;
    }

    public int exportarDocument(GestorDocument.FILETYPE format, String path) {
        Document d = _ctrlDirectori.getDocumentActiu();
        Boolean b = _ctrlPersistencia.exportarDocument(d.getAutor(),d.getTitol(),d.getContingut(),format,path);
        if (!b) return -50;
        return d.getIdDoc();
    }

    public int importarDocument(String path) {
        ArrayList<String> values = _ctrlPersistencia.importarDocument(path);
        if (values == null)
            return -50;

        return _ctrlDirectori.afegirDocument(values.get(0), values.get(1), values.get(2));
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
