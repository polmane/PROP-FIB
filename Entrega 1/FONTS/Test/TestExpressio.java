package FONTS.Test;

import FONTS.Classes.Expressio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
