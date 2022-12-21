package Persistencia.Test;

import Domini.Classes.Pair;
import Persistencia.Classes.GestorExpressions;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGestorExpressions {

    /**
     * Adreça a la carpeta d'expressions del nostre sistema
     */
    private final String BD_PATH = System.getProperty("user.dir") + "/" + "expressions";

    /**
     * Objecte de la prova: Test del mètode guardarExpressio.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es guarda una expressio en la carpeta d'expressions del nostre sistema
     * Operativa: Es crea el directori per a les expressions si aquest no existeix.
     *            Es creen l'enter i l'string representant els paràmetres de l'expressio.
     *            Es guarda l'expressio al directori.
     *            Es comprova que s'ha guardat correctament l'expressio.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testGuardarExpressio() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id = 1000;
        String expressio = "p1 & p2";

        GestorExpressions gExp = new GestorExpressions();
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
        Assert.assertTrue(f.delete());
    }

    /**
     * Objecte de la prova: Test de deteccions d'error en el mètode importarExpressions.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es detecten possibles errors en el mètode importarExpressions.
     * Operativa: Es crea el directori per a les expressions si aquest no existeix.
     *            Es guarda un fitxer al directori, representant una expressió de forma errònia.
     *            Es comprova que, en intentar importar l'expressio, es detecta l'error.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testImportarExpressionsError() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        String path = BD_PATH + "/ExpressioError.txt";
        try {
            FileWriter f = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(f);
            bw.write("Aquesta expressio es errònia\n");
            bw.close();
            f.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorExpressions gExp = new GestorExpressions();
        Assert.assertNull(gExp.carregarExpressions());

        File f = new File(path);
        Assert.assertTrue(f.delete());
    }

    /**
     * Objecte de la prova: Test del mètode importarExpressions.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: S'importen diferents expressions des de la carpeta d'expressions.
     * Operativa: Es crea el directori per a les expressions si aquest no existeix.
     *            Es creen l'enter i l'string representant els paràmetres de dues expressions.
     *            Es creen dos fitxers que representen aquestes expressions al directori.
     *            Es comprova que, en intentar importar les expressions, es fa correctament.
     *            S'esborren els fitxers que hem creat.
     */
    @Test
    public void testImportarExpressionsCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id0 = 23;
        String expressio0 = "hola & adeu";
        String path0 = BD_PATH + "/" + id0 +".txt";

        int id1 = 32;
        String expressio1 = "adeu & hola";
        String path1 = BD_PATH + "/" + id1 +".txt";

        try {
            FileWriter f0 = new FileWriter(path0);
            BufferedWriter bw0 = new BufferedWriter(f0);
            bw0.write(id0 + "\n");
            bw0.write(expressio0);
            bw0.close();
            f0.close();

            FileWriter f1 = new FileWriter(path1);
            BufferedWriter bw1 = new BufferedWriter(f1);
            bw1.write(id1 + "\n");
            bw1.write(expressio1);
            bw1.close();
            f1.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorExpressions gExp = new GestorExpressions();
        ArrayList<Pair<Integer, String>> result = gExp.carregarExpressions();
        Assert.assertNotNull(result);

        Assert.assertEquals(id0, result.get(0).first().intValue());
        Assert.assertEquals(expressio0, result.get(0).second());

        Assert.assertEquals(id1, result.get(1).first().intValue());
        Assert.assertEquals(expressio1, result.get(1).second());

        File f0 = new File(path0);
        Assert.assertTrue(f0.delete());

        File f1 = new File(path1);
        Assert.assertTrue(f1.delete());
    }

    /**
     * Objecte de la prova: Test de deteccions d'error en el mètode eliminarExpressio.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es detecta un identificador inexistent en intentar eliminar una expressio.
     * Operativa: Es crea el directori per a les expressions si aquest no existeix.
     *            Es comprova que, en intentar eliminar una expressio que no existeix, es detecta l'error.
     *            S'esborra el fitxer creat.
     */
    @Test
    public void testEliminarExpressioError() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        GestorExpressions gExp = new GestorExpressions();
        Boolean result = gExp.eliminarExpressio(1234);
        Assert.assertFalse(result);
    }

    /**
     * Objecte de la prova: Test del mètode eliminarExpressio.
     * Fitxer de dades necessari: Dades introduïdes manualment, no ha calgut un fitxer addicional
     *                            el mateix test crea un nou fitxer i l'esborra.
     * Valors estudiats: Es pot eliminar diverses expressions del directori.
     * Operativa: Es crea el directori per a les expressions si aquest no existeix.
     *            Es creen l'enter i l'string representant els paràmetres de quatre expressions.
     *            Es creen quatre fitxers que representen aquestes expressions al directori.
     *            Es comprova que, en intentar eliminar les expressions, es fa correctament.
     *            S'esborren els fitxers que hem creat.
     */
    @Test
    public void testEliminarExpressioCorrecte() {
        File dir = new File(BD_PATH);
        if (!dir.exists()) Assert.assertTrue(dir.mkdir());

        int id0 = 23;
        String expressio0 = "hola & adeu";
        String path0 = BD_PATH + "/" + id0 +".txt";

        int id1 = 32;
        String expressio1 = "adeu & hola";
        String path1 = BD_PATH + "/" + id1 +".txt";

        int id2 = 22;
        String expressio2 = "hola & hola";
        String path2 = BD_PATH + "/" + id2 +".txt";

        int id3 = 33;
        String expressio3 = "adeu & adeu";
        String path3 = BD_PATH + "/" + id3 +".txt";

        try {
            FileWriter f0 = new FileWriter(path0);
            BufferedWriter bw0 = new BufferedWriter(f0);
            bw0.write(id0 + "\n");
            bw0.write(expressio0);
            bw0.close();
            f0.close();

            FileWriter f1 = new FileWriter(path1);
            BufferedWriter bw1 = new BufferedWriter(f1);
            bw1.write(id1 + "\n");
            bw1.write(expressio1);
            bw1.close();
            f1.close();

            FileWriter f2 = new FileWriter(path2);
            BufferedWriter bw2 = new BufferedWriter(f2);
            bw2.write(id2 + "\n");
            bw2.write(expressio2);
            bw2.close();
            f0.close();

            FileWriter f3 = new FileWriter(path3);
            BufferedWriter bw3 = new BufferedWriter(f3);
            bw3.write(id3 + "\n");
            bw3.write(expressio3);
            bw3.close();
            f3.close();
        }
        catch (IOException e) {
            Assert.fail("Exception");
        }

        GestorExpressions gExp = new GestorExpressions();
        Boolean result = gExp.eliminarExpressio(id0);
        Assert.assertTrue(result);

        result = gExp.eliminarExpressio(id1);
        Assert.assertTrue(result);

        result = gExp.eliminarExpressio(id2);
        Assert.assertTrue(result);

        result = gExp.eliminarExpressio(id3);
        Assert.assertTrue(result);
    }
}
