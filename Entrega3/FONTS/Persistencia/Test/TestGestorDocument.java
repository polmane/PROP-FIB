import Classes.Document;
import Persistencia.Classes.GestorDirectori;
import Persistencia.Classes.GestorDocument;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGestorDocument {

    @Test //TODO: TEST BEN FET
    public void testExportarDocument() {
        GestorDocument gestor = new GestorDocument();
        Document doc = new Document(0, "Juli", "Test Exportar", "Contingut del text a exportar");

        gestor.exportarDocument(GestorDirectori.FILETYPE.XML, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        gestor.exportarDocument(GestorDirectori.FILETYPE.TXT, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        // gestor.exportarDocument(GestorDocument.FILETYPE.PROP, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
    }

}