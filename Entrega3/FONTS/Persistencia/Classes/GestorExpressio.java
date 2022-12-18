package Persistencia.Classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GestorExpressio {

    private final String path = System.getProperty("user.dir") + "/" + "expressio";

    public Boolean guardarExpressio (int idExp, String expressio) {
        File experssions = new File (path);
        if (!experssions.exists()) {
            experssions.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(path + "/" + String.valueOf(idExp) + ".txt");
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
        File expressio = new File (path + "/" + String.valueOf(idExp) + ".txt");
        if (expressio.delete()) {
            return true;
        }
        else return false;
    }
}