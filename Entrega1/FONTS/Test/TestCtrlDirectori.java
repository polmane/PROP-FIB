package Entrega1.FONTS.Test;


import Entrega1.FONTS.Classes.Pair;
import Entrega1.FONTS.Controladors.CtrlDirectori;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class TestCtrlDirectori {

    /**
     * Objecte de la prova: Test del mètode crearDirectori de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es crea un directori al controlador de directori
     * Operativa: Es comprova que al crear el controlador de directori inicialment no te cap directori
     *            obert, després es fa la crida a crearDirectori amb una id com a paràmetre. Aquesta
     *            funció no pot fallar i comprovem que el directori s'ha creat amb la mateixa id.
     */
    @Test
    public void TestCrearDirectori() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        assertNull(CtrlDir.getDirectoriObert());

        CtrlDir.crearDirectori(0);
        assertNotNull(CtrlDir.getDirectoriObert());
        assertEquals(0, CtrlDir.getDirectoriObert().getIdDir());
    }

    /**
     * Objecte de la prova: Test del mètode afegirDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'afegeix un document al directori
     * Operativa: Creem un nou document amb una crida al mètode afegirDocument i passant com a paràmetre
     *            tres Strings (autor, titol i contingut)
     *            Comprovem que detecti que els paràmetres siguin vàlids amb el codi d'error 30 (//Null arguments)
     *            Seguidament, comprovem que s'ha creat dins el nostre sistema (//Funcionament correcte)
     *            Finalment, comprovem que dona un error quan intentem crear un document ja existent (//ERROR:...)
     */
    @Test
    public void TestAfegirDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Null arguments
        assertEquals(30, CtrlDir.afegirDocument(null, "titol", ""));
        assertEquals(30, CtrlDir.afegirDocument("", "titol", ""));
        assertEquals(30, CtrlDir.afegirDocument("autor", null, ""));
        assertEquals(30, CtrlDir.afegirDocument("autor", "", ""));

        //Funcionament correcte
        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        String autor = "juli";
        String titol = "prova afegir document";
        String contingut = "document per a provar afegirDocument()";
        assertEquals(10, CtrlDir.afegirDocument(autor, titol, contingut));
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());

        //ERROR: ja existeix document amb el mateix autor i titol
        assertEquals(20, CtrlDir.afegirDocument(autor, titol, contingut));
    }

    /**
     * Objecte de la prova: Test del mètode eliminarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina un document del directori
     * Operativa: Creem un nou document utilitzant la crida a afegirDocument i passant com a paràmetre els tres Strings.
     *            A continuació, quan intentem eliminar-lo amb el mètode eliminarDocument i passant la id corresponent. Poden passar dues coses:
     *            El document s'elimina correctament (//Funcionament correcte)
     *            La id no existeix i dóna un codi d'error, l'integer 20 (//ERROR:...)
     */
    @Test
    public void TestEliminarDocument(){
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        CtrlDir.afegirDocument("juli", "prova eliminar document", "document per a provar eliminarDocument()");

        //ERROR: No existeix el document amb identificador idDoc
        CtrlDir.eliminarDocument(3);

        //Funcionament correcte
        CtrlDir.eliminarDocument(idDoc);
        assertNull(CtrlDir.getDirectoriObert().getDocs().get(idDoc));
    }

    /**
     * Objecte de la prova: Test del reciclatge d'identificadors de documents de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Reciclatge d'identificadors de documents
     * Operativa: Afegim un document al directori i en recordem la id. D'esprés l'eliminem.
     *            N'afegim un segon i comprovem que, buscant amb l'identificador del primer, trobem el segon document.
     */
    @Test
    public void TestReciclatgeIdentificadors(){
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("juli", "prova id eliminada", "document per a eliminar");
        int idDoc = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.eliminarDocument(idDoc);
        String autor = "juli";
        String titol = "prova id reciclada";
        String contingut = "aquest document hauria de reciclar l'identificador";
        CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());
    }

    /**
     * Objecte de la prova: Test del mètode seleccionarDocument de la classe CtrlDirectori.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional.
     * Valors estudiats: Funcionament correcte de la funció seleccionarDocument.
     * Operativa: Primer creem un nou document amb el mètode afegirDocument.
     *            A continuació, cridem l'operació seleccionarDocument passant com a paràmetre la id de l'expressió desitjada,
     *            el primer cas és una id correcte (//Funcionament correcte) i retorna el codi 10
     *            Finalment, intentem fer la crida amb una id incorrecte per assegurar-nos que retorna el codi d'error 20 (ERROR:...)
     */
    @Test
    public void TestSeleccionarDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("Juli","Prova","");
        CtrlDir.afegirDocument("Juli","Prova2","");
        CtrlDir.afegirDocument("Juli","Prova3","");
        assertEquals(2, CtrlDir.getDocumentActiu().getIdDoc());

        //Funcionament correcte
        assertEquals(10, CtrlDir.seleccionarDocument(0));
        assertEquals(0, CtrlDir.getDocumentActiu().getIdDoc());

        //ERROR: id fora de rang
        assertEquals(20, CtrlDir.seleccionarDocument(5));
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica l'autor del document actiu.
     * Operativa: Primer comprovem que retorna el codi d'error 31 quan intentem modificarAutor en un directori buit.
     *            Després creem tres documents amb l'operació afegirDocument
     *            Comprovem que detecti els arguments no vàlids (String null o buida) i retorna el codi 30
     *            A continuació, fem el test que impedeix tenir documents repetits amb el codi d'error 20
     *            Finalment comprovem el funcionament correcte, que retorna el codi 10
     */
    @Test
    public void TestModificarAutor() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Directori buit
        assertEquals(31, CtrlDir.modificarAutor("o"));

        CtrlDir.afegirDocument("Juli","Prova","");
        CtrlDir.afegirDocument("Pol","Prova","");
        CtrlDir.afegirDocument("Isaac","Prova","");

        //Null arguments
        assertEquals(30, CtrlDir.modificarAutor(null));
        assertEquals(30, CtrlDir.modificarAutor(""));
        assertEquals(30, CtrlDir.modificarAutor(" "));

        //ERROR: Document repetit
        assertEquals(20, CtrlDir.modificarAutor("Pol"));

        //Funcionament correcte
        assertEquals(10, CtrlDir.modificarAutor("Nou Autor"));
        assertEquals("Nou Autor", CtrlDir.getDocumentActiu().getAutor());
    }

    /**
     * Objecte de la prova: Test del mètode modificarTitol de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica el titol del document actiu.
     * Operativa: Primer comprovem que retorna el codi d'error 31 quan intentem modificarTitol en un directori buit.
     *            Després creem tres documents amb l'operació afegirDocument
     *            Comprovem que detecti els arguments no vàlids (String null o buida) i retorna el codi 30
     *            A continuació, fem el test que impedeix tenir documents repetits amb el codi d'error 20
     *            Finalment comprovem el funcionament correcte, que retorna el codi 10
     */
    @Test
    public void TestModificarTitol() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Directori bui
        assertEquals(31, CtrlDir.modificarTitol("o"));

        CtrlDir.afegirDocument("Juli","Prova1","");
        CtrlDir.afegirDocument("Juli","Prova2","");
        CtrlDir.afegirDocument("Juli","Prova3","");

        //Null arguments
        assertEquals(30, CtrlDir.modificarTitol(null));
        assertEquals(30, CtrlDir.modificarTitol(""));
        assertEquals(30, CtrlDir.modificarTitol(" "));

        //ERROR: Document repetit
        assertEquals(20, CtrlDir.modificarTitol("Prova1"));

        //Funcionament correcte
        assertEquals(10, CtrlDir.modificarTitol("Nou Titol"));
        assertEquals("Nou Titol", CtrlDir.getDocumentActiu().getTitol());
    }

    /**
     * Objecte de la prova: Test del mètode modificarContingut de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es modifica el contingut del document actiu.
     * Operativa: Primer comprovem que retorna el codi d'error 31 quan intentem modificarContingut en un directori buit.
     *            Després creem un document amb l'operació afegirDocument
     *            Finalment comprovem el funcionament correcte, que retorna el codi 10
     */
    @Test
    public void TestModificarContingut() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Directori bui
        assertEquals(31, CtrlDir.modificarContingut("o"));

        CtrlDir.afegirDocument("Juli","Prova","contingut inicial");

        //Funcionament correcte
        assertEquals(10, CtrlDir.modificarContingut("contingut final"));
        assertEquals("contingut final", CtrlDir.getDocumentActiu().getContingut());
    }

    /**
     * Objecte de la prova: Test del mètode compararDocuments de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'obtenen el nombre que es vol de documents més rellevants al comparar amb un altre
     *                      segons diferents mètodes de compració i ordenats també de diferents maneres.
     * Operativa: Primer comprovem que retorna null quan els arguments no son vàlids:
     *              - No es passa un mètode de comparació i/o d'ordenació
     *              - No es demana un nombre positiu de documents
     *              - L'identificador del document amb el qual comparar no és vàlid
     *            Després creem diferents documents per tal de fer la comparació, fem diferents crides per a
     *              comprovar que funcionen correctament els diferents mètodes de comparació i d'ordenació.
     *            Comprovem també que, si 'k' és més gran que el nombre de documents del directori, retornem tots
     *              els documents possibles
     *            Finalment comprovem que si només hi ha un document al directori no es pot fer la comparació
     */
    @Test
    public void TestCompararDocuments() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        int idDoc1, idDoc2, idDoc3, idDoc4, idDoc5;
        List<Pair<String, String>> ret;


        //Errors de null i d'arguments
        CtrlDir.afegirDocument("Pol","Prova","A A A A A");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Juli","Prova","A A A A A");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Isaac","Prova","A A A A A");
        idDoc3 = CtrlDir.getDocumentActiu().getIdDoc();
        assertNull(CtrlDir.compararDocuments(null, CtrlDirectori.SORTING.AUT_ASC, 1, idDoc1));
        assertNull(CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, null, 1, idDoc1));
        assertNull(CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 0, idDoc1));
        assertNull(CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 1, 4));
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);
        CtrlDir.eliminarDocument(idDoc3);


        //METODE_COMPARACIO I SORTING
        CtrlDir.afegirDocument("Juli","Titol1","hola bon dia");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Pau","Titol2","dia i dia");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Joan","Titol3","hola bon mati");
        idDoc3 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Jordi","Titol4","hola hola hola");
        idDoc4 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Pep","Titol5",    "hola hola hola hola");
        idDoc5 = CtrlDir.getDocumentActiu().getIdDoc();
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.SIM_DESC, 3, idDoc1);
        assertEquals("Joan", ret.get(0).first());
        assertEquals("Pep", ret.get(1).first());
        assertEquals("Jordi", ret.get(2).first());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.BOOL, CtrlDirectori.SORTING.SIM_DESC, 3, idDoc1);
        assertEquals("Pep", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Pau", ret.get(2).first());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.SIM_ASC, 3, idDoc1);
        assertEquals("Jordi", ret.get(0).first());
        assertEquals("Pep", ret.get(1).first());
        assertEquals("Joan", ret.get(2).first());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 3, idDoc1);
        assertEquals("Joan", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Pep", ret.get(2).first());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_DESC, 3, idDoc1);
        assertEquals("Pep", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Joan", ret.get(2).first());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.TIT_ASC, 3, idDoc1);
        assertEquals("Titol3", ret.get(0).second());
        assertEquals("Titol4", ret.get(1).second());
        assertEquals("Titol5", ret.get(2).second());
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.TIT_DESC, 3, idDoc1);
        assertEquals("Titol5", ret.get(0).second());
        assertEquals("Titol4", ret.get(1).second());
        assertEquals("Titol3", ret.get(2).second());
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);
        CtrlDir.eliminarDocument(idDoc3);
        CtrlDir.eliminarDocument(idDoc4);
        CtrlDir.eliminarDocument(idDoc5);


        //k > nombre documents
        CtrlDir.afegirDocument("Manel","Prova","el barri gotic de girona");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Isaac","Prova","fem un projecte de programació");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 4, idDoc1);
        assertEquals(1, ret.size());
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);


        //Un unic document
        CtrlDir.afegirDocument("Juli","Prova","A A A A A");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        ret = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 1, idDoc1);
        assertNull(ret);
        CtrlDir.eliminarDocument(idDoc1);
    }

    /**
     * Objecte de la prova: Test del mètode compararQuery de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'obtenen el nombre que es vol de documents més rellevants al comparar amb una query de Strings
     *                      segons diferents mètodes de compració i ordenats també de diferents maneres
     * Operativa: Primer comprovem que retorna null quan els arguments no son vàlids:
     *              - No es passa un mètode de comparació i/o d'ordenació
     *              - No es demana un nombre positiu de documents
     *              - La query no és vàlida (null o String buit)
     *              - No hi ha documents al directori
     *            Després Comprovem també que, si 'k' és més gran que el nombre de documents del directori, retornem tots
     *              els documents possibles
     *            Finalment creem diferents documents per tal de fer la comparació, fem diferents crides per a
     *              comprovar que funcionen correctament els diferents mètodes de comparació i d'ordenació
     */
    @Test
    public void TestCompararQuery() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        int idDoc1, idDoc2, idDoc3, idDoc4;
        List<Pair<String, String>> ret;
        String query = new String("hola bon dia");

        //Directori buit
        assertNull(CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 0, query));

        //Errors de null i d'arguments
        CtrlDir.afegirDocument("Pol","Prova","A A A A A");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Juli","Prova","A A A A A");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Isaac","Prova","A A A A A");
        idDoc3 = CtrlDir.getDocumentActiu().getIdDoc();
        assertNull(CtrlDir.compararQuery(null, CtrlDirectori.SORTING.AUT_ASC, 1, query));
        assertNull(CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, null, 1, query));
        assertNull(CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 0, query));
        assertNull(CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 1, null));
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);
        CtrlDir.eliminarDocument(idDoc3);


        //k > nombre documents
        CtrlDir.afegirDocument("Manel","Prova","hola hola hola");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Isaac","Prova","no tinc cap paraula");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 4, query);
        assertEquals(2, ret.size());
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);


        //METODE_COMPARACIO I SORTING
        CtrlDir.afegirDocument("Pau","Titol2","dia i dia");
        idDoc1 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Joan","Titol3","hola bon mati");
        idDoc2 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Jordi","Titol4","hola hola hola");
        idDoc3 = CtrlDir.getDocumentActiu().getIdDoc();
        CtrlDir.afegirDocument("Pep","Titol5",    "hola hola hola hola");
        idDoc4 = CtrlDir.getDocumentActiu().getIdDoc();
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.SIM_DESC, 3, query);
        assertEquals("Joan", ret.get(0).first());
        assertEquals("Pep", ret.get(1).first());
        assertEquals("Jordi", ret.get(2).first());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.BOOL, CtrlDirectori.SORTING.SIM_DESC, 3, query);
        assertEquals("Pep", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Pau", ret.get(2).first());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.SIM_ASC, 3, query);
        assertEquals("Jordi", ret.get(0).first());
        assertEquals("Pep", ret.get(1).first());
        assertEquals("Joan", ret.get(2).first());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_ASC, 3, query);
        assertEquals("Joan", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Pep", ret.get(2).first());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.AUT_DESC, 3, query);
        assertEquals("Pep", ret.get(0).first());
        assertEquals("Jordi", ret.get(1).first());
        assertEquals("Joan", ret.get(2).first());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.TIT_ASC, 3, query);
        assertEquals("Titol3", ret.get(0).second());
        assertEquals("Titol4", ret.get(1).second());
        assertEquals("Titol5", ret.get(2).second());
        ret = CtrlDir.compararQuery(CtrlDirectori.METODE_COMPARACIO.TF_IDF, CtrlDirectori.SORTING.TIT_DESC, 3, query);
        assertEquals("Titol5", ret.get(0).second());
        assertEquals("Titol4", ret.get(1).second());
        assertEquals("Titol3", ret.get(2).second());
        CtrlDir.eliminarDocument(idDoc1);
        CtrlDir.eliminarDocument(idDoc2);
        CtrlDir.eliminarDocument(idDoc3);
        CtrlDir.eliminarDocument(idDoc4);
    }

    /**
     * Objecte de la prova: Test del mètode cercaPerAutoriTitol de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Retorna el contingut del document amb l'autor i el titol que es passen com a paràmetres String
     * Operativa: Primer comprovem que retorni null en cas de fer la cerca en un directori buit
     *              A continuació afegim uns quants documents per a fer les proves
     *              Seguidament comprovem que retorni null si algun dels arguments no és un String vàlid
     *              Comprovem que si cap document té el mateix titol i autor que la cerca es retorni null
     *              Finalment comprovem que es retorni el contingut quan existeix el document que busquem
     */
    @Test
    public void TestCercaPerAutoriTitol() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Cas directori buit
        assertNull(CtrlDir.cercaPerAutoriTitol("Autor", "Titol"));

        CtrlDir.afegirDocument("Juli","Titol1","Juli1");
        CtrlDir.afegirDocument("Juli","Titol2","Juli2");
        CtrlDir.afegirDocument("Pol","Titol1","Pol1");
        CtrlDir.afegirDocument("Pol", "Titol2", "Pol2");
        CtrlDir.afegirDocument("Isaac","Titol1","Isaac1");
        CtrlDir.afegirDocument("Isaac", "Titol2", "Isaac2");

        //Nulls o string buida
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", null));
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", ""));
        assertNull(CtrlDir.cercaPerAutoriTitol(null, "Titol1"));
        assertNull(CtrlDir.cercaPerAutoriTitol("", "Titol1"));

        //Titol erroni
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", "TitolErroni"));

        //Autor erroni
        assertNull(CtrlDir.cercaPerAutoriTitol("AutorErroni", "Titol1"));

        //Funcionament correcte
        assertEquals("Pol2", CtrlDir.cercaPerAutoriTitol("Pol", "Titol2"));
    }

    /**
     * Objecte de la prova: Test del mètode llistaAutorsPerPrefix de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Retorna una llista, ordenada de diferents maneres, amb els autors que comencen per un prefix
     * Operativa: Primer comprovem que retorni null en cas de fer la cerca en un directori buit
     *              A continuació afegim uns quants documents per a fer les proves
     *              Seguidament comprovem que retorni null si el prefix és un String null
     *              Comprovem que si no compleix el prefix cap autor la cerca retorna null
     *              Si el prefix és una cadena buida es retornen tots els autors
     *              Finalment comprovem els diferents mètodes d'ordenació
     */
    @Test
    public void TestLlistaAutorsPerPrefix() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Cas directori buit
        assertNull(CtrlDir.llistaAutorsPerPrefix("Autor", null));

        CtrlDir.afegirDocument("Juli","Titol1","Juli1");
        CtrlDir.afegirDocument("Joan","Titol2","Juli2");
        CtrlDir.afegirDocument("Pol","Titol1","Pol1");
        CtrlDir.afegirDocument("Pau", "Titol2", "Pol2");
        CtrlDir.afegirDocument("Isaac","Titol1","Isaac1");
        CtrlDir.afegirDocument("Isidre", "Titol2", "Isaac2");

        //Null argument
        assertNull(CtrlDir.llistaAutorsPerPrefix(null, null));

        //Cap autor
        assertNull(CtrlDir.llistaAutorsPerPrefix("Mar", null));

        //Tots els autors
        assertEquals(6, CtrlDir.llistaAutorsPerPrefix("", null).size());

        //Ordre ascendent
        List<String> ret = CtrlDir.llistaAutorsPerPrefix("J", CtrlDirectori.SORTING.AUT_ASC);
        assertEquals("Joan", ret.get(0));
        assertEquals("Juli", ret.get(1));

        //Ordre descendent
        ret = CtrlDir.llistaAutorsPerPrefix("P", CtrlDirectori.SORTING.AUT_DESC);
        assertEquals("Pol", ret.get(0));
        assertEquals("Pau", ret.get(1));

    }

    /**
     * Objecte de la prova: Test del mètode llistaTitolsPerAutor de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Retorna una llista, ordenada de diferents maneres, amb els titols que ha escrit un autor
     * Operativa: Primer comprovem que retorni null en cas de fer la cerca en un directori buit
     *              A continuació afegim uns quants documents per a fer les proves
     *              Seguidament comprovem que retorni null si el titol és un String null
     *              Comprovem que si no existeix l'autor la cerca retorna null
     *              Finalment comprovem els diferents mètodes d'ordenació
     */
    @Test
    public void TestLlistaTitolsPerAutor() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Cas directori buit
        assertNull(CtrlDir.llistaTitolsPerAutor("Autor", CtrlDirectori.SORTING.TIT_ASC));

        CtrlDir.afegirDocument("Juli","Titol1","Juli1");
        CtrlDir.afegirDocument("Juli","Titol2","Juli2");
        CtrlDir.afegirDocument("Juli","Titol3","Juli3");
        CtrlDir.afegirDocument("Pol","Titol1","Pol1");
        CtrlDir.afegirDocument("Pol", "Titol2", "Pol2");
        CtrlDir.afegirDocument("Isaac","Titol1","Isaac1");
        CtrlDir.afegirDocument("Isaac", "Titol2", "Isaac2");

        //Null arguments
        assertNull(CtrlDir.llistaTitolsPerAutor(null, null));
        assertNull(CtrlDir.llistaTitolsPerAutor("", null));

        //Cap autor
        assertNull(CtrlDir.llistaTitolsPerAutor("Mar", null));

        //Ordre ascendent
        List<String> ret = CtrlDir.llistaTitolsPerAutor("Juli", CtrlDirectori.SORTING.TIT_ASC);
        assertEquals("Titol1", ret.get(0));
        assertEquals("Titol2", ret.get(1));
        assertEquals("Titol3", ret.get(2));

        //Ordre descendent
        ret = CtrlDir.llistaTitolsPerAutor("Pol", CtrlDirectori.SORTING.TIT_DESC);
        assertEquals("Titol2", ret.get(0));
        assertEquals("Titol1", ret.get(1));

    }
}
