package Persistencia.Test;

import Persistencia.Classes.GestorDocument;
import Persistencia.Classes.GestorExpressions;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestGestorExpressions {

    private final String BD_PATH = System.getProperty("user.dir") + "/" + "expressions";

    @Test
    public void testGuardarExpressio() {
        GestorExpressions gExp = new GestorExpressions();
        int id = 1000;
        String expressio = "p1 & p2";
        gExp.guardarExpressio(id, expressio);
        File f = new File(BD_PATH + "/" + id + ".txt");
        Scanner result;
        try {
            result = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            Assert.fail("File Not Found");
            return;
        }
        Assert.assertEquals(String.valueOf(id), result.nextLine());
        Assert.assertEquals(expressio, result.nextLine());
        result.close();
        f.delete();
    }
}
