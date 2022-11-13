package FONTS.Controladors;

import FONTS.Classes.BinaryTree;
import FONTS.Classes.Document;
import FONTS.Classes.Expressio;


import java.util.ArrayList;
import java.util.HashMap;

public class CtrlExpressio {
    /**
     * Representa l'expressio sobre la que es treballa
     */
    private Expressio expressioSeleccionada;

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
     * Getter d'expressioSeleccionada
     */
    public Expressio getExpressioSeleccionada() {
        return expressioSeleccionada;
    }

    /**
     * AfegirExpressió permet afegir una nova expressió dins el sistema
     * @param expressio representa l'string que l'usuari reconeix com l'expressió per fer la cerca
     */
    public void afegirExpressio(String expressio) throws Exception {
        expressioSeleccionada = new Expressio(IdNovaExp,expressio);
        ++IdNovaExp;
        for (Expressio e : expressions.values()) {
            if (e.getExpressio().equals(expressio)) {
                //throw new Exception("Ja existeix una expresió igual en el directori");
                return;
            }
        }
        expressions.put(expressioSeleccionada.getIdEXp(),expressioSeleccionada);
    }
    /**
     * Operacio per modificar l'expressio seleccionada
     */
    public void modificarExpressio(String exp) {
        expressioSeleccionada.setExpressio(exp);
        expressioSeleccionada.ExpressionTree = new BinaryTree(exp);
    }

    public void eliminarexpressio(int idExp) throws Exception {
        if (expressions.containsKey(idExp)) {
            expressions.remove(idExp);
        }
        /**} else {
            throw new Exception("La expressió no esta en el directori");
        }*/
    }

    /**
     * Fa una cerca dels documents que contenen les paraules de la expressió
     * que es passa com a parametre
     * @return ArrayList<Document> retorna els documents que compleixen els criteris
     * de cerca de la expressió passada com a paràmetre
     */
    public ArrayList<Document> selectPerExpressio(Integer idExp, HashMap<Integer, Document> docs) {
        String exp = expressions.get(idExp).getExpressio();
        BinaryTree bt = expressions.get(idExp).ExpressionTree;
        ArrayList<Document> greatDocs = new ArrayList<>();
        for (Document d : docs.values()) {
            int result = BinaryTree.evalTree(bt.root, d);
            if (result != 0) greatDocs.add(d);
        }
        return greatDocs;

    }
}

