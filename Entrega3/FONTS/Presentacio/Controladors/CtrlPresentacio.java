package Presentacio.Controladors;

import java.util.*;
import Domini.Classes.Pair;
import Domini.Classes.Document;;
import Domini.Controladors.CtrlDomini;
import Presentacio.Vistes.*;

import java.util.List;

public class CtrlPresentacio {

    public CtrlDomini _ctrlDomini;
    private vistaPaginaPrincipal vPrincipal = null;
    private vistaRecuperarSessio vRecuperarSessio;
    private vistaGestioExpressio vGestioExpressio;
    private vistaCrearDocument vCrearDoc;
    private vistaSeleccionarDocument vSelecDoc;

    private vistaVisualitzarModificarDocument vVisualModiDoc;
    private vistaContingutDocument vContingutDoc;
    private vistaDocumentsSemblants vDocsSemblants;
    private vistaDocumentsRellevants vDocsRellevants;
    private vistaCrearExpressio vCrearExp;

    private vistaModificarExpressio vModiExp;
    private vistaCerques vCerques;

    //VISTES
    public CtrlPresentacio() {
         _ctrlDomini = new CtrlDomini();
         vPrincipal = new vistaPaginaPrincipal(this);
    }

    public void iniPresentacio() {

        vPrincipal.hacerVisible();
        vPrincipal.desactivar();
        vRecuperarSessio = new vistaRecuperarSessio(this);
    }

    public void activarPagPrincipal() {
        vPrincipal.activar();
        //VistaPagPrincipal vPp = new VistaPagPrincipal();
    }

    public void activarGestioExpressio() {
        vGestioExpressio.activar();
    }

    public void ObrirVistaCrearDocument() {
        vPrincipal.desactivar();
        vCrearDoc = new vistaCrearDocument(this);
    }

    public void ObrirVistaSeleccionarDocument() {
        vPrincipal.desactivar();
        vSelecDoc = new vistaSeleccionarDocument(this);
    }

    public void ObrirVistaVisualitzarModificarDocument() {
        vPrincipal.desactivar();
        vVisualModiDoc = new vistaVisualitzarModificarDocument(this);
    }

    public void ObrirVistaContingutDocument() {
        vPrincipal.desactivar();
        vContingutDoc = new vistaContingutDocument(this);
    }

    public void ObrirVistaDocumentsSemblants() {
        vPrincipal.desactivar();
        vDocsSemblants = new vistaDocumentsSemblants(this);
    }

    public void ObrirVistaDocumentsRellevants() {
        vPrincipal.desactivar();
        vDocsRellevants = new vistaDocumentsRellevants(this);
    }

    public void ObrirVistaCerques() {
        vPrincipal.desactivar();
        vCerques = new vistaCerques(this);
    }

    public void ObrirVistaGestioExpressio() {
        vPrincipal.desactivar();
        vGestioExpressio = new vistaGestioExpressio(this);
    }

    public void ObrirVistaCrearExpressio() {
        vGestioExpressio.desactivar();
        vCrearExp = new vistaCrearExpressio(this);
    }

    public void ObrirVistaModificarExpressio() {
        vGestioExpressio.desactivar();
        vModiExp = new vistaModificarExpressio(this);
    }


    //FUNCIONS DE DOMINI

    public void crearDirectori(int id) {
        _ctrlDomini._ctrlDirectori.crearDirectori(id);
    }

    public int crearDocument(String autor, String titol, String contingut) { return _ctrlDomini.afegirDocument(autor, titol, contingut); }

    public int seleccionarDocument(int id) { return _ctrlDomini.seleccionarDocument(id); }

    public int modificarAutor(String autor) {
        return _ctrlDomini.modificarAutor(autor);
    }

    public int modificarTitol(String titol) {
        return _ctrlDomini.modificarTitol(titol);
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

    //public static void exportarDocument(CtrlDirectori.FILETYPE format, String path) { _ctrlDomini.exportarDocument(format,path); }

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

    public int afegirExp(String expressio)  { return _ctrlDomini.afegirExpressio(expressio); }

    public int seleccionarExpressio (int id) {
        return _ctrlDomini.seleccionarExpressio(id);
    }

    public int modificarExpressio(String exp){ return _ctrlDomini.modificarExpressio(exp); }

    public int eliminarExpressio(int id){
        return _ctrlDomini.eliminarExpressio(id);
    }

    public List<Pair<String, String>> selectPerExpressio(int id) { return _ctrlDomini.selectPerExpressio(id); }

    public ArrayList<String> llistarDocuments() {
        return _ctrlDomini.llistarDocuments();
    }
    public ArrayList<String> llistarExpressions() {
        return _ctrlDomini.llistarExpressions();
    }

    public ArrayList<String>  toStringDocActiu() {
        return _ctrlDomini.toStringDocActiu();
    }

    public ArrayList<String>  toStringExpActiva() {
        return _ctrlDomini.toStringExpActiva();
    }
}