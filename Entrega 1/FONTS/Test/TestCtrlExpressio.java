package FONTS.Test;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;
import FONTS.Classes.Expressio;
import FONTS.Controladors.CtrlDirectori;
import FONTS.Controladors.CtrlExpressio;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCtrlExpressio {

    /**
     * Objecte de la prova: Test del mètode afegirExpressio de la classe CtrlExpressió
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
        assertEquals(1, CtrlExp.expressions.size());

        //ERROR: ja existeix una expressió amb el mateix format
        String exp1 = "pau ! pol";
        CtrlExp.afegirExpressio(exp1);
        assertEquals(1, CtrlExp.expressions.size());


    }


    /**
     * Objecte de la prova: Test del mètode eliminarExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina una expressio del conjunt d'expressions
     * Operativa: es comprova que l'operacio eliminarExpressio funciona correctament
     */
    @Test
    public void TestEliminarExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        CtrlExp.afegirExpressio("pol ! pau");

        //ERROR: No existeix l'expressio amb el identificador idExp
        CtrlExp.eliminarexpressio(1);
        assertEquals(1, CtrlExp.expressions.size());

        //Funcionament correcte
        CtrlExp.eliminarexpressio(CtrlExp.getExpressioSeleccionada().getIdEXp());
        assertEquals(0, CtrlExp.expressions.size());
    }

    /**
     * Objecte de la prova: Test del mètode modificarExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica una expressio del conjunt d'expressions
     * Operativa: es comprova que l'operacio modificarExpressio funciona correctament
     */
    @Test
    public void TestModificarExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();

        CtrlExp.afegirExpressio("pol ! pau");
        String exp = "pol ! pau";

        //Funcionament correcte
        String exp1 = "p1 or p2";
        CtrlExp.modificarExpressio(exp1);
        assertEquals(exp1, CtrlExp.getExpressioSeleccionada().getExpressio());

        //ERROR: l'expressió modificada ja existeix al directori
        String exp2 = "p1 or p2";
        CtrlExp.modificarExpressio(exp2);
        assertNotEquals(exp2, exp);
    }

    /**
     * Objecte de la prova: Test del mètode selectPerExpressio de la classe CtrlExpressió
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'obtenen els documents que compleixen el contingut de l'expressió seleccionada
     * Operativa: es comprova que l'operacio selectPerExpressio funciona correctament
     */
    @Test
    public void TestSelectPerExpressio() throws Exception {
        CtrlExpressio CtrlExp = new CtrlExpressio();
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);
        Directori dir = CtrlDir.getDirectoriObert();
        CtrlDir.afegirDocument("autor1", "doc1", "hola p1 p2 p3");

       //Expressio = una paraula
        CtrlExp.afegirExpressio("hola");
        ArrayList<Document> d = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d.size());

        //Expressio = negació d'una paraula
        CtrlExp.afegirExpressio("!hola");
        ArrayList<Document> d1 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d1.size());

        CtrlExp.afegirExpressio("{p1 p2 p3}");
        ArrayList<Document> d2 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d2.size());

        CtrlExp.afegirExpressio("!{p1 p2 p3}");
        ArrayList<Document> d3 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d3.size());

        CtrlExp.afegirExpressio("\"p1 p2 p3\"");
        ArrayList<Document> d4 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d4.size());

        CtrlExp.afegirExpressio("!\"p1 p2 p3\"");
        ArrayList<Document> d5 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d5.size());

        CtrlExp.afegirExpressio("p1 & p2");
        ArrayList<Document> d6 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d6.size());

        CtrlExp.afegirExpressio("p1 & !p2");
        ArrayList<Document> d7 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d7.size());

        CtrlExp.afegirExpressio("!p1 & p2");
        ArrayList<Document> d8 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d8.size());

        CtrlExp.afegirExpressio("!p1 & !p2");
        ArrayList<Document> d9 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d9.size());

        CtrlExp.afegirExpressio("p1 | p2");
        ArrayList<Document> d10 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d10.size());

        CtrlExp.afegirExpressio("p1 | !p2");
        ArrayList<Document> d11 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d11.size());

        CtrlExp.afegirExpressio("!p1 | p2");
        ArrayList<Document> d12 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d12.size());

        CtrlExp.afegirExpressio("!p1 | !p2");
        ArrayList<Document> d13 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d13.size());

        CtrlExp.afegirExpressio("{hola p1} | p2");
        ArrayList<Document> d14 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d14.size());

        CtrlExp.afegirExpressio("{hola p1} | !p2");
        ArrayList<Document> d15 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d15.size());

        CtrlExp.afegirExpressio("!{hola p1} | p2");
        ArrayList<Document> d16 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d16.size());

        CtrlExp.afegirExpressio("!{hola p1} | !p2");
        ArrayList<Document> d17 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d17.size());

        CtrlExp.afegirExpressio("{hola p1} & p2");
        ArrayList<Document> d18 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d18.size());

        CtrlExp.afegirExpressio("{hola p1} & !p2");
        ArrayList<Document> d19 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d19.size());

        CtrlExp.afegirExpressio("!{hola p1} & p2");
        ArrayList<Document> d20 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d20.size());

        CtrlExp.afegirExpressio("!{hola p1} & !p2");
        ArrayList<Document> d21 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d21.size());

        CtrlExp.afegirExpressio("{hola p1} | {p2 p3}");
        ArrayList<Document> d22 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d22.size());

        CtrlExp.afegirExpressio("{hola p1} | !{p2 p3}");
        ArrayList<Document> d23 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d23.size());

        CtrlExp.afegirExpressio("!{hola p1} | {p2 p3}");
        ArrayList<Document> d24 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d24.size());

        CtrlExp.afegirExpressio("!{hola p1} | !{p2 p3}");
        ArrayList<Document> d25 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d25.size());

        CtrlExp.afegirExpressio("{hola p1} & {p2 p3}");
         ArrayList<Document> d26 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
         assertEquals(1, d26.size());

        CtrlExp.afegirExpressio("{hola p1} & !{p2 p3}");
        ArrayList<Document> d27 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d27.size());

        CtrlExp.afegirExpressio("!{hola p1} & {p2 p3}");
        ArrayList<Document> d28 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d28.size());

        CtrlExp.afegirExpressio("!{hola p1} & !{p2 p3}");
        ArrayList<Document> d29 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d29.size());

        CtrlExp.afegirExpressio("(hola)");
        ArrayList<Document> d30 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d30.size());

        CtrlExp.afegirExpressio("!(hola)");
        ArrayList<Document> d31 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d31.size());

        CtrlExp.afegirExpressio("(p1) | (p2)");
        ArrayList<Document> d32 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d32.size());

        CtrlExp.afegirExpressio("(p1) | !(p2)");
        ArrayList<Document> d33 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d33.size());

        CtrlExp.afegirExpressio("!(p1) | (p2)");
        ArrayList<Document> d34 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d34.size());

        CtrlExp.afegirExpressio("!(p1) | !(p2)");
        ArrayList<Document> d35 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d35.size());

        CtrlExp.afegirExpressio("(p1) & (p2)");
        ArrayList<Document> d36 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d36.size());

        CtrlExp.afegirExpressio("(p1) & !(p2)");
        ArrayList<Document> d37 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d37.size());

        CtrlExp.afegirExpressio("!(p1) & (p2)");
        ArrayList<Document> d38 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d38.size());

        CtrlExp.afegirExpressio("!(p1) & !(p2)");
        ArrayList<Document> d39 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d39.size());

        CtrlExp.afegirExpressio("(p1 | p3) | (p2)");
        ArrayList<Document> d40 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d40.size());

        CtrlExp.afegirExpressio("(p1 | !p3) | (p2)");
        ArrayList<Document> d41 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d41.size());

        CtrlExp.afegirExpressio("(!p1 | p3) | (p2)");
        ArrayList<Document> d42 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d42.size());

        CtrlExp.afegirExpressio("(!p1 | !p3) | (p2)");
        ArrayList<Document> d43 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d43.size());

        CtrlExp.afegirExpressio("(p1 & p3) | (p2)");
        ArrayList<Document> d44 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d44.size());

        CtrlExp.afegirExpressio("(p1 & !p3) | (p2)");
        ArrayList<Document> d45 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d45.size());

        CtrlExp.afegirExpressio("(!p1 & p3) | (p2)");
        ArrayList<Document> d46 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d46.size());

        CtrlExp.afegirExpressio("(!p1 & !p3) | (p2)");
        ArrayList<Document> d47 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d47.size());

        CtrlExp.afegirExpressio("({p1 p2} | p3)");
        ArrayList<Document> d48 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d48.size());

        CtrlExp.afegirExpressio("({p1 p2} | !p3)");
        ArrayList<Document> d49 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d49.size());

        CtrlExp.afegirExpressio("(!{p1 p2} | p3)");
        ArrayList<Document> d50 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d50.size());

        CtrlExp.afegirExpressio("(!{p1 p2} | !p3)");
        ArrayList<Document> d51 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d51.size());

        CtrlExp.afegirExpressio("({p1 p2} & p3)");
        ArrayList<Document> d52 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(1, d52.size());

        CtrlExp.afegirExpressio("({p1 p2} & !p3)");
        ArrayList<Document> d53 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d53.size());

        CtrlExp.afegirExpressio("(!{p1 p2} & p3)");
        ArrayList<Document> d54 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d54.size());

        CtrlExp.afegirExpressio("(!{p1 p2} & !p3)");
        ArrayList<Document> d55 = CtrlExp.selectPerExpressio(CtrlExp.getExpressioSeleccionada().getIdEXp(), dir.getDocs());
        assertEquals(0, d55.size());
    }
}
