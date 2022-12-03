package Entrega2.Persistencia.FONTS.Test;

import Entrega2.Persistencia.FONTS.Classes.GestorDocument;
import Entrega1.FONTS.Classes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGestorDocument {

    @Test //TODO: TEST BEN FET
    public void testExportarDocument() {
        GestorDocument gestor = new GestorDocument();
        Document doc = new Document(0, "Juli", "Test Exportar", "Contingut del text a exportar");

        gestor.exportarDocument(GestorDocument.FILETYPE.TXT, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        gestor.exportarDocument(GestorDocument.FILETYPE.XML, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        // gestor.exportarDocument(GestorDocument.FILETYPE.PROP, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
    }

}
