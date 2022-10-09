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

        Document doc = new Document(idDoc, "juli", "prova_guardar_document");
        dir.guardarDocument(doc);
        assertEquals(doc, dir.getDocs().get(idDoc));

        //ERROR: identificador document no esperat pel directori
        Document doc2 = new Document(-1, "juli", "prova_error_ids");
        dir.guardarDocument(doc2);

        //ERROR: ja existeix document amb el mateix autor i titol
        Document doc3 = new Document(dir.getIdNouDoc(), "juli", "el_mateix_titol");
        dir.guardarDocument(doc3);
        Document doc4 = new Document(dir.getIdNouDoc(), "juli", "el_mateix_titol");
        dir.guardarDocument(doc4);

    }
}
