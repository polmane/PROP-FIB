package Presentacio.Vistes;

import Domini.Classes.Pair;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Representa la vista de la pàgina on es fa la cerca dels documents rellevants segons una query e paraules
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaDocumentsRellevants extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Botó per seleccionar el mètode BOOL de comparació
     */
    private JRadioButton BOOLRadioButton;
    /**
     * Botó per seleccionar el mètode TF_IDF de comparació
     */
    private JRadioButton TFIDFRadioButton;
    /**
     * Desplegable per seleccionar el mètode d'ordenació del resultat
     */
    private JComboBox Sorting;
    /**
     * Botó per realitzar la cerca
     */
    private JButton Buscar;
    /**
     * Botó que obra la pàgina principal
     */
    private JButton Enrere;
    /**
     * Panell que conté els boton de buscar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelMetode;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Àrea de text per introduïr el nombre de documents semblants a obtenir de la cerca
     */
    private JSpinner k;
    /**
     * Àrea de text per introduïr les paraules per les quals fer la cerca
     */
    private JTextArea Paraules;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPanePar;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPaneRes;
    /**
     * Etiqueta per indicar el nombre de documents semblants que volem
     */
    private JLabel labelK;
    /**
     * Etiqueta per indicar el camp de Paraules (Query)
     */
    private JLabel labelQuery;
    /**
     * Etiqueta per indicar l'ordre, "Sorting"
     */
    private JLabel labelSorting;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaDocumentsRellevants
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaDocumentsRellevants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents més rellevants segons una sèrie de paraules");

        Paraules.setLineWrap(true);
        Paraules.setWrapStyleWord(true);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsRellevants");
                frame.dispose();
                dispose();
            }
        });

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });

        TFIDFRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_radioButtonTF_IDF(e);
            }
        });

        BOOLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_radioButtonBOOL(e);
            }
        });

        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }

    /**
     * Funció que captura l'acció del botó Buscar i crida a la funció compararQuery del controlador de Presentació
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");
        int numdocs = 0;
        try {
            numdocs = Integer.parseInt(String.valueOf(k.getValue()));
        } catch (NumberFormatException excepcio) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "No s'ha introduit un valor correcte de documents", "El nombre de documents a obtenir \n ha de ser un nombre natural major que 0", strBotones, 0);
            System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
        }

        List<Pair<String, String>> res;
        String sort = String.valueOf(Sorting.getSelectedItem());
        String query = (String.valueOf(Paraules.getText()));
        System.out.println(query);

        if (BOOLRadioButton.isSelected()) {
            res = _ctrlPresentacio.compararQuery("BOOL", sort, numdocs, query);

        } else {
            res = _ctrlPresentacio.compararQuery("TF_IDF", sort, numdocs, query);
        }
        if (res == null) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Cerca documents rellevants ", "No hi ha resultats per aquests paràmetres \n (Recorda que el numero de documents a obtenir \n ha de ser un nombre major que 0)", strBotones, 1);
            System.out.println("Resultat null de documents semblants: " + isel + " " + strBotones[isel]);
        } else {
            for (int i = 0; i < res.size(); ++i) {
                Resultat.append("Autor: " + res.get(i).first());
                Resultat.append(" | ");
                Resultat.append("Títol: " + res.get(i).second());
                Resultat.append("\n");
            }
        }
    }

    /**
     * Funció que captura l'acció del botó BOOLRadioButton i deixa de seleccionar el TFIDFRadioButton
     * @param event acció que es captura al clicar el botó BOOLRadioButton
     */
    public void actionPerformed_radioButtonBOOL(ActionEvent event) {
        TFIDFRadioButton.setSelected(false);
        System.out.println("Botó BOOL");
    }

    /**
     * Funció que captura l'acció del botó TFIDFRadioButton i deixa de seleccionar el BOOLRadioButton
     * @param event acció que es captura al clicar el botó TFIDFRadioButton
     */
    public void actionPerformed_radioButtonTF_IDF(ActionEvent event) {
        BOOLRadioButton.setSelected(false);
        System.out.println("Botó TFIDF");
    }

}