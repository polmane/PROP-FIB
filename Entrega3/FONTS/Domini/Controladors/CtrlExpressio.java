package Domini.Controladors;

import Domini.Classes.BinaryTree;
import Domini.Classes.Document;
import Domini.Classes.Expressio;

import java.util.ArrayList;
import java.util.HashMap;

public class CtrlExpressio {
    /**
     * Representa l'expressio sobre la que es treballa
     */
    private Expressio expressioSeleccionada;

    /**
     * Representa la Id de la nova expressió
     */
    private Integer IdNovaExp;


    /**
     * Representa el directori on esta l'expressio seleccionada
     */
    public HashMap<Integer,Expressio> expressions;

    /**
     * Constructora
     */
    public CtrlExpressio() {
        this.expressioSeleccionada = null;
        IdNovaExp = 0;
        expressions = new HashMap<>();
    }

    /**
     * Getter d'expressió seleccionada
     * @return retorna l'expressió seleccionada
     */
    public Expressio getExpressioSeleccionada() {
        return expressioSeleccionada;
    }

    public HashMap<Integer, Expressio> getExpressions() {
        return expressions;
    }

    public void setExpressions(HashMap<Integer, Expressio> expressions) {
        this.expressions = expressions;
    }

    /**
     * seleccionarExpressió permet setejar l'expressió seleccionada dins el sistema
     * @param idExp respresenta la id de l'expressió que volem seleccionar
     * @return si retorna 10 vol dir que s'ha realitzat l'operació correctament, si retorna 20 significa que hi ha hagut un error
     */
    public int seleccionarExpressio (Integer idExp) {
        if (expressions.containsKey(idExp)) {
            expressioSeleccionada = expressions.get(idExp);
            return 10;
        }
        return 20;
    }


    /**
     * AfegirExpressió permet afegir una nova expressió dins el sistema
     * @param expressio representa l'string que l'usuari reconeix com l'expressió per fer la cerca
     * @return si retorna 10 vol dir que s'ha realitzat l'operació correctament, si retorna 20 significa que hi ha hagut un error
     */
    public int afegirExpressio(String expressio){
        if (expressio == null || expressio.isBlank()) return 30;

        for (Expressio e : expressions.values()) {
            if (e.getExpressio().equals(expressio)) {
                return 20;
            }
        }

        expressioSeleccionada = new Expressio(IdNovaExp,expressio);
        ++IdNovaExp;

        expressions.put(expressioSeleccionada.getIdExp(),expressioSeleccionada);
        return 10;
    }

    /**
     * modificarExpressió modifica l'expressió seleccionada amb un nou text corresponent
     * @param exp representa la nova expressió
     * @return si retorna 10 vol dir que s'ha realitzat l'operació correctament, si retorna 20 significa que hi ha hagut un error
     */
    public int modificarExpressio(String exp){
        if (expressioSeleccionada == null) return 31;
        if (exp == null || exp.isEmpty()) {
            return 30;
        }
        for (Expressio e : expressions.values()) {
            if (e.getExpressio().equals(exp)) {
                return 20;
            }
        }
        expressioSeleccionada.setExpressio(exp);
        expressioSeleccionada.setExpressionTree(new BinaryTree(exp));
        return 10;
    }

    /**
     * eliminarExpressió elimina l'expressió amb idExp del sistema
     * @param idExp representa la id de l'expressió que volem eliminar
     * @return si retorna 10 vol dir que s'ha realitzat l'operació correctament, si retorna 20 significa que hi ha hagut un error
     */
    public int eliminarExpressio(int idExp){
        if (expressions.containsKey(idExp)) {
            expressions.remove(idExp);
            if (expressioSeleccionada != null && idExp == expressioSeleccionada.getIdExp()) {
                expressioSeleccionada = null;
                return 11;
            }
            return 10;
        } else {
            return 20;
        }
    }

    /**
     * selectPerExpressió busca, de tots els documents que nosaltres tenim en el sistema, aquells documents que compleixen l'expressió
     *
     * @param idExp    representa la id de l'expressió que volem evaluar
     * @param document representa el document possible a evaluar
     * @return retorna els documents que compleixen l'expressió
     */
    public boolean selectPerExpressio(Integer idExp, Document document) {
        String exp = expressions.get(idExp).getExpressio();
        BinaryTree bt = expressions.get(idExp).getExpressionTree();
        int result = BinaryTree.evalTree(bt.root, document);
        return (result != 0);
    }

    public ArrayList<String> llistarExpressions() {
        ArrayList<String> resultat = new ArrayList<>();
        for (HashMap.Entry<Integer, Expressio> exp : expressions.entrySet()) {
            resultat.add(String.valueOf(exp.getKey()));
            resultat.add(exp.getValue().getExpressio());
        }
        return resultat;
    }

    public ArrayList<String>  toStringExpActiva() {
        ArrayList<String> resultat = new ArrayList<>();
        if (expressioSeleccionada != null) {
            resultat.add(String.valueOf(expressioSeleccionada.getIdExp()));
            resultat.add(expressioSeleccionada.getExpressio());
        }
        else {
            resultat.add("-31");
        }
        return resultat;
    }
}

