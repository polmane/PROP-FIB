package FONTS.Test;

import FONTS.Classes.Document;
import FONTS.Controladors.CtrlExpressio;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        CtrlExp.eliminarexpressio(CtrlExp.getExpressioSeleccionada().getIdExp());
        assertEquals(0, CtrlExp.getExpressions().size());

        //ERROR: No existeix l'expressio amb el identificador idExp
        int resultat = CtrlExp.eliminarexpressio(1);
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
        ArrayList<Document> d1 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d1.size());

        CtrlExp.afegirExpressio("{p1 p2 p3}");
        ArrayList<Document> d2 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d2.size());

        CtrlExp.afegirExpressio("!{p1 p2 p3}");
        ArrayList<Document> d3 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d3.size());

        CtrlExp.afegirExpressio("\"p1 p2 p3\"");
        ArrayList<Document> d4 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d4.size());

        CtrlExp.afegirExpressio("!\"p1 p2 p3\"");
        ArrayList<Document> d5 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d5.size());

        CtrlExp.afegirExpressio("p1 & p2");
        ArrayList<Document> d6 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d6.size());

        CtrlExp.afegirExpressio("p1 & !p2");
        ArrayList<Document> d7 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d7.size());

        CtrlExp.afegirExpressio("!p1 & p2");
        ArrayList<Document> d8 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d8.size());

        CtrlExp.afegirExpressio("!p1 & !p2");
        ArrayList<Document> d9 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d9.size());

        CtrlExp.afegirExpressio("p1 | p2");
        ArrayList<Document> d10 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d10.size());

        CtrlExp.afegirExpressio("p1 | !p2");
        ArrayList<Document> d11 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d11.size());

        CtrlExp.afegirExpressio("!p1 | p2");
        ArrayList<Document> d12 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d12.size());

        CtrlExp.afegirExpressio("!p1 | !p2");
        ArrayList<Document> d13 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d13.size());

        CtrlExp.afegirExpressio("{hola p1} | p2");
        ArrayList<Document> d14 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d14.size());

        CtrlExp.afegirExpressio("{hola p1} | !p2");
        ArrayList<Document> d15 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d15.size());

        CtrlExp.afegirExpressio("!{hola p1} | p2");
        ArrayList<Document> d16 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d16.size());

        CtrlExp.afegirExpressio("!{hola p1} | !p2");
        ArrayList<Document> d17 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d17.size());

        CtrlExp.afegirExpressio("{hola p1} & p2");
        ArrayList<Document> d18 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d18.size());

        CtrlExp.afegirExpressio("{hola p1} & !p2");
        ArrayList<Document> d19 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d19.size());

        CtrlExp.afegirExpressio("!{hola p1} & p2");
        ArrayList<Document> d20 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d20.size());

        CtrlExp.afegirExpressio("!{hola p1} & !p2");
        ArrayList<Document> d21 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d21.size());

        CtrlExp.afegirExpressio("{hola p1} | {p2 p3}");
        ArrayList<Document> d22 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d22.size());

        CtrlExp.afegirExpressio("{hola p1} | !{p2 p3}");
        ArrayList<Document> d23 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d23.size());

        CtrlExp.afegirExpressio("!{hola p1} | {p2 p3}");
        ArrayList<Document> d24 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d24.size());

        CtrlExp.afegirExpressio("!{hola p1} | !{p2 p3}");
        ArrayList<Document> d25 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d25.size());

        CtrlExp.afegirExpressio("{hola p1} & {p2 p3}");
         ArrayList<Document> d26 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
         assertEquals(1, d26.size());

        CtrlExp.afegirExpressio("{hola p1} & !{p2 p3}");
        ArrayList<Document> d27 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d27.size());

        CtrlExp.afegirExpressio("!{hola p1} & {p2 p3}");
        ArrayList<Document> d28 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d28.size());

        CtrlExp.afegirExpressio("!{hola p1} & !{p2 p3}");
        ArrayList<Document> d29 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d29.size());

        CtrlExp.afegirExpressio("(p1 | p3) | p2");
        ArrayList<Document> d30 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d30.size());

        CtrlExp.afegirExpressio("(p1 | !p3) | p2");
        ArrayList<Document> d31 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d31.size());

        CtrlExp.afegirExpressio("(!p1 | p3) | p2");
        ArrayList<Document> d32 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d32.size());

        CtrlExp.afegirExpressio("(!p1 | !p3) | p2");
        ArrayList<Document> d33 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d33.size());

        CtrlExp.afegirExpressio("(p1 & p3) | p2");
        ArrayList<Document> d34 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d34.size());

        CtrlExp.afegirExpressio("(p1 & !p3) | p2");
        ArrayList<Document> d35 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d35.size());

        CtrlExp.afegirExpressio("(!p1 & p3) | p2");
        ArrayList<Document> d36 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d36.size());

        CtrlExp.afegirExpressio("(!p1 & !p3) | p2");
        ArrayList<Document> d37 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d37.size());

        CtrlExp.afegirExpressio("({p1 p2} | p3)");
        ArrayList<Document> d38 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d38.size());

        CtrlExp.afegirExpressio("({p1 p2} | !p3)");
        ArrayList<Document> d39 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d39.size());

        CtrlExp.afegirExpressio("(!{p1 p2} | p3)");
        ArrayList<Document> d40 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d40.size());

        CtrlExp.afegirExpressio("(!{p1 p2} | !p3)");
        ArrayList<Document> d41 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d41.size());

        CtrlExp.afegirExpressio("({p1 p2} & p3)");
        ArrayList<Document> d42 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d42.size());

        CtrlExp.afegirExpressio("({p1 p2} & !p3)");
        ArrayList<Document> d43 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d43.size());

        CtrlExp.afegirExpressio("(!{p1 p2} & p3)");
        ArrayList<Document> d44 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d44.size());

        CtrlExp.afegirExpressio("(!{p1 p2} & !p3)");
        ArrayList<Document> d45 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(0, d45.size());

        CtrlExp.afegirExpressio("({p1 p2} & p3 & (!p4 | hola))");
        ArrayList<Document> d46 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdExp(), dir.getDocs());
        assertEquals(1, d46.size());
    }
}
