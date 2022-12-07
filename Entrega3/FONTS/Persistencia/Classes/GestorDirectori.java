package Persistencia.Classes;

import Classes.Directori;
import Classes.Document;
import Classes.Expressio;

import java.util.Collection;

public class GestorDirectori {

    public enum FILETYPE {
        TXT, XML, PROP
    }

    public void exportarDirectori(FILETYPE format, Directori directori, Collection<Expressio> expressions, String path) {
        GestorDocument g_doc = new GestorDocument();
        for (Document doc : directori.getDocs().values()) g_doc.exportarDocument(format, doc, path);
        GestorExpressio g_exp = new GestorExpressio();
        for (Expressio exp : expressions) g_exp.exportarExpressio(format, exp, path);
        exportarPesos(directori, path);
    }

    private void exportarPesos(Directori directori, String path) {
        //TODO: IMPLEMENTACIO
    }
}
