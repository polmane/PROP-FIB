package Presentacio.Vistes;

import Domini.Classes.Pair;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la vista de la pàgina on es fa la cerca dels documents semblants
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaDocumentsSemblants extends JFrame {

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
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelMetode;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelK;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelDocument;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Panell que conté els boton de buscar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Desplegable que mostra els documents actius amb els que realitzar la cerca
     */
    private JComboBox Documents;
    /**
     * Mòdel del Spinner k
     */
    private SpinnerModel valors_k;
    /**
     * Àrea de text per introduïr el nombre de documents semblants a obtenir de la cerca
     */
    private JSpinner k;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPaneRes;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPaneDocs;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaDocumentsSemblants
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaDocumentsSemblants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents semblants");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) {
            Buscar.setEnabled(false);
            k.setEnabled(false);

        } else if (resultat.size() == 1) {
            Buscar.setEnabled(false);
            k.setEnabled(false);
            Documents.addItem(resultat.get(0) + " | " + resultat.get(1) + " | " + resultat.get(2));

        } else {
            for (int i = 0; i < resultat.size(); i += 3) {
                Documents.addItem(resultat.get(i) + " | " + resultat.get(i + 1) + " | " + resultat.get(i + 2));
            }
            k.setEnabled(true);
            valors_k = new SpinnerNumberModel(0, 0, (resultat.size() / 3) - 1, 1);
            k.setModel(valors_k);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsSemblants");
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
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
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
     * Funció que captura l'acció del botó Buscar i crida a la funció compararDocuments del controlador de Presentació
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
            int isel = vistaDialogo.setDialogo(frame, "No s'ha introduit un valor correcte de documents", "El nombre de documents a obtenir \n ha de ser un nombre natural major que 0", strBotones, 1);
            System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
        }

        List<Pair<String, String>> res;
        String sort = String.valueOf(Sorting.getSelectedItem());
        String infoDoc = (String.valueOf(Documents.getSelectedItem()));
        int idDoc = Integer.parseInt(infoDoc.substring(0, 1));

        if (BOOLRadioButton.isSelected()) {
            res = _ctrlPresentacio.compararDocuments("BOOL", sort, numdocs, idDoc);

        } else {
            res = _ctrlPresentacio.compararDocuments("TF_IDF", sort, numdocs, idDoc);
        }
        if (res == null) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Cerca semblants ", "No hi ha resultats per aquests paràmetres", strBotones, 2);
            System.out.println("Resultat null de documents semblants: " + isel + " " + strBotones[isel]);
        } else {
            for (int i = 0; i < res.size(); ++i) {
                Resultat.append(res.get(i).first());
                Resultat.append(" ");
                Resultat.append(res.get(i).second());
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
