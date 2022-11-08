package FONTS.Test;

import FONTS.Classes.Expressio;
import FONTS.Controladors.CtrlExpressio;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestExpressio {
    /**
     * Objecte de la prova: Test dels mètodes creadors de la classe Expressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es cre un objecte Expressio
     * Operativa: es comprova que lesconstructores de expressio funcionen correctament
     */
    @Test
    public void TestConstructores() {
        int idExp = 0;
        String expressio = "!";
        Expressio exp = new Expressio(idExp, expressio);

        assertEquals(0, exp.getIdEXp());
        assertEquals("!", exp.getExpressio());
    }

    /**
     * Objecte de la prova: Test del mètode afegirExpressio de la classe Expressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'afegeix una expressio al conjunt d'expressions
     * Operativa: es comprova que l'operacio afegirExpressio funciona correctament
     */

    @Test
    public void TestAfegirExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        //Funcionament correcte
        String exp = "pau ! pol";
        CtrlExp.afegirExpressio(exp);

        //ERROR: ja existeix una expressió amb el mateix format
        String exp1 = "pau ! pol";
        CtrlExp.afegirExpressio(exp1);


    }


    /**
     * Objecte de la prova: Test del mètode eliminarExpressio de la classe Expressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina una expressio del conjunt d'expressions
     * Operativa: es comprova que l'operacio eliminarExpressio funciona correctament
     */
    @Test
    public void TestEliminarExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();
        int idExp = 0;

        CtrlExp.afegirExpressio("pol ! pau");

        //ERROR: No existeix l'expressio amb el identificador idExp
        CtrlExp.eliminarexpressio(1);

        //Funcionament correcte
        CtrlExp.eliminarexpressio(idExp);
    }

    /**
     * Objecte de la prova: Test del mètode modificarExpressio de la classe Expressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica una expressio del conjunt d'expressions
     * Operativa: es comprova que l'operacio modificarExpressio funciona correctament
     */

    @Test
    public void TestModificarExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        CtrlExp.afegirExpressio("pol ! pau");

        //Funcionament correcte
        String exp = "p1 or p2";
        CtrlExp.modificarExpressio(exp);

        //ERROR: l'expressió modificada ja existeix al directori
        String exp1 = "p1 or p2";
        CtrlExp.modificarExpressio(exp1);
    }
}
