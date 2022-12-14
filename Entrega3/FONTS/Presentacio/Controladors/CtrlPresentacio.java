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

    private static CtrlDomini _ctrlDomini;
    private VistaPagPrincipal vPrincipal;

    //VISTES
    public CtrlPresentacio() {
         _ctrlDomini = new CtrlDomini(new CtrlDirectori(), new CtrlExpressio());
         vPrincipal = new VistaPagPrincipal();
    }

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

    public static void vistaPaginaOpcions(int id, String autor, String titol, String contingut) {
        VistaPaginaOpcions vPop = new VistaPaginaOpcions(id, autor, titol, contingut);
    }

    public static void vistaModificarDocument(int id, String autor, String titol, String contingut) {
        VistaModificarDocument vMdoc = new VistaModificarDocument(id, autor, titol, contingut);
    }

    public static void vistaInfoDocument(int id) {
        VistaInfoDocument vIdoc = new VistaInfoDocument();
    }

    public static void vistaContingutDocument(int id, String autor, String titol, String contingut) {
        VistaContingutDocument vCdoc = new VistaContingutDocument(id, autor, titol, contingut);
    }

    public static void vistaDocsSemblants() {
        VistaDocsSemblants vDocsS = new VistaDocsSemblants();
    }

    public static void vistaDocsSemblantsPerExp() {
        VistaDocsSemblantsPerExp vSPexp = new VistaDocsSemblantsPerExp();
    }

    //FUNCIONS DE DOMINI

    public static void crearDirectori(int id) {
        _ctrlDomini._ctrlDirectori.crearDirectori(id);
    }

    public static int crearDocument(String autor, String titol, String contingut) { return _ctrlDomini.afegirDocument(titol, autor, contingut); }

    public static int seleccionarDocument(int id) { return _ctrlDomini.seleccionarDocument(id); }

    public static int afegirExp(String expressio)  { return _ctrlDomini.afegirExpressio(expressio); }

    public static int modificarAutor(String autor) {
        return _ctrlDomini.modificarAutor(autor);
    }

    public static int modificarTitol(String titol) {
        return _ctrlDomini.modificarAutor(titol);
    }

    public static int modificarContingut(String contingut) {
        return _ctrlDomini.modificarContingut(contingut);
    }

    public static List<Pair<String, String>> compararDocuments(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, int k, int id) {
        return _ctrlDomini.compararDocuments(m, s, k, id);
    }

    public static List<Pair<String, String>> compararQuery(CtrlDirectori.METODE_COMPARACIO m, CtrlDirectori.SORTING s, int k, String paraules) {
        return _ctrlDomini.compararQuery(m, s, k, paraules);
    }

    //public static void exportarDocument(CtrlDirectori.FILETYPE format, String path) { cd.exportarDocument(format,path); }

    public static int eliminarDocument(int id){
        return _ctrlDomini.eliminarDocument(id);
    }

    public static String cercaPerAutoriTitol(String autor, String titol) { return _ctrlDomini.cercaPerAutoriTitol(autor, titol); }

    public static List<String> llistaAutorsPerPrefix(String pre , CtrlDirectori.SORTING s) { return _ctrlDomini.llistaAutorsPerPrefix(pre, s); }

    public static List<String> llistaTitolsPerAutor(String autor, CtrlDirectori.SORTING s) { return _ctrlDomini.llistaTitolsPerAutor(autor,s); }

    public static int seleccionarExpressio (int id) {
        return _ctrlDomini.seleccionarExpressio(id);
    }

    public static int modificarExpressio(String exp){ return _ctrlDomini.modificarExpressio(exp); }

    public static int eliminarExpressio(int id){
        return _ctrlDomini.eliminarExpressio(id);
    }

    public static ArrayList<Document> selectPerExpressio(int id) { return _ctrlDomini.selectPerExpressio(id); }

}