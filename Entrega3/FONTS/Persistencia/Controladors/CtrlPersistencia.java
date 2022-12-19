package Persistencia.Controladors;

import Domini.Classes.Pair;
import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressions;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CtrlPersistencia {

    private final GestorBD _gBD;
    private final GestorDocument _gDoc;
    private final GestorExpressions _gExp;


    public CtrlPersistencia() {
        this._gBD = new GestorBD();
        this._gDoc = new GestorDocument();
        this._gExp = new GestorExpressions();
    }

    public Boolean guardarContingutDocument(int idDoc, String contingut) {
        return _gBD.guardarContingutDocument(idDoc, contingut);
    }

    public Boolean guardarEstat(int idDir, HashMap<Integer, HashMap<String, Double>> pesosDocs, PriorityQueue<Integer> deletedIds, int idNouDoc, HashMap<Integer, Pair<String, String>> docs) {
        return _gBD.guardarEstat(idDir, pesosDocs, deletedIds, idNouDoc, docs);
    }

    public Boolean eliminarDocument(int idDoc) {
        return _gBD.eliminarDocument(idDoc);
    }

    public Boolean exportarDocument(String autor, String titol, String contingut, GestorDocument.FILETYPE format, String path) {
        return _gDoc.exportarDocument(autor, titol, contingut, format, path);
    }

    public Boolean guardarExpressio(int idExp, String expressio) {
        return _gExp.guardarExpressio(idExp, expressio);
    }

    public Boolean eliminarExpressio(int idExp) {
        return _gExp.eliminarExpressio(idExp);
    }

    public String carregarContingutDocument(int idDoc) {
        return _gBD.carregarContingutDocument(idDoc);
    }
}
