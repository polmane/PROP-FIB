package FONTS.Test;

import FONTS.Classes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestDocument {

    /**
     * Objecte de la prova: Test dels mètodes creadors de la classe Document
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es crea un objecte Document
     * Operativa: 1) el primer grup d'asserts comprova que podem crear un document amb tots els camps complerts menys el contingut
     *            2) el segon grup d'asserts comprova que el document es pot crear amb totes les variables possibles de la classe Document
     */
    @Test
    public void TestConstructores() {
        int idDoc = 0;
        String autor = "pol";
        String titol = "prova";
        Document doc1 = new Document(idDoc, autor, titol);
        assertEquals(0, doc1.getIdDoc());
        assertEquals("pol",doc1.getAutor());
        assertEquals("prova",doc1.getTitol());
        assertNull(doc1.getContingut());

        String contingut = "Estem provant la nostra creadora";
        titol = "prova2";
        Document doc2 = new Document(idDoc, autor, titol, contingut);
        assertEquals(0, doc2.getIdDoc());
        assertEquals("pol",doc2.getAutor());
        assertEquals("prova2",doc2.getTitol());
        assertEquals("Estem provant la nostra creadora", doc2.getContingut());
    }
}
