package Persistencia.Classes;

import Domini.Classes.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class GestorExpressions {

    private final String BD_PATH = System.getProperty("user.dir") + "/" + "expressions";

    public ArrayList<Pair<Integer, String>> carregarExpressions() {
        ArrayList<Pair<Integer, String>> expressions = new ArrayList<>();
        File dir = new File(BD_PATH);
        try {
            for (File FileExpressio : Objects.requireNonNull(dir.listFiles())) {
                Scanner scanner = new Scanner(FileExpressio);
                int id = scanner.nextBigInteger().intValue();
                String exp = scanner.nextLine();
                expressions.add(new Pair<>(id, exp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Pair p = new Pair(-1,null);
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();
            result.add(p);
            return result;
        }
        return expressions;
    }

    public Boolean guardarExpressio (int idExp, String expressio) {
        File experssions = new File (BD_PATH);
        if (!experssions.exists()) {
            experssions.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(BD_PATH + "/" + String.valueOf(idExp) + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(expressio);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminarExpressio (int idExp) {
        File expressio = new File (BD_PATH + "/" + String.valueOf(idExp) + ".txt");
        if (expressio.delete()) {
            return true;
        }
        else return false;
    }
}
