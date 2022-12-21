package Domini.Drivers;



import Domini.Classes.Document;
import Domini.Classes.Expressio;
import Domini.Controladors.CtrlExpressio;

import java.util.HashMap;
import java.util.Scanner;

public class DriverCtrlExpressio {
    private static CtrlExpressio _ctrlExpressio;

    public void testConstructora() {
        _ctrlExpressio = new CtrlExpressio();
        System.out.println("Controlador de directori creat!");
    }

    public void testAfegirExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'expressió booleana:");
        String exp = input.nextLine();
        int codi = _ctrlExpressio.afegirExpressio(exp);
        if (codi == 10)
        {
            System.out.println("Expressio: ' " + exp + " ' afegida correctament");
        }
        else if (codi == 20){
            System.out.println("ERROR: Ja existeix un expressió idèntica, NO s'ha afegit cap nova expressió");
        }
        else if (codi == 30) {
            System.out.println("ERROR: Expressió no vàlida, escriu un string!");
        }
    }

    public void testSeleccionarExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlExpressio.getExpressions().size() == 0) {
            System.out.println("No hi ha expressions disponibles");
        }
        else {
            if (_ctrlExpressio.getExpressioSeleccionada() == null) System.out.println("Expressió seleccionada actual: null");
            else System.out.println("(Expressió seleccionada:" + _ctrlExpressio.getExpressioSeleccionada().getIdExp() +")");
            mostrarExpressions();
            System.out.println("Escriu l'identificador de l'expressio a seleccionar:");
            Scanner input = new Scanner(System.in);
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Identificador no vàlid. Intenta-ho de nou");
            }
            int idExp = input.nextInt();
            input.nextLine();

            int codi = _ctrlExpressio.seleccionarExpressio(idExp);
            if (codi == 10) System.out.println("Expressió "+idExp+" seleccionada correctament");
            else {
                System.out.println("ERROR: No existeix cap expressió amb aquest identificador");
            }
        }
    }

    public void testModificarExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu la nova expressió:");
        String exp = input.nextLine();
        int codi = _ctrlExpressio.modificarExpressio(exp);
        if (codi == 10) {System.out.println("Expressio modificada correctament");}
        else if (codi == 20){
            System.out.println("ERROR: Ja existeix un expressió idèntica, NO s'ha modificat l'expressió");
        }
        else if (codi == 30){
            System.out.println("ERROR: Expressio no vàlida, escrigui un string significatiu!");
        }
        else if (codi == 31) {
            System.out.println("ERROR: No tens cap expressio seleccionada. Selecciona-la i torna-ho a intentar (funcinalitat (3))");
        }
    }

    public void testEliminarExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        if (_ctrlExpressio.getExpressions().size() == 0) {
            System.out.println("No hi ha expressions disponibles");
        }
        else {
            if (_ctrlExpressio.getExpressioSeleccionada() == null)
                System.out.println("Expressió seleccionada actual: null");
            else
                System.out.println("(Expressió seleccionada:" + _ctrlExpressio.getExpressioSeleccionada().getIdExp() + ")");
            mostrarExpressions();
            System.out.println("(Expressió seleccionada:" + _ctrlExpressio.getExpressioSeleccionada().getIdExp() + ")");
            Scanner input = new Scanner(System.in);
            System.out.println("Escriu l'identificador de l'expressió a eliminar (prem 'a' per abortar):");
            if (!input.hasNextInt()) {
                input.next();
                System.out.println("Abortant eliminar exxpressio");
                return;
            }
            int id = input.nextInt();
            input.nextLine();
            int codi = _ctrlExpressio.eliminarExpressio(id);
            if (codi == 11)
                System.out.println("L'expressió eliminada corresponia amb l'expressió seleccionada. Recorda seleccionar-ne un altra (funcionalitat 3)!");
            else if (codi == 10) System.out.println("Expressió eliminada correctament");
            else if (codi == 20){
                System.out.println("No existeix l'expressió amb aquest identificador");
            }
        }
    }

    public void testSelectPerExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        Scanner input = new Scanner(System.in);
        if (_ctrlExpressio.getExpressioSeleccionada() == null) System.out.println("No tens cap expressio seleccionada. Selecciona-la i torna-ho a intentar (funcionalitat 3)");
        else {
            System.out.println("Simulem un document per veure si compleix l'expressió booleana seleccionada, insereix el contingut d'aquest:");
            String contingut = input.nextLine();
            Document doc = new Document(0,"","", contingut);
            doc.setOcurrencies(obteContingut(contingut));

            if (_ctrlExpressio.selectPerExpressio(_ctrlExpressio.getExpressioSeleccionada().getIdExp(), doc)) {
                System.out.println("El document compleix l'expressió");
            }
            else {
                System.out.println("EL document no compleix l'expressió");
            }
        }
    }
    public static void main (String [] args) {
        System.out.println("--------------------------------------");
        System.out.println("Driver del Controlador d'Expressió");
        System.out.println("--------------------------------------\n");
        DriverCtrlExpressio DExp = new DriverCtrlExpressio();
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
                    DExp.testConstructora();
                    break;
                case 2:
                    System.out.println("---Afegir expressió---");
                    DExp.testAfegirExpressio();
                    break;
                case 3:
                    System.out.println("---Seleccionar expressió---");
                    DExp.testSeleccionarExpressio();
                    break;
                case 4:
                    System.out.println("---Modificar expressió seleccionada---");
                    DExp.testModificarExpressio();
                    break;
                case 5:
                    System.out.println("---Eliminar expressió---");
                    DExp.testEliminarExpressio();
                    break;
                case 6:
                    System.out.println("---Document satisfà expressió---");
                    DExp.testSelectPerExpressio();
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
        System.out.println("2: Afegir expressió");
        System.out.println("3: Seleccionar expressió");
        System.out.println("4: Modificar expressió");
        System.out.println("5: Eliminar expressió");
        System.out.println("6: Document satisfà expressió\n");
    }

    private static void mostrarExpressions() {
        System.out.println("Expressions actuals: (  Id  |  Expressió booleana  )");
        for (Expressio exp : _ctrlExpressio.getExpressions().values()) {
            System.out.println(exp.getIdExp() + "  |  " + exp.getExpressio());
        }
    }
    private HashMap<String, Integer> obteContingut(String contingut) {
        String text = contingut;
        HashMap<String,Integer> paraules = new HashMap<>();
        if (text != null && !text.isEmpty()) {
            int i = 0;
            while (i < text.length()) {
                StringBuilder paraula = new StringBuilder();
                while (i < text.length() && esUnCharCorrecte(text.charAt(i))) {
                    paraula.append(text.charAt(i));
                    ++i;
                }
                ++i;
                if (paraula.length() > 0) {
                    paraula = new StringBuilder(paraula.toString().toLowerCase());
                    if (paraules.containsKey(paraula.toString())) paraules.put(paraula.toString(),paraules.get(paraula.toString())+1);
                    else paraules.put(paraula.toString(), 1);
                }
            }
        }
        return paraules;
    }

    private boolean esUnCharCorrecte(char c) {
        String simbols = " .,'-;:_´`+¨^*{[]}!$%&/()=~|@#€¬";
        for (int i = 0; i < simbols.length(); ++i) {
            if (simbols.charAt(i) == c) return false;
        }
        return true;
    }
}
