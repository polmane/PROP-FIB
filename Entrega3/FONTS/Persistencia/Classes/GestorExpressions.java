package Persistencia.Classes;

import java.io.*;

public class GestorExpressions {

    private static final String BD_PATH = "Entrega3/FONTS/Persistencia/BD";

    //TODO: TEST
    public void guardarExpressio(int idExp, String string) {
        String nom = "Expressio" + idExp;
        try {
            File dir = new File(BD_PATH + "Expressions");
            File docExp = new File(dir, nom);
            Writer output = new BufferedWriter(new FileWriter(docExp));
            output.write(string + "\n");
            output.flush();
        } catch (Exception e) {
            System.err.println("L'expressio " + idExp + ": \"" + string + "\" no s'ha guardat correctament");
            throw new RuntimeException(e);
        }
    }
}
