package Entrega2.Persistencia.FONTS.Test;

import Entrega1.FONTS.Classes.Expressio;
import Entrega2.Persistencia.FONTS.Classes.GestorDirectori;
import Entrega2.Persistencia.FONTS.Classes.GestorExpressio;
import org.junit.Test;

public class TestGestorExpressio {

    @Test //TODO: TEST BEN FET
    public void testExportarExpressio() {
        GestorExpressio gestor = new GestorExpressio();
        Expressio expressio = new Expressio(0, "Aquesta & es | ({una, expressio} & \"per guardar\")");

        gestor.exportarExpressio(GestorDirectori.FILETYPE.TXT, expressio, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        gestor.exportarExpressio(GestorDirectori.FILETYPE.XML, expressio, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
        // gestor.exportarDocument(GestorDocument.FILETYPE.PROP, doc, "D:/Juli/01_Uni/Q5/PROP/subgrup-prop11.1/Entrega2/Persistencia/Exported");
    }
}
