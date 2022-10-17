package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Expressio;

import java.util.HashMap;

public class CtrlExpressio {
    /**
     * Representa l'expressio sobre la que es treballa
     */
    private Expressio expressioSeleccionada;


    /**
     * Representa el directori on esta l'expressio seleccionada
     */
    private Directori dir;

    /**
     * Constructora
     */
    public CtrlExpressio() {
        this.expressioSeleccionada = null;
    }

    /**
     * Getter d'expressioSeleccionada
     */
    public Expressio getExpressioSeleccionada() {
        return expressioSeleccionada;
    }

    /**
     * Operacio per modificar l'expressio seleccionada
     */
    public void modificarExpressio(String exp) {
        expressioSeleccionada.setExpressio(exp);
    }

    public void eliminarexpressio(int idExp) throws Exception {
        HashMap<Integer, String> m = dir.expressions;
        if (m.containsKey(idExp)) {
            m.remove(idExp);
        } else {
            throw new Exception("La expressió no esta en el directori");
        }
    }

}
