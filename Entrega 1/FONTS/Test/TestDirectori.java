package FONTS.Test;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    /**
     * Objecte de la prova: Test del mètode afegirDocument de la classe Directori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'afegeix un document al directori
     * Operativa: es comprova que afegir un document al directori funcioni correctament
     */
    @Test
    public void TestGuardarDocument() {
        Directori dir = new Directori(0);
        int idDoc = dir.getIdNouDoc();

        Document doc = new Document(idDoc, "juli", "prova_afegir_document");
        dir.guardarDocument(doc);
        assertEquals(doc, dir.getDocs().get(idDoc));

        //TODO: Comprovació de possibles errors al guardar document
    }
}
