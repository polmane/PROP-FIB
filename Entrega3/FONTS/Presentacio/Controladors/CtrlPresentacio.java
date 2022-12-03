package FONTS.Controladors;

import FONTS.Domini.CtrlDomini;

public class CtrlPresentacio {

    private static CtrlDomini cd = new CtrlDomini();

    //VISTES

    public static void iniPresentacio() {
        VistaCrearDirectori vCd = new VistaCrearDirectori();
    }

    public static void vistaPagPrincipal(int id) {
        VistaPagPrincipal vPp = new VistaPagPrincipal(id);
    }

    public static void VistaAfegirExpressio() {
        VistaAfegirExpressio() vAexp = new VistaAfegirExpressio();
    }

    public static void vistaCrearDocument() {
        VistaCrearDocument vCdoc = new VistaCrearDocument();
    }

    public static void vistaPaginaOpcions(int id) {
        VistaPaginaOpcions vPop = new VistaPaginaOpcions(id);
    }

    public static void vistaModificarDocument(int id) {
        VistaModificarDocument vMdoc = new VistaModificarDocument(id);
    }

    public static void vistaInfoDocument(int id) {
        VistaInfoDocument vIdoc = new VistaInfoDocument(id);
    }

    public static void vistaContingutDocument() {
        VistaContingutDocument vCdoc = new VistaContingutDocument();
    }

    public static void vistaDocsSemblants() {
        VistaDocsSemblants vDocsS = new VistaDocsSemblants();
    }

    public static void vistaDocsSemblantsPerExp() {
        VistaDocsSemblantsPerExp vSPexp = new VistaDocsSemblantsPerExp();
    }

    //FUNCIONS DE DOMINI

    public static void crearDirectori(int id) {
        cd._ctrlDirectori.crearDirectori(id);
    }

    public static void crearDocument(int id, String autor, String titol) {
        cd.
    }

    public static void obrirDocument(CtrlDirectori.FILETYPE format, String path) {
        cd.exportarDocument(format, path);
    }







}