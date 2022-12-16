package Presentacio.Controladors;

import java.util.*;
import Domini.Classes.Pair;
import Domini.Classes.Document;
import Domini.Controladors.CtrlDirectori;
import Domini.Controladors.CtrlDomini;
import Presentacio.Vistes.*;

import java.util.List;

public class CtrlPresentacio {

    private CtrlDomini _ctrlDomini;
    private VistaPagPrincipal vPrincipal = null;
    private VistaCrearDirectori vCd;
    private VistaAfegirExpressio vAexp;
    private VistaCrearDocument vCrearDoc;
    private VistaCarregarDocument vCarregarDoc;
    private VistaPaginaOpcions vPop;
    private VistaModificarDocument vMdoc;
    private VistaInfoDocument vIdoc;
    private VistaContingutDocument vCdoc;
    private VistaDocsSemblants vDocsS;
    private VistaDocsSemblantsPerExp vSPexp;


    //VISTES
    public CtrlPresentacio() {
         _ctrlDomini = new CtrlDomini();
         vPrincipal = new VistaPagPrincipal(this);
    }

    public void iniPresentacio() {

        vPrincipal.hacerVisible();
        vPrincipal.desactivar();
        VistaCrearDirectori vCd = new VistaCrearDirectori(this);
    }

    public void activarPagPrincipal() {
        vPrincipal.activar();
        //VistaPagPrincipal vPp = new VistaPagPrincipal();
    }

    public void vistaAfegirExpressio() {
        vPrincipal.desactivar();
        vAexp = new VistaAfegirExpressio(this);
    }

    public void vistaCrearDocument() {
        vPrincipal.desactivar();
        vCrearDoc = new VistaCrearDocument(this);
    }

    public void vistaCarregarDocument() {
        vCarregarDoc = new VistaCarregarDocument();
    }

    public void vistaPaginaOpcions(int id, String autor, String titol, String contingut) {
        vPop = new VistaPaginaOpcions(this);
    }

    public void vistaModificarDocument(int id, String autor, String titol, String contingut) {
        vMdoc = new VistaModificarDocument(this);
    }

    public void vistaInfoDocument(int id) {
        vIdoc = new VistaInfoDocument(this);
    }

    public void vistaContingutDocument(int id, String autor, String titol, String contingut) {
        vCdoc = new VistaContingutDocument(this);
    }

    public void vistaDocsSemblants() {
        vDocsS = new VistaDocsSemblants(this);
    }

    public void vistaDocsSemblantsPerExp() {
        vSPexp = new VistaDocsSemblantsPerExp(this);
    }

    //FUNCIONS DE DOMINI

    public void crearDirectori(int id) {
        _ctrlDomini._ctrlDirectori.crearDirectori(id);
    }

    public int crearDocument(String autor, String titol, String contingut) { return _ctrlDomini.afegirDocument(titol, autor, contingut); }

    public int seleccionarDocument(int id) { return _ctrlDomini.seleccionarDocument(id); }

    public int afegirExp(String expressio)  { return _ctrlDomini.afegirExpressio(expressio); }

    public int modificarAutor(String autor) {
        return _ctrlDomini.modificarAutor(autor);
    }

    public int modificarTitol(String titol) {
        return _ctrlDomini.modificarAutor(titol);
    }

    public int modificarContingut(String contingut) {
        return _ctrlDomini.modificarContingut(contingut);
    }

    public List<Pair<String, String>> compararDocuments(String metodeComp, String sorting, int k, int id) {
        return _ctrlDomini.compararDocuments(metodeComp, sorting, k, id);
    }

    public List<Pair<String, String>> compararQuery(String metodeComp, String sorting, int k, String paraules) {
        return _ctrlDomini.compararQuery(metodeComp, sorting, k, paraules);
    }

    //public static void exportarDocument(CtrlDirectori.FILETYPE format, String path) { cd.exportarDocument(format,path); }

    public int eliminarDocument(int id){
        return _ctrlDomini.eliminarDocument(id);
    }

    public String cercaPerAutoriTitol(String autor, String titol) { return _ctrlDomini.cercaPerAutoriTitol(autor, titol); }

    public List<String> llistaAutorsPerPrefix(String pre , String sorting) {
        return _ctrlDomini.llistaAutorsPerPrefix(pre, sorting);
    }

    public List<String> llistaTitolsPerAutor(String autor, String sorting) {
        return _ctrlDomini.llistaTitolsPerAutor(autor,sorting);
    }

    public int seleccionarExpressio (int id) {
        return _ctrlDomini.seleccionarExpressio(id);
    }

    public int modificarExpressio(String exp){ return _ctrlDomini.modificarExpressio(exp); }

    public int eliminarExpressio(int id){
        return _ctrlDomini.eliminarExpressio(id);
    }

    public ArrayList<Document> selectPerExpressio(int id) { return _ctrlDomini.selectPerExpressio(id); }

}