package Domini.Test;

import Domini.Classes.Directori;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestDirectori {
    /**
     * Objecte de la prova: Test dels mètodes creadors de la classe Directori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es crea un objecte Directori
     * Operativa: es comprova que la constructora de directori funciona correctament
     */
    @Test
    public void TestConstructores() {
        int idDir = 0;
        Directori dir = new Directori(idDir);
        assertEquals(0, dir.getIdDir());
        assertEquals(0, dir.getIdNouDoc());
    }
}
