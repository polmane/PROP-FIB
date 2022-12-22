package Persistencia.Classes;

import Domini.Classes.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa un Gestor d'Expressions
 * @author pol.camprubi.prats
 * @author juli.serra.balaguer
 */
public class GestorExpressions {
    /**
     * Representa el path on hi han les expressions
     */
    private final String BD_PATH = System.getProperty("user.dir") + "/" + "expressions";

    /**
     * Funció per carregar les expressions a MP
     * @return retorna un arrayList de pair on la primera variable és la id de l'expressió i la segona és l'expressió
     */
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

    /**
     * Funció per guardar una expressió
     * @param idExp id de l'expressió a guardar
     * @param expressio expressió a guardar
     * @return true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean guardarExpressio (int idExp, String expressio) {
        File experssions = new File(BD_PATH);
        if (!experssions.exists()) {
            if (!experssions.mkdir()) return false;
        }
        try {
            FileWriter fw = new FileWriter(BD_PATH + "/" + idExp + ".txt");
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

    /**
     * Funció per eliminar una expressió del sistema
     * @param idExp id de l'experssió a eliminar
     * @return retorna true en cas de funcionament correcte, false en cas contrari
     */
    public Boolean eliminarExpressio (int idExp) {
        File expressio = new File(BD_PATH + "/" + idExp + ".txt");
        boolean b = expressio.delete();
        if (b) {
            File dirExp = new File(BD_PATH);
            if(dirExp.length() == 0) {
                dirExp.delete();
            }
            return true;
        }
        return false;
    }
}
