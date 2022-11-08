package FONTS.Drivers;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

import java.sql.SQLOutput;
import java.util.*;

import FONTS.Controladors.CtrlDirectori;

public class DriverCtrlDirectori {
    private CtrlDirectori _ctrlDirectori;

    public void testConstructora() {
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        _ctrlDirectori = new CtrlDirectori();
        _ctrlDirectori.crearDirectori(id);
    }
    public void testAfegirDocument() {
        Scanner input = new Scanner(System.in);
        String autor = input.nextLine();
        String titol = input.nextLine();
        String contingut = input.nextLine();
        try {
            _ctrlDirectori.afegirDocument(autor, titol, contingut);
            System.out.println("Document afegit correctament");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void testModificarAutor() {
        Scanner input = new Scanner(System.in);
        String autor = input.nextLine();
        _ctrlDirectori.modificarAutor(autor);
        System.out.println("Autor modificat correctament");
    }
    public void testModificarTitol() {
        Scanner input = new Scanner(System.in);
        String titol = input.nextLine();
        _ctrlDirectori.modificarTitol(titol);
        System.out.println("Autor modificat correctament");
    }
    public static void main (String [] args) {
        System.out.println("Driver del Controlador de Directori");
        DriverCtrlDirectori DDir = new DriverCtrlDirectori();
        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("Escull (nombre) entre les seguents funcionalitats:");
            option = input.nextInt();
            mostrarFuncionalitats();
            switch (option) {
                case 0:
                    System.out.println("Tancar Driver");
                    break;
                case 1:
                default:
                    System.out.println("Funcionalitat no existent");
                    break;
            }
        }
    }

    private static void mostrarFuncionalitats() {
        System.out.println("");
    }
}
