package FONTS.Test;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;
import FONTS.Controladors.CtrlDirectori;
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
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        Document doc = new Document(idDoc, "juli", "prova_guardar_document");
        CtrlDir.guardarDocument(doc);
        assertEquals(doc, CtrlDir.getDirectoriObert().getDocs().get(idDoc));

        //ERROR: identificador document no esperat pel directori
        Document doc2 = new Document(-1, "juli", "prova_error_ids");
        CtrlDir.guardarDocument(doc2);
        assertEquals(doc, CtrlDir.getDirectoriObert().getDocs().get(idDoc));

        //ERROR: ja existeix document amb el mateix autor i titol
        idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        Document doc3 = new Document(idDoc, "juli", "el_mateix_titol");
        CtrlDir.guardarDocument(doc3);
        assertEquals(doc3, CtrlDir.getDirectoriObert().getDocs().get(idDoc));
        Document doc4 = new Document(CtrlDir.getDirectoriObert().getIdNouDoc(), "juli", "el_mateix_titol");
        CtrlDir.guardarDocument(doc4);
        assertEquals(doc3, CtrlDir.getDirectoriObert().getDocs().get(idDoc));
    }
}
