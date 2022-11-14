package FONTS.Drivers;

import FONTS.Classes.Document;
import FONTS.Classes.Expressio;
import FONTS.Controladors.CtrlExpressio;

import java.util.Scanner;

public class DriverCtlExpressio {
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
        if (codi == 10) {System.out.println("Expressio afegida correctament");}
        else {
            System.out.println("ERROR: Ja existeix un expressió idèntica, NO s'ha afegit cap nova expressió");
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
            mostrarExpressions();
            System.out.println("(Expressió seleccionada:" + _ctrlExpressio.getExpressioSeleccionada().getIdExp() +")");
            Scanner input = new Scanner(System.in);
            int idExp = input.nextInt();
            int codi = _ctrlExpressio.seleccionarExpressio(idExp);
            if (codi == 10) System.out.println("Expressió seleccionada correctament");
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
        System.out.println("ERROR: Ja existeix un expressió idèntica, NO s'ha modificat l'expressió");
    }

    public void testEliminarExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        mostrarExpressions();
        System.out.println("(Expressió seleccionada:" + _ctrlExpressio.getExpressioSeleccionada().getIdExp() +")");
        Scanner input = new Scanner(System.in);
        System.out.println("Escriu l'identificador de l'expressió a eliminar:");
        int id = input.nextInt();
        int codi = _ctrlExpressio.eliminarExpressio(id);
        if (codi == 11)System.out.println("L'expressió eliminada corresponia amb l'expressió seleccionada. Recorda seleccionar-ne un altra!");
        else if (codi == 10) System.out.println("Expressió eliminada correctament");
        else {
            System.out.println("No existeix l'expressió amb aquest identificador");
        }
    }

    public void testSelectPerExpressio() {
        if (_ctrlExpressio == null) {
            System.out.println("Primer has de crear el controlador! Fes-ho amb la funcionalitat Constructora (1)");
            return;
        }
        System.out.println("Comparar doc");
    }
    public static void main (String [] args) {
        System.out.println("--------------------------------------");
        System.out.println("Driver del Controlador d'Expressió");
        System.out.println("--------------------------------------\n");
        DriverCtlExpressio DExp = new DriverCtlExpressio();
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
}
