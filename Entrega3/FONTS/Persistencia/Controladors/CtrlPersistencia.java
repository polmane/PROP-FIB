package Persistencia.Controladors;

import Persistencia.Classes.GestorBD;
import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressio;

public class CtrlPersistencia {

    private final GestorBD _gBD;
    private final GestorDocument _gDoc;
    private final GestorExpressio _gExp;

    public CtrlPersistencia() {
        this._gBD = new GestorBD();
        this._gDoc = new GestorDocument();
        this._gExp = new GestorExpressio();
    }
}
