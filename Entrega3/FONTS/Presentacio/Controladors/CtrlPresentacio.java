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

    public static void iniPresentacio() {
        VistaCrearDirectori vCd = new VistaCrearDirectori();
    }

    public static void PagPrincipal() {
        VistaPagPrincipal vPp = new VistaPagPrincipal();
    }

    public static void vistaAfegirExpressio() {
        VistaAfegirExpressio vAexp = new VistaAfegirExpressio();
    }

    public static void vistaCrearDocument() {
        VistaCrearDocument vCdoc = new VistaCrearDocument();
    }

    public static void vistaCarregarDocument() { VistaCarregarDocument vCDoc = new VistaCarregarDocument(); }

    public static void vistaPaginaOpcions(int id) {
        VistaPaginaOpcions vPop = new VistaPaginaOpcions(id);
    }

    public static void vistaModificarDocument(int id) {
        VistaModificarDocument vMdoc = new VistaModificarDocument();
    }

    public static void vistaInfoDocument(int id) {
        VistaInfoDocument vIdoc = new VistaInfoDocument();
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