package Domini.Controladors;



import Domini.Classes.Directori;
import Domini.Classes.Document;
import Domini.Classes.Expressio;
import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Controladors.CtrlPersistencia;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


/**
 * Representa un Controlador de Domini
 * @author pol.camprubi.prats
 * @author juli.serra.balaguer
 */
public class CtrlDomini {
    /**
     * Representa un Controlador de Directori
     */
    private final CtrlDirectori _ctrlDirectori;
    /**
     * Representa un Controlador d'Expressió
     */
    private final CtrlExpressio _ctrlExpressio;
    /**
     * Representa un Controlador de Persistència
     */
    private final CtrlPersistencia _ctrlPersistencia;

    /**
     * Getter del controlador de directori
     * @return retorna el controlador de directori
     */
    public CtrlDirectori get_ctrlDirectori() {
        return _ctrlDirectori;
    }

    /**
     * Getter del controlador d'expressió
     * @return retorna el controlador d'expressió
     */
    public CtrlExpressio get_ctrlExpressio() {
        return _ctrlExpressio;
    }

    /**
     * Getter del controlador de persistència
     * @return retorna el controlador de persistència
     */
    public CtrlPersistencia get_ctrlPersistencia() {
        return _ctrlPersistencia;
    }

    /**
     * Constructora del controlador de domini
     */
    public CtrlDomini() {
        this._ctrlDirectori = new CtrlDirectori();
        this._ctrlExpressio = new CtrlExpressio();
        this._ctrlPersistencia = new CtrlPersistencia();
    }

    /**
     * Funció per crear un directori a partir d'una id
     * @param idDir id del nou directori
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public int crearDirectori(int idDir) {
        if(_ctrlDirectori.crearDirectori(idDir)) return -10;
        return -50;
    }

        /**
         * Funció que permet afegir documents en el nostre sistema
         * @param autor nom de l'autor del document
         * @param titol nom del títol del document
         * @param contingut nom del contingut del document
         * @return consultar els codis de return en el document word entregat, la id del document creat en cas de funcionament correcte
         */
    public int afegirDocument(String autor, String titol, String contingut) {
        if (_ctrlDirectori.getDocumentActiu() != null) _ctrlDirectori.getDocumentActiu().setContingut(null);
        int i = _ctrlDirectori.afegirDocument(autor, titol, contingut);
        if (i > -1) {
            boolean b = _ctrlPersistencia.guardarContingutDocument(i, contingut);
            if (!b) return -50;
        }
        return i;
    }

    /**
     * Funció per seleccionar el document actiu
     * @param idDoc id del document a seleccionar
     * @return consultar els codis de return en el document word entregat, la id del document creat en cas de funcionament correcte
     */
    public int seleccionarDocument(int idDoc) {
        if (_ctrlDirectori.getDocumentActiu() != null) _ctrlDirectori.getDocumentActiu().setContingut(null);
        int i = _ctrlDirectori.seleccionarDocument(idDoc);
        if (i > -1) {
            String contingut = _ctrlPersistencia.carregarContingutDocument(idDoc);
            if (contingut == null) return -50;
            _ctrlDirectori.getDocumentActiu().setContingut(contingut);
        }
        return i;
    }

    /**
     * Funció per modificar l'autor del document actiu
     * @param autor nou valor de la variable autor
     * @return consultar els codis de return en el document word entregat
     */
    public int modificarAutor(String autor) {
        return _ctrlDirectori.modificarAutor(autor);
    }

    /**
     * Funció per modificar el títol del document actiu
     * @param titol nou valor de la variable títol
     * @return consultar els codis de return en el document word entregat
     */
    public int modificarTitol(String titol) {
        return _ctrlDirectori.modificarTitol(titol);
    }

