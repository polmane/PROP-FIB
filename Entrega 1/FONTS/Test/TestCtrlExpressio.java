package FONTS.Test;

import FONTS.Classes.Document;
import FONTS.Controladors.CtrlExpressio;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCtrlExpressio {

    /**
     * Objecte de la prova: Test del mètode afegirExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'afegeix una expressio al conjunt d'expressions
     * Operativa: Creem una nova expressió amb una crida al mètode afegirExpressio i passant com a paràmetre un String.
     *              Seguidament, comprovem que s'ha creat dins el nostre sistema (//Funcionament correcte)
     *              Finalment, comprovem que dona un error quan intentem crear una expressió ja existent (//ERROR:...)
     */

    @Test
    public void TestAfegirExpressio(){
        CtrlExpressio CtrlExp = new CtrlExpressio();

        //Funcionament correcte
        String exp = "pau ! pol";
        CtrlExp.afegirExpressio(exp);
        assertEquals(1, CtrlExp.getExpressions().size());

        //ERROR: ja existeix una expressió amb el mateix format
        String exp1 = "pau ! pol";
        int resultat = CtrlExp.afegirExpressio(exp1);
        assertEquals(20, resultat);
    }

    /**
     * Objecte de la prova: Test del mètode eliminarExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina una expressio del conjunt d'expressions
     * Operativa: Creem una nova expressió utilitzant la crida a afegirExpressio i passant com a paràmetre un String.
     *              A continuació, quan intentem eliminar l'expressió amb el mètode eliminarExpressio i passant la id corresponent. Poden passar dues coses:
     *              l'expressió s'elimina correctament (//Funcionament correcte)
     *              la id no existeix i dóna un codi d'error, l'integer 20 (//ERROR:...)
     */
    @Test
    public void TestEliminarExpressio(){
        CtrlExpressio CtrlExp = new CtrlExpressio();

        CtrlExp.afegirExpressio("pol & pau");

        //Funcionament correcte
        CtrlExp.eliminarExpressio(CtrlExp.getExpressioSeleccionada().getIdExp());
        assertEquals(0, CtrlExp.getExpressions().size());

        //ERROR: No existeix l'expressio amb el identificador idExp
        int resultat = CtrlExp.eliminarExpressio(1);
        assertEquals(20, resultat);

    }

    /**
     * Objecte de la prova: Test del mètode modificarExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica una expressio del conjunt d'expressions
     * Operativa: Per començar, creem una nova expressió amb l'operació afegirExpressio.
     *              A continuació, cridem al mètode modificarExpressio amb l'expressió corresponent a modificar (//Funcionament correcte)
     *              Finalment, testejem que no podem tenir dues expressions repetides (//ERROR:...)
     */

    @Test
    public void TestModificarExpressio() {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        String exp = "pol & pau";
        CtrlExp.afegirExpressio(exp);

        //Funcionament correcte
        String exp1 = "p1 & p2";
        CtrlExp.modificarExpressio(exp1);
        assertEquals(exp1, CtrlExp.getExpressioSeleccionada().getExpressio());

        //ERROR: l'expressió modificada ja existeix al directori
        String exp2 = "p1 & p2";
        int resultat = CtrlExp.modificarExpressio(exp2);
        assertEquals(20, resultat);
    }

    /**
     * Objecte de la prova: Test del mètode seleccionarExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: L'atribut expressióSeleccionada de CtrlExpressio es seteja amb la expressió corresponent a la id
     * Operativa: Creem una nova expressió amb el mètode afegirExpressió.
     *              A continuació,cridem l'operació seleccionarExpressio passant com a paràmetre la id de l'expressió desitjada, el primer cas és una id correcte (//Funcionament correcte)
     *              Finalment, intentem fer la crida amb una id incorrecte per assegurar-nos que no funciona (ERROR:...)
     */
    @Test
    public void TestSeleccionarExpressio() {
        CtrlExpressio CtrlExp = new CtrlExpressio();
        CtrlExp.afegirExpressio("pol & pau");

        //Funcionament correcte
        CtrlExp.seleccionarExpressio(0);
        assertEquals(0,CtrlExp.getExpressioSeleccionada().getIdExp());

        //ERROR: no existeix la id que volem seleccionar
        int resultat = CtrlExp.seleccionarExpressio(10);
        assertEquals(20, resultat);
    }

    /**
     * Objecte de la prova: Test del mètode selectPerExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'obtenen els documents que compleixen el contingut de l'expressió seleccionada
     * Operativa: Creem un document per tal de testejar sobre ell.
     *              A continuació, creem totes les expressions que ens interessa evaluar per tal de comprovar que el màtode selectPerExpressio funciona correctament.
     *              Els paràmetres de cada expressió son diferents
     */

    @Test
    public void TestSelectPerExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        Document document = new Document(0,"autor1", "doc1", "hola p1 p2 p3");
        document.getOcurrencies().put("hola",1);
        document.getOcurrencies().put("p1",1);
        document.getOcurrencies().put("p2",1);
        document.getOcurrencies().put("p3",1);

       //Expressio = una paraula
        CtrlExp.afegirExpressio("hola & p1");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        //Expressio = negació d'una paraula
        CtrlExp.afegirExpressio("!hola & p1");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{p1 p2 p3}");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{p1 p2 p3}");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("\"p1 p2 p3\"");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!\"p1 p2 p3\"");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("p1 & p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("p1 & !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!p1 & p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!p1 & !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("p1 | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("p1 | !p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!p1 | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!p1 | !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} | !p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} | !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} & p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} & !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} & p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} & !p2");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} | {p2 p3}");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} | !{p2 p3}");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} | {p2 p3}");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} | !{p2 p3}");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} & {p2 p3}");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("{hola p1} & !{p2 p3}");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} & {p2 p3}");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("!{hola p1} & !{p2 p3}");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(p1 | p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(p1 | !p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!p1 | p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!p1 | !p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(p1 & p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(p1 & !p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!p1 & p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!p1 & !p3) | p2");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("({p1 p2} | p3)");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("({p1 p2} | !p3)");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!{p1 p2} | p3)");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!{p1 p2} | !p3)");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("({p1 p2} & p3)");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("({p1 p2} & !p3)");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!{p1 p2} & p3)");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));

        CtrlExp.afegirExpressio("(!{p1 p2} & !p3)");
        assertFalse(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));
        CtrlExp.afegirExpressio("({p1 p2} & p3 & (!p4 | hola))");
        assertTrue(CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), document));
    }
}
