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
     * Objecte de la prova: Test del mètode guardarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es guarda un document al directori
     * Operativa: es comprova que guardar un document al directori funcioni correctament
     */
    @Test
    public void TestGuardarDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Funcionament correcte
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

    @Test
    public void TestAfegirDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Funcionament correcte
        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        Document doc = new Document(idDoc, "juli", "prova_guardar_document");
        CtrlDir.guardarDocument(doc);
        assertEquals(doc, CtrlDir.getDirectoriObert().getDocs().get(idDoc));

        //TODO:Falta veure que crei document amb una id reciclada

        //TODO:Falta veure que no peti quan no tenim un document amb la id (veure que la condició contains dins el for es compleix)

        //ERROR: ja existeix document amb el mateix autor i titol
        idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        Document doc3 = new Document(idDoc, "juli", "el_mateix_titol");
        CtrlDir.guardarDocument(doc3);
        assertEquals(doc3, CtrlDir.getDirectoriObert().getDocs().get(idDoc));
        Document doc4 = new Document(CtrlDir.getDirectoriObert().getIdNouDoc(), "juli", "el_mateix_titol");
        CtrlDir.guardarDocument(doc4);
        assertEquals(doc3, CtrlDir.getDirectoriObert().getDocs().get(idDoc));
    }

    /**
     * Objecte de la prova: Test del mètode eliminarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina un document del directori
     * Operativa: es comprova que eliminar un document del directori funcioni correctament
     */
    @Test
    public void TestEliminarDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        Document doc = new Document(idDoc, "juli", "prova_eliminar_document");
        CtrlDir.guardarDocument(doc);
        assertEquals(doc, CtrlDir.getDirectoriObert().getDocs().get(idDoc));

        //ERROR: No existeix el document amb identificador idDoc
        CtrlDir.eliminarDocument(3);

        //Funcionament correcte
        CtrlDir.eliminarDocument(doc.getIdDoc());
        assertNull(CtrlDir.getDirectoriObert().getDocs().get(idDoc));
    }
}
