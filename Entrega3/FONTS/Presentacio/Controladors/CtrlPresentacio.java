package Presentacio.Controladors;

import java.util.*;
import Domini.Classes.Pair;
import Domini.Classes.Document;
import Domini.Controladors.CtrlDirectori;
import Domini.Controladors.CtrlDomini;
import Domini.Controladors.CtrlExpressio;
import Presentacio.Vistes.*;

import java.util.List;

public class CtrlPresentacio {

    private static CtrlDomini cd = new CtrlDomini(new CtrlDirectori(), new CtrlExpressio());

    //VISTES

    public static void vistaCrearDirectori() {
        VistaCrearDirectori vCd = new VistaCrearDirectori();
    }

    public static void vistaPagPrincipal() {
        VistaPagPrincipal vPp = new VistaPagPrincipal();
    }

    public static void vistaAfegirExpressio() {
        VistaAfegirExpressio vAexp = new VistaAfegirExpressio();
    }

    public static void vistaCrearDocument() {
        VistaCrearDocument vCdoc = new VistaCrearDocument();
    }

    public static void vistaCarregarDocument() { VistaCarregarDocument vCDoc = new VistaCarregarDocument(); }

    public static void vistaPaginaOpcions(int id, String autor, String titol, String contingut) {
        VistaPaginaOpcions vPop = new VistaPaginaOpcions(id, autor, titol, contingut);
    }

    public static void vistaModificarDocument(int id, String autor, String titol, String contingut) {
        VistaModificarDocument vMdoc = new VistaModificarDocument(id, autor, titol, contingut);
    }

    /**public static void vistaInfoDocument(int id, String autor, String titol, String contingut) {
        VistaInfoDocument vIdoc = new VistaInfoDocument(id, autor, titol, contingut);
    }*/

    public static void vistaContingutDocument(int id, String autor, String titol, String contingut) {
        VistaContingutDocument vCdoc = new VistaContingutDocument(id, autor, titol, contingut);
    }

    public static void vistaDocsSemblants(int id, String autor, String titol, String contingut) {
        VistaDocsSemblants vDocsS = new VistaDocsSemblants(id, autor, titol, contingut);
    }

    public static void vistaDocsSemblantsPerExp(int id, String autor, String titol, String contingut) {
        VistaDocsSemblantsPerExp vSPexp = new VistaDocsSemblantsPerExp(id, autor, titol, contingut);
    }

    //FUNCIONS DE DOMINI

    public static void crearDirectori(int id) {
        cd._ctrlDirectori.crearDirectori(id);
    }

    public static int crearDocument(String autor, String titol, String contingut) { return cd.afegirDocument(titol, autor, contingut); }

    public static int seleccionarDocument(int id) { return cd.seleccionarDocument(id); }

    public static int afegirExp(String expressio)  { return cd.afegirExpressio(expressio); }

    public static int modificarAutor(String autor) {
        return cd.modificarAutor(autor);
    }

    public static int modificarTitol(String titol) {
        return cd.modificarAutor(titol);
    }

    public static int modificarContingut(String contingut) {
        return cd.modificarContingut(contingut);
    }

    public static List<Pair<String, String>> compararDocuments(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, int k, int id) {
        return cd.compararDocuments(m, s, k, id);
    }

    public static List<Pair<String, String>> compararQuery(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, int k, String paraules) {
        return cd.compararQuery(m, s, k, paraules);
    }

    //public static void exportarDocument(CtrlDirectori.FILETYPE format, String path) { cd.exportarDocument(format,path); }

    public static int eliminarDocument(int id){
        return cd.eliminarDocument(id);
    }

    public static String cercaPerAutoriTitol(String autor, String titol) { return cd.cercaPerAutoriTitol(autor, titol); }

    public static List<String> llistaAutorsPerPrefix(String pre , CtrlDirectori.SORTING s) { return cd.llistaAutorsPerPrefix(pre, s); }

    public static List<String> llistaTitolsPerAutor(String autor, CtrlDirectori.SORTING s) { return cd.llistaTitolsPerAutor(autor,s); }

    public static int seleccionarExpressio (int id) {
        return cd.seleccionarExpressio(id);
    }

    public static int modificarExpressio(String exp){ return cd.modificarExpressio(exp); }

    public static int eliminarExpressio(int id){
        return cd.eliminarExpressio(id);
    }

    public static ArrayList<Document> selectPerExpressio(int id) { return cd.selectPerExpressio(id); }

}