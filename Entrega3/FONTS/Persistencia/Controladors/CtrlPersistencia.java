package Persistencia.Controladors;

import Persistencia.Classes.GestorDirectori;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressio;

public class CtrlPersistencia {

    private final GestorDirectori _gDir;
    private final GestorDocument _gDoc;
    private final GestorExpressio _gExp;

    public CtrlPersistencia(GestorDirectori _gDir, GestorDocument _gDoc, GestorExpressio _gExp) {
        this._gDir = _gDir;
        this._gDoc = _gDoc;
        this._gExp = _gExp;
    }
}
