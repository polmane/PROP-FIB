package Persistencia.Classes;

import Domini.Classes.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorExpressions {

    private static final String BD_PATH = "Entrega3/FONTS/Persistencia/BD";

    //TODO: TEST
    public void guardarExpressio(int idExp, String string) {
        String nom = "Expressio" + idExp;
        try {
            File dir = new File(BD_PATH + "Expressions");
            File docExp = new File(dir, nom);
            Writer output = new BufferedWriter(new FileWriter(docExp));
            output.write(idExp + "\n");
            output.write(string + "\n");
            output.flush();
        } catch (Exception e) {
            System.err.println("L'expressio " + idExp + ": \"" + string + "\" no s'ha guardat correctament");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pair<Integer, String>> carregarExpressions() {
        ArrayList<Pair<Integer, String>> expressions = new ArrayList<>();
        File dir = new File(BD_PATH + "Expressions");
        try {
            for (File FileExpressio : dir.listFiles()) {
                Scanner scanner = new Scanner(FileExpressio);
                int id = scanner.nextBigInteger().intValue();
                String exp = scanner.nextLine();
                expressions.add(new Pair<>(id, exp));
            }
        } catch (Exception e) {
            System.err.println("No s'han pogut carregar les expressions correctament");
            throw new RuntimeException(e);
        }
        return expressions;
    }
}
