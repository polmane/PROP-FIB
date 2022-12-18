package Persistencia.Controladors;

import Persistencia.Classes.GestorDirectori;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressions;

public class CtrlPersistencia {

    private final GestorDirectori _gDir;
    private final GestorDocument _gDoc;
    private final GestorExpressions _gExp;

    public CtrlPersistencia(GestorDirectori _gDir, GestorDocument _gDoc, GestorExpressions _gExp) {
        this._gDir = _gDir;
        this._gDoc = _gDoc;
        this._gExp = _gExp;
    }
}
