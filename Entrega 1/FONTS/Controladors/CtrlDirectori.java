package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;
import FONTS.Classes.Expressio;

import java.util.Vector;
import javax.print.Doc;

public class CtrlDirectori {
    /**
     * Representa el directori que s'està utilitzant
     */
    private Directori directoriObert;

    /**
     * Representa el document carregat en el directori
     */
    private Document documentActiu;

    /**
     * Representa una Expressio
     */
    private Expressio expressio;

    /**
     * Constructora
     */
    public CtrlDirectori() {
        directoriObert = null;
        documentActiu = null;
    }

    /**
     * Getters de directoriObert i documentActiu
     */
    public Directori getDirectoriObert() {
        return directoriObert;
    }
    public Document getDocumentActiu() {
        return documentActiu;
    }

    /**
     * Operació per crear un directori en el nostre sistema
     */
    public void crearDirectori(int idDir) {
        if (directoriObert == null) {
            directoriObert = new Directori(idDir);
        }
    }

    public void carregarDocument(int idDoc) {

    }


    /**
     * Modifica l'autor del document actiu
     * @param autor és el nou nom d'autor que es vol utilitzar pel document
     */
    public void modificarAutor(String autor) {
        documentActiu.setAutor(autor);
    }

    /**
     * Modifica el títol del document actiu
     * @param titol és el nou nom del títol que es vol utilitzar pel document
     */
    public void modificarTitol(String titol) {
        documentActiu.setTitol(titol);
    }

    /**
     * Modifica el contingut del document actiu
     * @param contingut és el nou contingut que es vol utilitzar pel document
     */
    public void modificarContngut(String contingut) {
        documentActiu.setContingut(contingut);
    }

    /**
     * Fa una cerca dels documents que contenen les paraules de la expressió
     * que es passa com a parametre
     * @param expressio conté les paraules que volem buscar en els documents
     * @return Vector<Document> retorna els documents que compleixen els criteris
     * de cerca de la expressió passada com a paràmetre
     */
    public Vector<Document> selectPerExpressio(Expressio expressio) {
        Vector<Document> v = new Vector<Document>();
        String exp = expressio.getExpressio();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (c == )
        }
        return v;
    }
}
