package FONTS.Drivers;

import java.util.*;

import FONTS.Classes.Document;
import FONTS.Controladors.CtrlDirectori;

public class DriverCtrlDirectori {
    private static CtrlDirectori _ctrlDirectori;

    public void testConstructora() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'identificador (id, tipus enter) del teu directori:");
        int id = input.nextInt();
        _ctrlDirectori = new CtrlDirectori();
        _ctrlDirectori.crearDirectori(id);
        System.out.println("Controlador de directori creat!");
    }

    public void testAfegirDocument() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nom de l'autor");
        String autor = input.nextLine();
        System.out.println("Escriu el titol del document");
        String titol = input.nextLine();
        System.out.println("Escriu el contingut");
        String contingut = input.nextLine();
        try {
            _ctrlDirectori.afegirDocument(autor, titol, contingut);
            System.out.println("Document afegit correctament");
        } catch (Exception e) {
            System.out.println("ERROR: Ja existeix un document amb autor i titol donats, NO s'ha afegit cap nou document");
        }
    }

    public void testSeleccionarDocument() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        mostrarDocuments();
        Scanner input = new Scanner(System.in);
        int idDoc = input.nextInt();
        if (_ctrlDirectori.getDirectoriObert().getDocs().size() == 0) {
            System.out.println("No hi ha document disponibles");
        }
        else {
            _ctrlDirectori.seleccionarDocument(idDoc);
            System.out.println("Document seleccionat correctament");
        }
    }

    public void testModificarAutor() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou nom de l'autor:");
        String autor = input.nextLine();
        _ctrlDirectori.modificarAutor(autor);
        System.out.println("Autor modificat correctament");
    }

    public void testModificarTitol() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou titol del document:");
        String titol = input.nextLine();
        _ctrlDirectori.modificarTitol(titol);
        System.out.println("Titol modificat correctament");
    }

    public void testModificarContingut() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou contingut del document:");
        String contingut = input.nextLine();
        _ctrlDirectori.modificarContingut(contingut);
        System.out.println("Contingut modificat correctament");
    }

    public void testEliminarDocument() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        mostrarDocuments();
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'identificador del document a eliminar:");
        int id = input.nextInt();
        try {
            _ctrlDirectori.eliminarDocument(id);
            System.out.println("Document eliminat correctament");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testCercaPerAutoriTitol() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nom de l'autor:");
        String autor = input.nextLine();
        System.out.println("Escriu el titol del document :");
        String titol = input.nextLine();
        String contingut = _ctrlDirectori.cercaPerAutoriTitol(autor, titol);
        if (contingut == null) System.out.println("No s'ha trobat cap document amb aquest autor i titol");
        else {
            System.out.println("Contingut del document de " + autor + " amb el titol: " + titol + ":");
            System.out.println(contingut);
        }
    }

    public void testLlistaAutorsPerPrefix() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el prefix de nom d'autor a cercar:");
        String pre = input.nextLine();
        List<String> res = _ctrlDirectori.llistaAutorsPerPrefix(pre);
        System.out.println("Llista d'autors amb prefix: " + pre);
        System.out.println(res);
    }

    public void testLlistaTitolsPerAutor() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nom de l'autor:");
        String autor = input.nextLine();
        List<String> res = _ctrlDirectori.llistaTitolsPerAutor(autor);
        System.out.println("Llista de titols de " + autor + ":");
        System.out.println(res);
    }

    public void testCompararDocuments() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
    }
    public static void main (String [] args) {
        System.out.println("--------------------------------------");
        System.out.println("Driver del Controlador de Directori");
        System.out.println("--------------------------------------\n");
        DriverCtrlDirectori DDir = new DriverCtrlDirectori();
        mostrarFuncionalitats();
        System.out.println("Escull (un numero) d'entre les anteriors funcionalitats:");

        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            if (option != -1) System.out.println("Escull una funcionalitat: (Si vols llistar les funcionalitats escriu: h)");
            if (!input.hasNextInt()) {
                input.next();
                mostrarFuncionalitats();
                System.out.println("Escull una funcionalitat:");
            }
            option = input.nextInt();
            System.out.println();
            switch (option) {
                case 0:
                    System.out.println("---Tancar Driver---");
                    break;
                case 1:
                    System.out.println("---Constructora---");
                    DDir.testConstructora();
                    break;
                case 2:
                    System.out.println("---Afegir document---");
                    DDir.testAfegirDocument();
                    break;
                case 3:
                    System.out.println("---Seleccionar document---");
                    DDir.testSeleccionarDocument();
                    break;
                case 4:
                    System.out.println("---Modificar autor (del document seleccionat)---");
                    DDir.testModificarAutor();
                    break;
                case 5:
                    System.out.println("---Modificar titol (del document seleccionat)---");
                    DDir.testModificarTitol();
                    break;
                case 6:
                    System.out.println("---Modificar contingut (del document seleccionat)---");
                    DDir.testModificarContingut();
                    break;
                case 7:
                    System.out.println("---Eliminar document---");
                    DDir.testEliminarDocument();
                    break;
                case 8:
                    System.out.println("---Contingut de titol i autor---");
                    DDir.testCercaPerAutoriTitol();
                    break;
                case 9:
                    System.out.println("---Autors per prefix---");
                    DDir.testLlistaAutorsPerPrefix();
                    break;
                case 10:
                    System.out.println("---Llista de titols de un autor---");
                    DDir.testLlistaTitolsPerAutor();
                    break;
                case 11:
                    System.out.println("---Comparar documents---");
                    DDir.testCompararDocuments();
                    break;
                default:
                    System.out.println("Funcionalitat no existent");
                    break;
            }
            System.out.println("-----------------FINISHED-----------------\n");
        }
    }

    private static void mostrarFuncionalitats() {
        System.out.println("Funcionalitats:");
        System.out.println("0: Tancar Driver");
        System.out.println("1: Constructora");
        System.out.println("2: Afegir document");
        System.out.println("3: Seleccionar document");
        System.out.println("4: Modificar autor");
        System.out.println("5: Modificar titol");
        System.out.println("6: Modificar contingut");
        System.out.println("7: Eliminar document");
        System.out.println("8: Contingut de titol i autor");
        System.out.println("9: Autors per prefix");
        System.out.println("10: Llista de titols de un autor");
        System.out.println("11: Comparar documents\n");
    }

    private static void mostrarDocuments() {
        System.out.println("Documents actuals: (  Id  |  Autor  |  Titol  )");
        for (int i = 0; i < _ctrlDirectori.getDirectoriObert().getDocs().size(); ++i) {
            Document doc = _ctrlDirectori.getDirectoriObert().getDocs().get(i);
            if (doc != null) System.out.println(i + "  |  " + doc.getAutor() + "  |  " + doc.getTitol());
        }
    }
}
