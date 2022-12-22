package Presentacio.Controladors;

import Domini.Classes.Pair;
import Domini.Controladors.CtrlDomini;
import Presentacio.Vistes.*;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un Controlador de Presentació
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class CtrlPresentacio {

    /**
     * Representa un controlador de Domini
     */
    public CtrlDomini _ctrlDomini;
    /**
     * Representa la vista de la Pàgina Principal
     */
    private vistaPaginaPrincipal vPrincipal = null;
    /**
     * Representa la vista de la Pàgina Recuperar Sessió
     */
    private vistaRecuperarSessio vRecuperarSessio;
    /**
     * Representa la vista de la Pàgina on es gestiona una Expressió
     */
    private vistaGestioExpressio vGestioExpressio;
    /**
     * Representa la vista de la Pàgina on es crea un Document
     */
    private vistaCrearDocument vCrearDoc;
    /**
     * Representa la vista de la Pàgina on es selecciona un Document
     */
    private vistaSeleccionarDocument vSelecDoc;
    /**
     * Representa la vista de la Pàgina on es visualitza i es modifica un document
     */
    private vistaVisualitzarModificarDocument vVisualModiDoc;
    /**
     * Representa la vista de la Pàgina on es fa la cerca del contingut d'un Document
     */
    private vistaContingutDocument vContingutDoc;
    /**
     * Representa la vista de la Pàgina on es fa la cerca dels documents semblants a un document seleccionat
     */
    private vistaDocumentsSemblants vDocsSemblants;
    /**
     * Representa la vista de la Pàgina on es fa la cerca dels documents més rellevants segons una query de paraules
     */
    private vistaDocumentsRellevants vDocsRellevants;
    /**
     * Representa la vista de la Pàgina on es crea una Expressió
     */
    private vistaCrearExpressio vCrearExp;
    /**
     * Representa la vista de la Pàgina on es modifica una Expressió
     */
    private vistaModificarExpressio vModiExp;
    /**
     * Representa la vista de la Pàgina on es fa la cerca dels títols d'un autor o dels autors que comencen per un prefix
     */
    private vistaCerques vCerques;


    /**
     * Constructora del Controlador de Presentació
     */
    public CtrlPresentacio() {
         _ctrlDomini = new CtrlDomini();
         int codi = _ctrlDomini.carregarExpressions();
         if (codi == -50) System.out.println("No hi ha expressions");
         else {
             System.out.println("Expressions carregades correctament");
         }
         vPrincipal = new vistaPaginaPrincipal(this);
    }

    /**
     * Constructora de la vistaRecuperarSessio
     */
    public void iniPresentacio() {

        vPrincipal.hacerVisible();
        vPrincipal.desactivar();
        vRecuperarSessio = new vistaRecuperarSessio(this);
    }

    /**
     * Mètode que activa la vistaPagPrincipal
     */
    public void activarPagPrincipal() {
        vPrincipal.activar();
    }

    /**
     * Mètode que activa la vistaGestioExpressio
     */
    public void activarGestioExpressio() {
        vGestioExpressio.activar();
    }

    /**
     * Constructora de la vistaCrearDocument
     */
    public void ObrirVistaCrearDocument() {
        vPrincipal.desactivar();
        vCrearDoc = new vistaCrearDocument(this);
    }

    /**
     * Constructora de la vistaSeleccionarDocument
     */
    public void ObrirVistaSeleccionarDocument() {
        vPrincipal.desactivar();
        vSelecDoc = new vistaSeleccionarDocument(this);
    }

    /**
     * Constructora de la vistaVisualitzarModificarDocument
     */
    public void ObrirVistaVisualitzarModificarDocument() {
        vPrincipal.desactivar();
        vVisualModiDoc = new vistaVisualitzarModificarDocument(this);
    }

    /**
     * Constructora de la vistaContingutDocument
     */
    public void ObrirVistaContingutDocument() {
        vPrincipal.desactivar();
        vContingutDoc = new vistaContingutDocument(this);
    }

    /**
     * Constructora de la vistaDocumentsSemblants
     */
    public void ObrirVistaDocumentsSemblants() {
        vPrincipal.desactivar();
        vDocsSemblants = new vistaDocumentsSemblants(this);
    }

    /**
     * Constructora de la vistaDocumentsRellevants
     */
    public void ObrirVistaDocumentsRellevants() {
        vPrincipal.desactivar();
        vDocsRellevants = new vistaDocumentsRellevants(this);
    }

    /**
     * Constructora de la vistaCerques
     */
    public void ObrirVistaCerques() {
        vPrincipal.desactivar();
        vCerques = new vistaCerques(this);
    }

    /**
     * Constructora de la vistaGestioExpressio
     */
    public void ObrirVistaGestioExpressio() {
        vPrincipal.desactivar();
        vGestioExpressio = new vistaGestioExpressio(this);
    }

    /**
     * Constructora de la vistaCrearExpressio
     */
    public void ObrirVistaCrearExpressio() {
        vGestioExpressio.desactivar();
        vCrearExp = new vistaCrearExpressio(this);
    }

    /**
     * Constructora de la vistaModificarExpressio
     */
    public void ObrirVistaModificarExpressio() {
        vGestioExpressio.desactivar();
        vModiExp = new vistaModificarExpressio(this);
    }



    /**
     * Funció que crea un directori
     * @param id id del directori a crear
     * @return retorna la funció de Domini per crear un directori
     */
    public int crearDirectori(int id) {return _ctrlDomini.crearDirectori(id);}

    /**
     * Funció que crea un document
     * @param autor del document a crear
     * @param titol del document a crear
     * @param contingut del document a crear
     * @return retorna la funció de Domini per crear un document
     */
    public int crearDocument(String autor, String titol, String contingut) { return _ctrlDomini.afegirDocument(autor, titol, contingut); }

    /**
     * Funció que selecciona un document
     * @param id id del document a seleccionar
     * @return retorna la funció de Domini per seleccionar un document
     */
    public int seleccionarDocument(int id) { return _ctrlDomini.seleccionarDocument(id); }

    /**
     * Funció que modifica l'autor d'un document
     * @param autor nou valor de la variable autor
     * @return retorna la funció de Domini per modificar un autor
     */
    public int modificarAutor(String autor) {
        return _ctrlDomini.modificarAutor(autor);
    }

    /**
     * Funció que modifica el títol d'un document
     * @param titol nou valor de la variable títol
     * @return retorna la funció de Domini per modificar un títol
     */
    public int modificarTitol(String titol) {
        return _ctrlDomini.modificarTitol(titol);
    }

    /**
     * Funció que modifica el contingut d'un document
     * @param contingut nou valor de la variable autor
     * @return retorna la funció de Domini per modificar un títol
     */
    public int modificarContingut(String contingut) {
        return _ctrlDomini.modificarContingut(contingut);
    }

    /**
     * Funció que permet comparar documents i obtenir-ne el nivell de similaritat
     * @param metodeComp mètode de comparació (enum METODE_COMPARACIO)
     * @param sorting mètode d'ordenació del resultat (enum SORTING)
     * @param k nombre de documents similars a trobar
     * @param id identificador del document que s'utilitarà per fer les comparacions
     * @return retorna la funció de Domini per comparar documents
     */
    public List<Pair<String, String>> compararDocuments(String metodeComp, String sorting, int k, int id) {
        return _ctrlDomini.compararDocuments(metodeComp, sorting, k, id);
    }

    /**
     * Funció que permet comparar els documents del sistema amb una query de paraules entrada per l'usuari
     * @param metodeComp mètode de comparació (enum METODE_COMPARACIO)
     * @param sorting mètode d'ordenació (enum SORTING)
     * @param k nombre de documents similars a trobar
     * @param paraules query de paraules a comparar
     * @return retorna la funció de Domini per comparar els documents amb una query
     */
    public List<Pair<String, String>> compararQuery(String metodeComp, String sorting, int k, String paraules) {
        return _ctrlDomini.compararQuery(metodeComp, sorting, k, paraules);
    }

    /**
     * Funció per exportar un document determinat a l'ordinador
     * @param format format esperat d'exportació
     * @param path path on volem guardar el document
     * @return retorna la funció de Domini per exportar un document
     */
    public int exportarDocument(String format, String path) { return _ctrlDomini.exportarDocument(format,path); }

    /**
     * Funció per importar documents de l'ordinador al sistema
     * @param paths array de paths on hi han els documents
     * @return retorna la funció de Domini per importar un document
     */
    public int importarDocument(ArrayList<String> paths) {return _ctrlDomini.importarDocument(paths);}

    /**
     * Funció per eliminar un document del directori
     * @param id id del document a eliminar
     * @return retorna la funció de Domini per eliminar un document
     */
    public int eliminarDocument(int id){
        return _ctrlDomini.eliminarDocument(id);
    }

    /**
     * Funció per trobat el contingut donar un autor i títol
     * @param autor valor de la variable autor a buscar
     * @param titol valor de la variable títol a buscar
     * @return retorna la funció de Domini per buscar el contingut d'un document donats títol i autor
     */
    public String cercaPerAutoriTitol(String autor, String titol) { return _ctrlDomini.cercaPerAutoriTitol(autor, titol); }

    /**
     * Funció per llistar els autors del nostre sistema que començen per un prefix determinat
     * @param pre prefix pel que buscar els autors similars
     * @param sorting mètode d'ordenació (enum SORTING)
     * @return retorna la funció de Domini per llistar els autors que comencen per un prefix
     */
    public List<String> llistaAutorsPerPrefix(String pre , String sorting) {
        return _ctrlDomini.llistaAutorsPerPrefix(pre, sorting);
    }

    /**
     * Funció per llistar els títols del nostre sistema que començen per un autor determinat
     * @param autor autor pel que buscar els titols
     * @param sorting mètode d'ordenació (enum SORTING)
     * @return retorna la funció de Domini per llistar els títols d'un autor
     */
    public List<String> llistaTitolsPerAutor(String autor, String sorting) {
        return _ctrlDomini.llistaTitolsPerAutor(autor,sorting);
    }

    /**
     * Funció per afegir una expressió dins el nostre sistema
     * @param expressio expressió que volem guardar
     * @return retorna la funció de Domini per afegir una expressió
     */
    public int afegirExp(String expressio)  { return _ctrlDomini.afegirExpressio(expressio); }

    /**
     * Funció per seleccionar l'expressió seleccionada
     * @param id id del document a seleccionar
     * @return retorna la funció de Domini per seleccionar una expressió
     */
    public int seleccionarExpressio (int id) {
        return _ctrlDomini.seleccionarExpressio(id);
    }

    /**
     * Funció per modificar l'expressió seleccionada
     * @param exp nou valor de la variable expressió
     * @return retorna la funció de Domini per modificar una expressió
     */
    public int modificarExpressio(String exp){ return _ctrlDomini.modificarExpressio(exp); }

    /**
     * Funció per eliminar una expressió del sistema
     * @param id id de l'expressió a eliminar
     * @return retorna la funció de Domini per eliminar una expressió
     */
    public int eliminarExpressio(int id){
        return _ctrlDomini.eliminarExpressio(id);
    }

    /**
     * Funció per seleccionar els document que compleixen una expressió
     * @param id id de l'expressió a comparar
     * @return retorna la funció de Domini per fer una cerca dels documents que compleixen una expressió
     */
    public List<Pair<String, String>> selectPerExpressio(int id) { return _ctrlDomini.selectPerExpressio(id); }

    /**
     * Funció per llistar els documents actius del programa
     * @return retorna la funció de Domini per llistar els documents actius
     */
    public ArrayList<String> llistarDocuments() {
        return _ctrlDomini.llistarDocuments();
    }

    /**
     * Funció per carregar les expressions actives en el directori
     * @return retorna la funció de Domini per llistar les expressions actives
     */
    public ArrayList<String> llistarExpressions() {
        return _ctrlDomini.llistarExpressions();
    }

    /**
     * Funció obtenir la informació del document seleccionat
     * @return retorna la funció de Domini per conseguir la informació del document seleccionat
     */
    public ArrayList<String>  toStringDocActiu() {
        return _ctrlDomini.toStringDocActiu();
    }

    /**
     * Funció per obtenir la informació de l'expressió seleccionada
     * @return retorna retorna la funció de Domini per conseguir la informació de l'expressió seleccionada
     */
    public ArrayList<String>  toStringExpActiva() {
        return _ctrlDomini.toStringExpActiva();
    }

    /**
     * Funció per carregar l'estat del programa un cop es torna a executar el codi
     * @return retorna la funció de Domini per carregar l'estat del programa
     */
    public int carregarEstat() { return _ctrlDomini.carregarEstat(); }

    /**
     * Funció que permet guardar l'estat del programa un cop l'usuari tanca l'app
     * @return retorna la funció de Domini per guardar l'estat del programa
     */
    public int guardarEstat() {return _ctrlDomini.guardarEstat();}

}