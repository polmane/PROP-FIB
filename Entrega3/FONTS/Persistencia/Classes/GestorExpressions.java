package Persistencia.Classes;

import Domini.Classes.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class GestorExpressions {

    private final String BD_PATH = System.getProperty("user.dir") + "/" + "expressions";

    public ArrayList<Pair<Integer, String>> carregarExpressions() {
        ArrayList<Pair<Integer, String>> expressions = new ArrayList<>();
        Scanner scanner = null;
        try {
            File dir = new File(BD_PATH);
            for (File FileExpressio : dir.listFiles()) {
                scanner = new Scanner(FileExpressio);
                Integer id = Integer.parseInt(scanner.nextLine());
                String exp = scanner.nextLine();
                expressions.add(new Pair<>(id, exp));
                scanner.close();
            }
        } catch (NullPointerException | NumberFormatException | IOException e) {
            if (scanner != null)
                scanner.close();
            return null;
        }
        return expressions;
    }

    public Boolean guardarExpressio (int idExp, String expressio) {
        File experssions = new File(BD_PATH);
        if (!experssions.exists()) {
            experssions.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(BD_PATH + "/" + String.valueOf(idExp) + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(idExp + "\n");
            bw.write(expressio);
            bw.close();
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public Boolean eliminarExpressio (int idExp) {
        File expressio = new File(BD_PATH + "/" + String.valueOf(idExp) + ".txt");
        return expressio.delete();
    }
}
