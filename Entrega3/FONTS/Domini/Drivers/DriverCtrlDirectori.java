package Domini.Drivers;


import Domini.Classes.Document;
import Domini.Classes.Pair;
import Domini.Controladors.CtrlDirectori;
import Domini.Controladors.CtrlDomini;
import Persistencia.Controladors.CtrlPersistencia;


import java.util.List;
import java.util.Scanner;

public class DriverCtrlDirectori {
    private static CtrlDomini _ctrlDomini;
    private static CtrlDirectori _ctrlDirectori;
    private static CtrlPersistencia _ctrlPersistencia;

    public void testConstructora() {
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'identificador (id, tipus enter) del teu directori:");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Identificador no vàlid. Intenta-ho de nou:");
        }
        int id = input.nextInt();
        input.nextLine();
        System.out.println("(Si més endavant torna a executar la funció constructora es perdrà tot el que hi havia en el directori.\n També, és la manera de reiniciar el directori)\n");
        _ctrlDirectori = new CtrlDirectori();
        _ctrlPersistencia = new CtrlPersistencia();
        _ctrlDomini = new CtrlDomini(_ctrlDirectori,null,_ctrlPersistencia);
        _ctrlDomini.get_ctrlDirectori().crearDirectori(id);
        System.out.println("Controlador de directori creat!");
    }

    public void testAfegirDocument() {
        if (_ctrlDomini == null) {
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
        int codi = _ctrlDomini.afegirDocument(autor, titol, contingut);
        if (codi > -1 | codi == -10) {System.out.println("Document afegit i seleccionat correctament. ");}
        else if (codi == -20){
            System.out.println("ERROR: Ja existeix un document amb autor i titol donats, NO s'ha afegit cap nou document");
        }
        else if (codi == -50) System.out.println("ERROR: El docuement no s'ha afegit correctament a persistencia");
        else {
            System.out.println("ERROR: Autor i/o titol no vàlids, escrigui un string!");
        }
    }

    public void testSeleccionarDocument() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlDomini.get_ctrlDirectori().getDirectoriObert().getDocs().size() == 0) {
            System.out.println("No hi ha documents disponibles");
        }
        else {
            if (_ctrlDomini.get_ctrlDirectori().getDocumentActiu() == null) System.out.println("Document seleccionat actual: null");
            else System.out.println("(Document seleccionat actual:" + _ctrlDomini.get_ctrlDirectori().getDocumentActiu().getIdDoc() +")");
            mostrarDocuments();
            System.out.println("Escriu l'identificador del document a seleccionar:");
            Scanner input = new Scanner(System.in);
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Identificador no vàlid. Intenta-ho de nou");
            }
            int idDoc = input.nextInt();
            input.nextLine();
            int codi = _ctrlDomini.seleccionarDocument(idDoc);
            if (codi > -1 | codi == -10) System.out.println("Document seleccionat correctament");
            else {
                System.out.println("ERROR: No existeix cap document amb aquest identificador");
            }
        }
    }

    public void testModificarAutor() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou nom de l'autor:");
        String autor = input.nextLine();
        int codi = _ctrlDomini.modificarAutor(autor);
        if (codi > -1 | codi == -10) {System.out.println("Autor modificat correctament");}
        else if (codi == -20){
            System.out.println("ERROR: Ja existeix un document amb el titol i el nou nom d'autor");
        }
        else if (codi == -30){
            System.out.println("ERROR: Autor no vàlid, escrigui un string significatiu!");
        }
        else if (codi == -31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (3))");
        }
    }

    public void testModificarTitol() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou titol del document:");
        String titol = input.nextLine();
        int codi = _ctrlDomini.modificarTitol(titol);
        if (codi > -1 | codi == -10) {System.out.println("Titol modificat correctament");}
        else if (codi == -20){
            System.out.println("ERROR: Ja existeix un document amb l'autor i el nou titol");
        }
        else if (codi == -30){
            System.out.println("ERROR: Titol no vàlid, escrigui un string significatiu!");
        }
        else if (codi == -31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (3))");
        }
    }

    public void testModificarContingut() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nou contingut del document:");
        String contingut = input.nextLine();
        int codi = _ctrlDomini.modificarContingut(contingut);
        if (codi > -1 | codi == -10) {System.out.println("Contingut modificat correctament");}
        else if (codi == -31) {
            System.out.println("ERROR: No tens cap document seleccionat. Selecciona'l i torna-ho a intentar (funcinalitat (3))");
        }
    }

    public void testEliminarDocument() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlDomini.get_ctrlDirectori().getDirectoriObert().getDocs().size() == 0) {
            System.out.println("No hi ha documents disponibles");
        }
        else {
            if (_ctrlDomini.get_ctrlDirectori().getDocumentActiu() == null) System.out.println("Document seleccionat actual: null, selecciona el document desitjat amb la opció 3");
            else {System.out.println("(Document seleccionat actual:" + _ctrlDomini.get_ctrlDirectori().getDocumentActiu().getIdDoc() + ")");}
            mostrarDocuments();
            System.out.println("Escriu l'identificador del document a eliminar (prem 'a' per abortar):");
            Scanner input = new Scanner(System.in);
            if (!input.hasNextInt()) {
                input.next();
                System.out.println("Abortant eliminar document");
                return;
            }
            int id = input.nextInt();
            input.nextLine();
            int codi = _ctrlDomini.eliminarDocument(id);
            if (codi == -11)
                System.out.println("El document eliminat corresponia al document actiu. RECORDA seleccionar-ne una altra (funcionalitat (3)");
            else if (codi > -1 | codi == -10) System.out.println("Document eliminat correctament");
            else if  (codi == -20) {
                System.out.println("ERROR: No existeix cap document amb aquest identificador");
            }
        }
    }

    public void testCercaPerAutoriTitol() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nom de l'autor:");
        String autor = input.nextLine();
        System.out.println("Escriu el titol del document :");
        String titol = input.nextLine();
        String contingut = _ctrlDomini.cercaPerAutoriTitol(autor, titol);

        if (contingut == null) System.out.println("No s'ha trobat cap document amb aquest autor i titol");
        else {
            System.out.println("Contingut del document de " + autor + " amb el titol: " + titol + ":");
            System.out.println(contingut);
        }
    }

    public void testLlistaAutorsPerPrefix() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el prefix de nom d'autor a cercar:");
        String pre = input.nextLine();
        System.out.println("Escriu el criteri d'ordre del resultat (AUT_ASC | AUT_DESC) (ordena els noms per ordre alfabètic Ascendent i Descendent, respectivament):");
        String criteri = input.nextLine();
        if (!criteri.equals("AUT_ASC") && !criteri.equals("AUT_DESC")) {
            criteri = "AUT_ASC";
            System.out.println("Criteri mal escrit, usem el criteri per defecte AUT_ASC");
        }
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(criteri);
        List<String> res = _ctrlDomini.llistaAutorsPerPrefix(pre, s);

        System.out.println();
        if (res == null) {
            System.out.println("No s'han trobat autors amb aquest prefix");
        }
        else {
            System.out.println("Llista d'autors amb prefix '" + pre + "' i criteri " + s);
            System.out.println(res);
        }
    }

    public void testLlistaTitolsPerAutor() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el nom de l'autor:");
        String autor = input.nextLine();
        System.out.println("Escriu el criteri d'ordre del resultat (TIT_ASC | TIT_DESC) (ordena els titols per ordre alfabètic Ascendent i Descendent, respectivament):");
        String criteri = input.nextLine();
        if (!criteri.equals("TIT_ASC") && !criteri.equals("TIT_DESC")) {
            criteri = "TIT_ASC";
            System.out.println("Criteri mal escrit, usem el criteri per defecte TIT_ASC");

        }
        System.out.println();
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(criteri);
        List<String> res = _ctrlDomini.llistaTitolsPerAutor(autor, s);
        if (res == null) {
            System.out.println("No s'han trobat titols d'aquest autor");
        }
        else {
            System.out.println("Llista de titols de " + autor + " amb criteri " + s + ":");
            System.out.println(res);
        }
    }

    public void testCompararDocuments() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el metode de comparació de documents (TF_IDF | BOOL):");
        String metode = input.nextLine();
        if (!metode.equals("TF_IDF") && !metode.equals("BOOL")) {
            metode = "TF_IDF";
            System.out.println("Metode mal escrit, usem el metode per defecte TF_IDF");
        }
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metode);

        System.out.println();
        System.out.println("Escriu el criteri d'ordre del resultat (SIM_ASC | SIM_DESC | AUT_ASC | AUT_DESC | TIT_ASC | TIT_DESC)");
        System.out.println("(SIM ordena els resultats per ordre de similaritat ASCendent o DESCdendent, respectivament,");
        System.out.println("AUT ordena els resultats per autor en ordre alfabètic ASCendent o DESCdendent, respectivament,");
        System.out.println("TIT ordena els resultats per titol en ordre alfabètic ASCendent o DESCdendent, respectivament,");

        String criteri = input.nextLine();
        if (!criteri.equals("SIM_ASC") && !criteri.equals("SIM_DESC") &&
            !criteri.equals("AUT_ASC") && !criteri.equals("AUT_DESC") &&
            !criteri.equals("TIT_ASC") && !criteri.equals("TIT_DESC")) {
            criteri = "SIM_DESC";
            System.out.println("Criteri mal escrit, usem el criteri per defecte SIM_DESC");
        }
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(criteri);

        System.out.println();
        System.out.println("Indica la quantitat (enter major que 0) màxima de documents més semblants que vols llistar (si n'hi ha suficients):");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Quantitat no vàlida. Intenta-ho de nou:");
        }
        int k = input.nextInt();
        input.nextLine();

        System.out.println();
        System.out.println("Escull l'identificador del document el qual compararem amb la resta:");
        mostrarDocuments();
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Identificador no vàlid. Intenta-ho de nou:");
        }
        int id = input.nextInt();
        input.nextLine();

        List<Pair<String, String>> res = _ctrlDomini.compararDocuments(m, s, k, id);
        if (res == null) {
            System.out.println("No existeix document amb aquest identificador. Alternativament, pot ser que no hi hagi suficients documents per comparar (¡recorda que NO es compara amb si mateix!)");
        }
        else {
            System.out.println("LLista de documents (Autor i titol) més semblants al document amb identificador " + id + " segons el metode "+m+ " i criteri "+s);
            System.out.println(res);
        }
    }

    public void testCompararQuery() {
        if (_ctrlDomini == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu el metode de comparació de documents amb la query (TF_IDF | BOOL):");
        String metode = input.nextLine();
        if (!metode.equals("TF_IDF") && !metode.equals("BOOL")) {
            metode = "TF_IDF";
            System.out.println("Metode mal escrit, usem el metode per defecte TF_IDF");
        }
        CtrlDirectori.METODE_COMPARACIO m = CtrlDirectori.METODE_COMPARACIO.valueOf(metode);

        System.out.println();
        System.out.println("Escriu el criteri d'ordre del resultat (SIM_ASC | SIM_DESC | AUT_ASC | AUT_DESC | TIT_ASC | TIT_DESC)");
        System.out.println("(SIM ordena els resultats per ordre de similaritat ASCendent o DESCdendent, respectivament,");
        System.out.println("AUT ordena els resultats per autor en ordre alfabètic ASCendent o DESCdendent, respectivament,");
        System.out.println("TIT ordena els resultats per titol en ordre alfabètic ASCendent o DESCdendent, respectivament,");

        String criteri = input.nextLine();
        if (!criteri.equals("SIM_ASC") && !criteri.equals("SIM_DESC") &&
                !criteri.equals("AUT_ASC") && !criteri.equals("AUT_DESC") &&
                !criteri.equals("TIT_ASC") && !criteri.equals("TIT_DESC")) {
            criteri = "SIM_DESC";
            System.out.println("Criteri mal escrit, usem el criteri per defecte SIM_DESC");
        }
        CtrlDirectori.SORTING s = CtrlDirectori.SORTING.valueOf(criteri);

        System.out.println();
        System.out.println("Indica la quantitat (enter major que 0) màxima de documents més semblants que vols llistar (si n'hi ha suficients):");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Quantitat no vàlida. Intenta-ho de nou:");
        }
        int k = input.nextInt();
        input.nextLine();

        System.out.println();
        System.out.println("Escriu les paraules de la query separades per un espai. (El simbols s'eliminaran)");
        String query = input.nextLine();

        List<Pair<String, String>> res = _ctrlDomini.compararQuery(m, s, k, query);
        if (res == null) {
            System.out.println("No hi ha suficients documents per comparar (mínim 1) o, alternativament, la query és buida");
        }
        else {
            System.out.println("LLista de documents (Autor i titol) més semblants a la query segons el metode "+m+ " i criteri "+s);
            System.out.println(res);
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
            while (!input.hasNextInt()) {
                input.next();
                mostrarFuncionalitats();
                System.out.println("Escull una funcionalitat:");
            }
            option = input.nextInt();
            input.nextLine();
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
                    System.out.println("---Documents semblants---");
                    DDir.testCompararDocuments();
                    break;
                case 12:
                    System.out.println("---Documents semblants a una query---");
                    DDir.testCompararQuery();
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
        System.out.println("10: Llista de titols d'un autor");
        System.out.println("11: Documents semblants");
        System.out.println("12: Documents semblants a una query\n");
    }

    private static void mostrarDocuments() {
        System.out.println("Documents actuals: (  Id  |  Autor  |  Titol  )");
        for (Document doc : _ctrlDomini.get_ctrlDirectori().getDirectoriObert().getDocs().values()) {
            System.out.println(doc.getIdDoc() + "  |  " + doc.getAutor() + "  |  " + doc.getTitol());
        }
    }
}
