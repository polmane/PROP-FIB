package FONTS.Drivers;

import FONTS.Classes.Document;
import FONTS.Controladors.CtrlDirectori;

import java.util.List;
import java.util.Scanner;

public class DriverCtrlDirectori {
    private static CtrlDirectori _ctrlDirectori;

    public void testConstructora() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'identificador (id, tipus enter) del teu directori:");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Identificador no vàlid. Intenta-ho de nou:");
        }
        int id = input.nextInt();
        System.out.println("(Si més endavant torna a executar la funció constructora es perdrà tot el que hi havia en el directori.\n També, és la manera de reiniciar el directori)");
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
        int codi = _ctrlDirectori.afegirDocument(autor, titol, contingut);
        if (codi == 10) {System.out.println("Document afegit i seleccionat correctament. ");}
        else if (codi == 20){
            System.out.println("ERROR: Ja existeix un document amb autor i titol donats, NO s'ha afegit cap nou document");
        }
        else {
            System.out.println("ERROR: Autor i/o titol no vàlids, escrigui un string!");
        }
    }

    public void testSeleccionarDocument() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlDirectori.getDirectoriObert().getDocs().size() == 0) {
            System.out.println("No hi ha documents disponibles");
        }
        else {
            System.out.println("(Document seleccionat actual:" + _ctrlDirectori.getDocumentActiu().getIdDoc() +")");
            mostrarDocuments();
            System.out.println("Escriu l'identificador del document a seleccionar:");
            Scanner input = new Scanner(System.in);
            while (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Identificador no vàlid. Intenta-ho de nou");
            }
            int idDoc = input.nextInt();
            int codi = _ctrlDirectori.seleccionarDocument(idDoc);
            if (codi == 10) System.out.println("Document seleccionat correctament");
            else {
                System.out.println("ERROR: No existeix cap document amb aquest identificador");
            }
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
        int codi = _ctrlDirectori.modificarAutor(autor);
        if (codi == 10) {System.out.println("Autor modificat correctament");}
        else if (codi == 20){
            System.out.println("ERROR: Ja existeix un document amb el titol i el nou nom d'autor");
        }
        else if (codi == 30){
            System.out.println("ERROR: Autor no vàlid, escrigui un string significatiu!");
        }
        else if (codi == 31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (2))");
        }
    }

    public void testModificarTitol() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou titol del document:");
        String titol = input.nextLine();
        int codi = _ctrlDirectori.modificarTitol(titol);
        if (codi == 10) {System.out.println("Titol modificat correctament");}
        else if (codi == 20){
            System.out.println("ERROR: Ja existeix un document amb l'autor i el nou titol");
        }
        else if (codi == 30){
            System.out.println("ERROR: Titol no vàlid, escrigui un string significatiu!");
        }
        else if (codi == 31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (2))");
        }
    }

    public void testModificarContingut() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou contingut del document:");
        String contingut = input.nextLine();
        int codi = _ctrlDirectori.modificarContingut(contingut);
        if (codi == 10) {System.out.println("Contingut modificat correctament");}
        else if (codi == 31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (2))");
        }
    }

    public void testEliminarDocument() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlDirectori.getDirectoriObert().getDocs().size() == 0) {
            System.out.println("No hi ha documents disponibles");
        }
        else {
            System.out.println("(Document seleccionat actual:" + _ctrlDirectori.getDocumentActiu().getIdDoc() + ")");
            mostrarDocuments();
            System.out.println("Escriu l'identificador del document a eliminar:");
            Scanner input = new Scanner(System.in);
            int id = input.nextInt();
            int codi = _ctrlDirectori.eliminarDocument(id);
            if (codi == 11)
                System.out.println("El document eliminat corresponia al document actiu. Recorda seleccionar-ne una altra (funcionalitat 3");
            else if (codi == 10) System.out.println("Document eliminat correctament");
            else {
                System.out.println("No existeix el document amb aquest identificador");
            }
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
        System.out.println("Escriu el criteri d'ordre (AUT_DESC | AUT_ASC) (ordena els noms per ordre alfabètic Descendent i Ascendent, respectivament):");
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(input.nextLine());
        List<String> res = _ctrlDirectori.llistaAutorsPerPrefix(pre, s);
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
        System.out.println("Escriu el criteri d'ordre (TIT_DESC | TIT_ASC) (ordena els titols per ordre alfabètic Descendent i Ascendent, respectivament):");
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(input.nextLine());
        List<String> res = _ctrlDirectori.llistaTitolsPerAutor(autor, s);
        System.out.println("Llista de titols de " + autor + ":");
        System.out.println(res);
    }

    public void testCompararDocuments() {
        if (_ctrlDirectori == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        System.out.println("Comparar doc");
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
            while (!input.hasNextInt()) {
                input.nextLine();
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
                    System.out.println("---Llista de titols d'un autor---");
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
        for (Document doc : _ctrlDirectori.getDirectoriObert().getDocs().values()) {
            System.out.println(doc.getIdDoc() + "  |  " + doc.getAutor() + "  |  " + doc.getTitol());
        }
    }
}
