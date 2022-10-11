package FONTS.Controladors;

import FONTS.Classes.Expressio;
public class CtrlExpressio {
    /**
     * Representa l'expressio sobre la que es treballa
     */
    private Expressio expressioSeleccionada;

    /**
     * Constructora
     */
    public CtrlExpressio() {
        this.expressioSeleccionada = null;
    }

    /**
     * Getter d'expressioseleccionada
     */
    public Expressio getExpressioSeleccionada() {
        return expressioSeleccionada;
    }

    /**
     * Operacio per modificar l'expressio seleccionada
     */
    public void modificarExpressio(string exp) {
        expressioSeleccionada.setExpressio(exp);
    }

    /**
     * Operacio per eliminar l'expressio seleccionada
     */
    public void eliminarExpressio(int idExp) {
        expressioSeleccionada.();
    }
}
