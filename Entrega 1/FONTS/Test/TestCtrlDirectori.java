package FONTS.Test;


import FONTS.Classes.Document;
import FONTS.Controladors.CtrlDirectori;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestCtrlDirectori {

    /**
     * Objecte de la prova: Test del mètode crearDirectori de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es crea un directori al controlador de directori
     * Operativa: es comprova que crear un directori funcioni correctament
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
     * Objecte de la prova: Test del mètode guardarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es guarda un document al directori
     * Operativa: es comprova que afegir un document al directori funcioni correctament
     */
    @Test
    public void TestAfegirDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Funcionament correcte
        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        String autor = "juli";
        String titol = "prova afegir document";
        String contingut = "document per a provar afegirDocument()";
        int res = CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(10, res);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());

        //ERROR: ja existeix document amb el mateix autor i titol
        res = CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(20, res);
    }

    /**
     * Objecte de la prova: Test del mètode eliminarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina un document del directori
     * Operativa: es comprova que eliminar un document del directori funcioni correctament
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
     * Objecte de la prova: Test dels mètodes afegirDocument i eliminarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Reciclatge d'identificadors de documents i del recorregut correcte de tots els documents del directori
     * Operativa: Es comprova que es reciclin els identificadors dels documents eliminats i que el recorregut dels documents se salti els identificadors eliminats.
     */
    @Test
    public void TestAfegirIEliminarDocument(){
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Veure que es crea un document amb una id reciclada
        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        CtrlDir.afegirDocument("juli", "prova id eliminada", "document per a eliminar");
        CtrlDir.eliminarDocument(idDoc);
        String autor = "juli";
        String titol = "prova id reciclada";
        String contingut = "aquest document hauria de reciclar l'identificador";
        CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());

        //Veure que no peti quan no tenim un document amb la id (veure que la condició contains dins el for es compleix)
        idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        assertEquals(1, idDoc); //Si no alguna cosa ha anat malament abans
        CtrlDir.afegirDocument("juli", "prova forat", "document per a fer un forat");
        CtrlDir.afegirDocument("juli", "prova segon forat", "document per a fer un segon forat");
        CtrlDir.afegirDocument("juli", "prova bulto", "document per a fer bulto");
        CtrlDir.eliminarDocument(idDoc); //Fem el forat
        CtrlDir.eliminarDocument(idDoc+1);
        CtrlDir.afegirDocument("juli", "prova salt forat", "document per a comprovar que ens saltem el forat");
    }

    /**
     * Objecte de la prova: Test del mètode seleccionarDocument de la classe CtrlDirectori.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional.
     * Valors estudiats: Funcionament correcte de la funció seleccionarDocument.
     * Operativa: Selecciona correctament el document quan l'identificador és vàlid, altrament retorna un codi d'error.
     */
    @Test
    public void TestSeleccionarDocument() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //id fora de rang directori buit
        assertEquals(20, CtrlDir.seleccionarDocument(3));

        CtrlDir.afegirDocument("Juli","Prova","");
        CtrlDir.afegirDocument("Juli","Prova2","");
        CtrlDir.afegirDocument("Juli","Prova3","");
        assertEquals(2, CtrlDir.getDocumentActiu().getIdDoc());

        //Funcionament correcte
        assertEquals(10, CtrlDir.seleccionarDocument(0));
        assertEquals(0, CtrlDir.getDocumentActiu().getIdDoc());

        //id fora de rang directori ple
        assertEquals(20, CtrlDir.seleccionarDocument(5));
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Gestió correcta dels errors i funcionament correcte de modificarAutor.
     * Operativa: Es comprova que saltin errors quan toca amb el codi corresponent i el funcionament correcte de la funció.
     */
    @Test
    public void TestModificarAutor() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("Juli","Prova","");
        CtrlDir.afegirDocument("Pol","Prova","");
        CtrlDir.afegirDocument("Isaac","Prova","");

        assertEquals(30, CtrlDir.modificarAutor(null));
        assertEquals(30, CtrlDir.modificarAutor(""));

        //Document actiu (Isaac, Prova, ,)
        assertEquals(20, CtrlDir.modificarAutor("Pol"));

        assertEquals(10, CtrlDir.modificarAutor("Nou Autor"));
        assertEquals("Nou Autor", CtrlDir.getDocumentActiu().getAutor());
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Gestió correcta dels errors i funcionament correcte de modificarAutor.
     * Operativa: Es comprova que saltin errors quan toca amb el codi corresponent i el funcionament correcte de la funció.
     */
    @Test
    public void TestModificarTitol() {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("Juli","Prova","");
        CtrlDir.afegirDocument("Pol","Prova","");
        CtrlDir.afegirDocument("Isaac","Prova","");

        assertEquals(30, CtrlDir.modificarAutor(null));
        assertEquals(30, CtrlDir.modificarAutor(""));

        //Document actiu (Isaac, Prova, ,)
        assertEquals(20, CtrlDir.modificarAutor("Pol"));

        assertEquals(10, CtrlDir.modificarAutor("Nou Autor"));
        assertEquals("Nou Autor", CtrlDir.getDocumentActiu().getAutor());
    }

    @Test
    public void TestCompararDocuments() { //TODO: fer ben fet
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("Pol","Prova","A A A A A");
        CtrlDir.afegirDocument("Manel","Prova","el barri gotic de girona");
        CtrlDir.afegirDocument("Isaac","Prova","fem un projecte de programació");
        CtrlDir.afegirDocument("Juli","Prova","la nit es a molt llarga");
        CtrlDir.afegirDocument("Pau","Prova","de de de de de de");
        CtrlDir.afegirDocument("Joan","Prova","el a a programa em peta i no se per on");
        CtrlDir.afegirDocument("Jordi","Prova","dema faig un viatge barcelona");
        CtrlDir.afegirDocument("Pep","Prova",    "la a a a meva casa es d'estil gotic");
        CtrlDir.afegirDocument("Carles","Prova","A A A A A");

    }

    /**
     * Objecte de la prova: Test del mètode cercaPerAutoriTitol de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Funcionament correcte de la funció cercaPerAutoriTitol
     * Operativa: Es comproven les diferents causes d'error de la funció i el seu funcionament correcte.
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

        //Titol erroni
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", "TitolErroni"));

        //Autor erroni
        assertNull(CtrlDir.cercaPerAutoriTitol("AutorErroni", "Titol1"));

        //Nulls o string buida
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", null));
        assertNull(CtrlDir.cercaPerAutoriTitol("Juli", ""));
        assertNull(CtrlDir.cercaPerAutoriTitol(null, "Titol1"));
        assertNull(CtrlDir.cercaPerAutoriTitol("", "Titol1"));

        //Funcionament correcte
        assertEquals("Pol2", CtrlDir.cercaPerAutoriTitol("Pol", "Titol2"));
    }

    /**
     * Objecte de la prova: Test del mètode llistaAutorsPerPrefix de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Funcionament correcte de la funció llistaAutorsPerPrefix
     * Operativa: Es comproven les diferents causes d'error de la funció i el seu funcionament correcte.
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
     * Valors estudiats: Funcionament correcte de la funció llistaTitolsPerAutor
     * Operativa: Es comproven les diferents causes d'error de la funció i el seu funcionament correcte.
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

        //Cap autor i nulls
        assertNull(CtrlDir.llistaTitolsPerAutor("Mar", null));
        assertNull(CtrlDir.llistaTitolsPerAutor(null, null));
        assertNull(CtrlDir.llistaTitolsPerAutor("", null));

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
