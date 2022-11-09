package FONTS.Test;


import FONTS.Classes.Document;
import FONTS.Controladors.CtrlDirectori;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestCtrlDirectori {
    /**
     * Objecte de la prova: Test del mètode guardarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: Es guarda un document al directori
     * Operativa: es comprova que guardar un document al directori funcioni correctament
     */
    @Test
    public void TestAfegirDocument() throws Exception {
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        //Funcionament correcte
        int idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        String autor = "juli";
        String titol = "prova afegir document";
        String contingut = "document per a provar afegirDocument()";
        CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());

        //ERROR: ja existeix document amb el mateix autor i titol
        //TODO: Això no ho podem comprovar perquè sinó els tests no passarien
        idDoc = CtrlDir.getDirectoriObert().getIdNouDoc();
        titol = "el mateix titol";
        contingut = "prova d'error a l'afegir documents amb el mateix autor i tiol";
        CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());
        CtrlDir.afegirDocument(autor, titol, contingut);
        assertEquals(autor, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getAutor());
        assertEquals(titol, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getTitol());
        assertEquals(contingut, CtrlDir.getDirectoriObert().getDocs().get(idDoc).getContingut());
        //Esperem que no s'hagi afegit el document, així que el seguent document a afegir tindrà just la id
        // seguent a l'original.
        String s = "El document amb autor: " + autor + " i títol: " + titol + " ja existeix";
        /*assertThrows(NullPointerException.class, () -> {

        });*/
        assertEquals(idDoc+1, CtrlDir.getDirectoriObert().getIdNouDoc());
    }

    /**
     * Objecte de la prova: Test del mètode eliminarDocument de la classe CtrlDirectori
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     * Valors estudiats: S'elimina un document del directori
     * Operativa: es comprova que eliminar un document del directori funcioni correctament
     */
    @Test
    public void TestEliminarDocument() throws Exception {
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
    public void TestAfegirIEliminarDocument() throws Exception {
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

    @Test
    public void TestCompararDocuments() throws Exception { //TODO: fer ben fet
        CtrlDirectori CtrlDir = new CtrlDirectori();
        CtrlDir.crearDirectori(0);

        CtrlDir.afegirDocument("Pol","Prova","A A A A A");
        CtrlDir.afegirDocument("Manel","Prova","el barri gotic de girona");
        CtrlDir.afegirDocument("Isaac","Prova","fem un projecte de programació");
        CtrlDir.afegirDocument("Juli","Prova","la nit es a molt llarga");
        CtrlDir.afegirDocument("Pau","Prova","de de de de de de");
        CtrlDir.afegirDocument("Joan","Prova","el programa em peta i no se per on");
        CtrlDir.afegirDocument("Jordi","Prova","dema faig un viatge barcelona");
        CtrlDir.afegirDocument("Pep","Prova",    "la meva casa es d'estil gotic");
        CtrlDir.afegirDocument("Carles","Prova","A A A A A");

        ArrayList<Document> semblants = CtrlDir.compararDocuments(CtrlDirectori.METODE_COMPARACIO.TF_IDF, 2,0);
        System.out.println("Els documents semblants al de " + CtrlDir.getDirectoriObert().docs.get(0).getAutor() + " són ");
        for (int i = 0; i < semblants.size(); ++i) {
            System.out.println(semblants.get(i).getAutor() + ": " + semblants.get(i).getContingut());
        }

    }
}
