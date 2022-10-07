package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

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
}
