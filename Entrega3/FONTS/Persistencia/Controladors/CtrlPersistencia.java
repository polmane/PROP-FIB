package Persistencia.Controladors;

import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressions;

public class CtrlPersistencia {

    private final GestorBD _gBD;
    private final GestorDocument _gDoc;
    private final GestorExpressions _gExp;

<<<<<<< HEAD
    public CtrlPersistencia(GestorDirectori _gDir, GestorDocument _gDoc, GestorExpressions _gExp) {
        this._gDir = _gDir;
        this._gDoc = _gDoc;
        this._gExp = _gExp;
=======
    public CtrlPersistencia() {
        this._gBD = new GestorBD();
        this._gDoc = new GestorDocument();
        this._gExp = new GestorExpressio();
>>>>>>> exportacions
    }
}
