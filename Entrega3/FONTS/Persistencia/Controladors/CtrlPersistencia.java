package Persistencia.Controladors;

import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Representa un Controlador de Persistència
 * @author pol.camprubi.prats
 * @author juli.serra.balaguer
 */
public class CtrlPersistencia {
    /**
     * Representa un gestor de Base de Dades
     */
    private final GestorBD _gBD;
    /**
     * Representa un Gestor de Document
     */
    private final GestorDocument _gDoc;
    /**
     * Representa un Gestor d'Expressions
     */
    private final GestorExpressions _gExp;

    /**
     * Constructora del Controlador de Persistència
     */
    public CtrlPersistencia() {
        this._gBD = new GestorBD();
        this._gDoc = new GestorDocument();
        this._gExp = new GestorExpressions();
    }
    /**
     * Funció que permet guardar el contingut d'un document a la base de dades
     * @param idDoc id del document a guardar la informació
     * @param contingut contingut a guardar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarContingutDocument(int idDoc, String contingut) {
        return _gBD.guardarContingutDocument(idDoc, contingut);
    }
    /**
     * Funció que permet carregar el contingut d'un document
     * @param idDoc id del document a carregar el contingut
     * @return retorna el contingut del docuement en cas de funcionament correcte, null en cas contrari
     */
    public String carregarContingutDocument(int idDoc) {
        return _gBD.carregarContingutDocument(idDoc);
    }
    /**
     * Funció que permet guardar l'estat del programa a la base de dades
     * @param idDir id del directori
     * @param pesosDocs matriu de pesos
     * @param deletedIds cua de ids
     * @param idNouDoc id del nou docuent
     * @param docs documents del nostre sistema
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarEstat(int idDir, HashMap<Integer, HashMap<String, Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String, String>> docs) {
        return _gBD.guardarEstat(idDir, pesosDocs, deletedIds, idNouDoc, docs);
    }
    /**
     * Funció que permet carregar l'estat del programa a MP
     * @return retorna un object Estat en cas de funcionament correcte, null en cas contrari
     */
    public GestorBD.Estat carregarEstat() {
        return _gBD.carregarEstat();
    }
    /**
     * Funció per carregar les expressions a MP
     * @return retorna un arrayList de pair on la primera variable és la id de l'expressió i la segona és l'expressió
     */
    public ArrayList<Pair<Integer, String>> carregarExpressions() {
        return _gExp.carregarExpressions();
    }
    /**
     * Funció per eliminar el document de la base de dades
     * @param idDoc identificador del document a eliminar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean eliminarDocument(int idDoc) {
        return _gBD.eliminarDocument(idDoc);
    }
    /**
     * Funció per exportar un document determinat a l'ordinador
     * @param autor autor del document a exportar
     * @param titol titol del document a exportar
     * @param contingut contingut del document a exportar
     * @param format format del documet a exportar (enum FILETYPE)
     * @param path path on situar el document a exportar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean exportarDocument(String autor, String titol, String contingut, GestorDocument.FILETYPE format, String path) {
        return _gDoc.exportarDocument(autor, titol, contingut, format, path);
    }
    /**
     * Funció per importar un document donat un path
     * @param path path on es troba el document a importar
     * @return retorna null en cas d'error, altrament retorna un array d'string on la primera posició és l'autor del document, la segona és el títol i la tercera el contingut
     */
    public ArrayList<String> importarDocument(String path) {
        return _gDoc.importarDocument(path);
    }
    /**
     * Funció per guardar una expressió
     * @param idExp id de l'expressió a guardar
     * @param expressio expressió a guardar
     * @return true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarExpressio(int idExp, String expressio) {
        return _gExp.guardarExpressio(idExp, expressio);
    }
    /**
     * Funció per eliminar una expressió del sistema
     * @param idExp id de l'experssió a eliminar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean eliminarExpressio(int idExp) {
        return _gExp.eliminarExpressio(idExp);
    }

}