    /**
     * Funció per modificar el contingut del document actiu
     * @param contingut nou valor de la variable títol
     * @return consultar els codis de return en el document word entregat, la id del document modificat en cas de funcionament correcte
     */
    public int modificarContingut(String contingut) {
        int i = _ctrlDirectori.modificarContingut(contingut);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarContingutDocument(i,contingut);
            if (!b)return -50;
        }
        return i;
    }

    /**
     * Funció que permet comparar documents i obtenir-ne el nivell de similaritat
     * @param metodeComp mètode de comparació (enum METODE_COMPARACIO)
     * @param sorting mètode d'ordenació del resultat (enum SORTING)
     * @param k nombre de documents similars a trobar
     * @param IdDoc identificador del document que s'utilitarà per fer les comparacions
     * @return retorna una llista de pairs on el primer valor és el nom de l'autor i el segon el títol del document semblant
     */
    public List<Pair<String, String>> compararDocuments(String metodeComp, String sorting, Integer k, Integer IdDoc) {
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metodeComp);
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.compararDocuments(m, s, k, IdDoc);
    }
    /**
     * Funció que permet comparar els documents del sistema amb una query de paraules entrada per l'usuari
     * @param metodeComp mètode de comparació (enum METODE_COMPARACIO)
     * @param sorting mètode d'ordenació (enum SORTING)
     * @param k nombre de documents similars a trobar
     * @param paraules query de paraules a comparar
     * @return retorna una llista de pairs on el primer valor és el nom de l'autor i el segon el títol del document semblant
     */
    public List<Pair<String, String>> compararQuery(String metodeComp, String sorting, Integer k, String paraules) {
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metodeComp);
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.compararQuery(m, s, k, paraules);
    }

    /**
     * Funció per eliminar un document del directori
     * @param idDoc id del document a eliminar
     * @return consultar els codis de return en el document word entregat, la id del document eliminat si no hi han errors
     */
    public int eliminarDocument(int idDoc) {
        int i = _ctrlDirectori.eliminarDocument(idDoc);
        if (i > -1 | i == -10 | i == -11) {
            Boolean b = _ctrlPersistencia.eliminarDocument(idDoc);
            if (!b) return -50;
            }
        return i;
    }

    /**
     * Funció per trobat el contingut donar un autor i títol
     * @param autor valor de la variable autor a buscar
     * @param titol valor de la variable títol a buscar
     * @return retorna el contingut del document autor+títol, null en cas que aquest document no existeixi
     */
    public String cercaPerAutoriTitol(String autor, String titol) {
        if (autor == null || titol == null || autor.isBlank() || titol.isBlank()) {
            return null;
        }
        for (Document doc : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            if (doc != _ctrlDirectori.getDocumentActiu()) doc.setContingut(_ctrlPersistencia.carregarContingutDocument(doc.getIdDoc()));
            if (doc.getTitol().equalsIgnoreCase(titol) && doc.getAutor().equalsIgnoreCase(autor)) {
                return doc.getContingut();
            }
            if (doc != _ctrlDirectori.getDocumentActiu()) doc.setContingut(null);
        }
        return null;
    }

    /**
     * Funció per llistar els autors del nostre sistema que començen per un prefix determinat
     * @param pre prefix pel que buscar els autors similars
     * @param sorting mètode d'ordenació (enum SORTING)
     * @return retorna una llista d'autors
     */
    public List<String> llistaAutorsPerPrefix(String pre , String sorting) {
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.llistaAutorsPerPrefix(pre, s);
    }
    /**
     * Funció per llistar els títols del nostre sistema que començen per un autor determinat
     * @param autor autor pel que buscar els titols
     * @param sorting mètode d'ordenació (enum SORTING)
     * @return retorna una llista de títols
     */
    public List<String> llistaTitolsPerAutor(String autor, String sorting) {
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(sorting);
        return _ctrlDirectori.llistaTitolsPerAutor(autor,s);
    }

    /**
     * Funció per seleccionar l'expressió seleccionada
     * @param idExp id del document a seleccionar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió creada en cas de funcionament correcte
     */
    public int seleccionarExpressio(Integer idExp) {
        return _ctrlExpressio.seleccionarExpressio(idExp);
    }

    /**
     * Funció per modificar l'expressió seleccionada
     * @param exp nou valor de la variable expressió
     * @return consultar els codis de return en el document word entregat, la id de l'expressió modificada en cas de funcionament correcte
     */
    public int modificarExpressio(String exp) {
        int i = _ctrlExpressio.modificarExpressio(exp);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarExpressio(i,exp);
            if (!b) return -50;
        }
        return i;
    }

    /**
     * Funció per seleccionar els document que compleixen una expressió
     * @param idExp id de l'expressió a comparar
     * @return retorna un vector dels documents que compleixen l'expressió
     */
    public List<Pair<String, String>> selectPerExpressio(Integer idExp) {
        List<Pair<String, String>> resultat = new ArrayList<>();

        for (Document document : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            if (document != _ctrlDirectori.getDocumentActiu())document.setContingut(_ctrlPersistencia.carregarContingutDocument(document.getIdDoc()));
            if(_ctrlExpressio.selectPerExpressio(idExp, document)) resultat.add(new Pair<>(document.getAutor(), document.getTitol()));
            if (document != _ctrlDirectori.getDocumentActiu()) document.setContingut(null);
        }
        return resultat;
    }
   
    /**
     * Funció que permet guardar l'estat del programa un cop l'usuari tanca l'app
     * @return consultar els codis de return en el document word entregat
     */
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

    /**
     * Funció per carregar l'estat del programa un cop es torna a executar el codi
     * @return consultar els codis de return en el document word entregat
     */
    public int carregarEstat() {
        GestorBD.Estat estat = _ctrlPersistencia.carregarEstat();

        if (estat == null)
            return -50;

        _ctrlDirectori.crearDirectori(estat.idDir);
        _ctrlDirectori.getDirectoriObert().setDeletedIds(estat.deletedIds);
        _ctrlDirectori.getDirectoriObert().setIdNouDoc(estat.idNouDoc);
        HashMap<Integer, String> continguts = new HashMap<>();
        for (int i = 0; i < estat.idNouDoc; ++i) {
            if (estat.docs.containsKey(i)) {
                String contingut = _ctrlPersistencia.carregarContingutDocument(i);
                if (contingut == null)
                    return -50;
                continguts.put(i, contingut);
            }
        }
        _ctrlDirectori.carregarDocs(estat.pesosDocs, estat.docs, continguts);

        return -10;
    }

    public ArrayList<String> llistarDocuments() {
        return _ctrlDirectori.llistarDocuments();
    }
    public ArrayList<String> llistarExpressions() {
        return _ctrlExpressio.llistarExpressions();
    }

    public ArrayList<String> toStringDocActiu() {
        return _ctrlDirectori.toStringDocActiu();
    }

    public ArrayList<String> toStringExpActiva() {
        return _ctrlExpressio.toStringExpActiva();
    }
    /**
     * Funció per carregar les expressions un cop es torna a executar el codi
     * @return consultar els codis de return en el document word entregat
     */
    public int carregarExpressions() {
        ArrayList<Pair<Integer, String>> expressionsArray = _ctrlPersistencia.carregarExpressions();

        if (expressionsArray == null)
            return -50;

        int idMax = Integer.MIN_VALUE;
        HashMap<Integer, Expressio> expressionsHashMap = new HashMap<>();
        for (Pair<Integer, String> expPair : expressionsArray) {
            int id = expPair.first();

            Expressio exp = new Expressio(id, expPair.second());

            if (id > idMax)
                idMax = id;

            expressionsHashMap.put(expPair.first(), exp);
        }

        _ctrlExpressio.setIdNovaExp(idMax + 1);
        _ctrlExpressio.setExpressions(expressionsHashMap);

        return -10;
    }

    /**
     * Funció per exportar un document determinat a l'ordinador
     * @param f format esperat d'exportació
     * @param path path on volem guardar el document
     * @return consultar els codis de return en el document word entregat, la id del document exportat en cas de funcionament correcte
     */
    public int exportarDocument(String f, String path) {
        GestorDocument.FILETYPE format = GestorDocument.FILETYPE.valueOf(f);
        Document d = _ctrlDirectori.getDocumentActiu();
        Boolean b = _ctrlPersistencia.exportarDocument(d.getAutor(),d.getTitol(),d.getContingut(),format,path);
        if (!b) return -50;
        return d.getIdDoc();
    }

    /**
     * Funció per importar documents de l'ordinador al sistema
     * @param paths array de paths on hi han els documents
     * @return  consultar els codis de return en el document word entregat
     */
    public int importarDocument(ArrayList<String> paths) {
        for (int i = 0; i < paths.size(); ++i) {
            ArrayList<String> values = _ctrlPersistencia.importarDocument(paths.get(i));
            if (values == null)
                return -50;

            int j = afegirDocument(values.get(0), values.get(1), values.get(2));
            if (j == -20 | j == -30) return i;
        }
        return -10;
    }

    /**
     * Funció per afegir una expressió dins el nostre sistema
     * @param expressio expressió que volem guardar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió creada en cas de funcionament correcte
     */
    public int afegirExpressio (String expressio) {
        int i = _ctrlExpressio.afegirExpressio(expressio);
        if (i > -1) {
            Boolean b = _ctrlPersistencia.guardarExpressio(i,expressio);
            if (!b) return -50;
        }
        return i;
    }

    /**
     * Funció per eliminar una expressió del sistema
     * @param idExp id de l'expressió a eliminar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió eliminada en cas de funcionament correcte
     */
    public int eliminarExpressio (int idExp) {
        int i = _ctrlExpressio.eliminarExpressio(idExp);
        if (i > -1 || i == -10 || i == -11) {
            Boolean b = _ctrlPersistencia.eliminarExpressio(idExp);
            if (!b) return -50;
        }
        return i;
    }

}
