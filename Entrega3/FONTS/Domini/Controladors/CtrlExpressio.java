package Domini.Controladors;

import Domini.Classes.BinaryTree;
import Domini.Classes.Document;
import Domini.Classes.Expressio;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa un Controlador d'expressions
 * @author pol.camprubi.prats
 * @author isaac.roma.granado
 */
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
     * Constructora del Controlador d'Expressió
     */
    public CtrlExpressio() {
        this.expressioSeleccionada = null;
        IdNovaExp = 0;
        expressions = new HashMap<>();
    }

    /**
     * Setter d'identificador per a una nova expressio
     */
    public void setIdNovaExp(Integer id) {IdNovaExp = id;}

    /**
     * Getter d'expressió seleccionada
     * @return retorna l'expressió seleccionada
     */
    public Expressio getExpressioSeleccionada() {
        return expressioSeleccionada;
    }

    /**
     * Getter de les expressions
     * @return retorna el hashmap d'expressions del sistema
     */
    public HashMap<Integer, Expressio> getExpressions() {
        return expressions;
    }

    /**
     * Setter de les expressions
     * @param expressions nou valor de la variable expressions
     */
    public void setExpressions(HashMap<Integer, Expressio> expressions) {
        this.expressions = expressions;
    }

    /**
     * Funció per seleccionar l'expressió seleccionada
     * @param idExp id del document a seleccionar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió creada en cas de funcionament correcte
     */
    public int seleccionarExpressio (Integer idExp) {
        if (expressions.containsKey(idExp)) {
            expressioSeleccionada = expressions.get(idExp);
            return -10;
        }
        return -20;
    }


    /**
     * Funció per afegir una expressió dins el nostre sistema
     * @param expressio expressió que volem guardar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió creada en cas de funcionament correcte
     */
    public int afegirExpressio(String expressio){
        if (expressio == null || expressio.isBlank()) return -30;
        for (Expressio e : expressions.values()) {
            if (e.getExpressio().equals(expressio)) {
                return -20;
            }
        }
        expressioSeleccionada = new Expressio(IdNovaExp,expressio);
        ++IdNovaExp;
        expressions.put(expressioSeleccionada.getIdExp(),expressioSeleccionada);
        return expressioSeleccionada.getIdExp();
    }

    /**
     * Funció per modificar l'expressió seleccionada
     * @param exp nou valor de la variable expressió
     * @return consultar els codis de return en el document word entregat, la id de l'expressió modificada en cas de funcionament correcte
     */
    public int modificarExpressio(String exp){
        if (expressioSeleccionada == null) return -31;
        if (exp == null || exp.isEmpty()) {
            return -30;
        }
        for (Expressio e : expressions.values()) {
            if (e.getExpressio().equals(exp)) {
                return -20;
            }
        }
        expressioSeleccionada.setExpressio(exp);
        expressioSeleccionada.setExpressionTree(new BinaryTree(exp));
        return expressioSeleccionada.getIdExp();
    }

    /**
     * Funció per eliminar una expressió del sistema
     * @param idExp id de l'expressió a eliminar
     * @return consultar els codis de return en el document word entregat, la id de l'expressió eliminada en cas de funcionament correcte
     */
    public int eliminarExpressio(int idExp){
        if (expressions.containsKey(idExp)) {
            expressions.remove(idExp);
            if (expressioSeleccionada != null && idExp == expressioSeleccionada.getIdExp()) {
                expressioSeleccionada = null;
                return -11;
            }
            return -10;
        } else {
            return -20;
        }
    }

    /**
     * Funció per seleccionar els document que compleixen una expressió
     * @param idExp id de l'expressió a comparar
     * @param document document per avaluar si compleix l'expressió
     * @return retorna un vector dels documents que compleixen l'expressió
     */
    public boolean selectPerExpressio(Integer idExp, Document document) {
        BinaryTree bt = expressions.get(idExp).getExpressionTree();
        int result = BinaryTree.evalTree(bt.root, document);
        return (result != 0);
    }

    /**
     * Funció per carregar les expressions actives en el directori
     * @return retorna un vector amb totes les expressions actives
     */
    public ArrayList<String> llistarExpressions() {
        ArrayList<String> resultat = new ArrayList<>();
        if (expressions.size() == 0) return null;
        for (HashMap.Entry<Integer, Expressio> exp : expressions.entrySet()) {
            resultat.add(String.valueOf(exp.getKey()));
            resultat.add(exp.getValue().getExpressio());
        }
        return resultat;
    }

    /**
     * Funció per obtenir la informació de l'expressió seleccionada
     * @return retorna un vector amb l'identificador i l'expressió seleccionada
     */
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

