package FONTS.Test;

import FONTS.Classes.Expressio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestExpressio {
    /**
     * Objecte de la prova: Test dels mètodes creadors de la classe Expressio
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es crea un objecte Expressio
     * Operativa: es comprova que les constructores de expressio funcionen correctament
     */
    @Test
    public void TestConstructores() {

        Expressio exp = new Expressio();

        assertNull(exp.getIdExp());
        assertNull(exp.getExpressio());

        int idExp = 0;
        Expressio exp1 = new Expressio(idExp);

        assertEquals(0, exp1.getIdExp());
        assertNull(exp1.getExpressio());

        idExp = 0;
        string expressio = "!";
        Expressio exp2 = new Expressio(idExp, expressio);

        assertEquals(0, exp2.getIdExp());
        assertEquals("!", exp2.getExpressio());
    }
}
