package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la vista de la pàgina on es fa la cerca dels títols d'un autor o dels autors que comencen per un prefix
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaCerques extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Àrea de text per escriure el necessari per fer la cerca
     */
    private JTextField Info;
    /**
     * Desplegable per seleccionar la cerca a realitzar
     */
    private JComboBox Cerques;
    /**
     * Botó per realitzar la cerca
     */
    private JButton Buscar;
    /**
     * Botó per obrir la vistaPagPrincipal
     */
    private JButton Enrere;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelInfo;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Panell que conté els botons de buscar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Desplegable per seleccionar el mètode d'ordenació del resultat
     */
    private JComboBox Sorting;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelSorting;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Panell "scroll" per al resultat
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaCerques
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaCerques(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Cerca dels titols d'un autor o dels autors que comencen per un prefix");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaCrearDocument");
                frame.dispose();
                dispose();
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
            }
        });

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });
        Cerques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_ComboBoxCerques(e);
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
     * Funció que captura l'acció del botó Buscar i crida a la funció llistaTitolsPerAutor del controlador de Presentació
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");
        List<String> res = new ArrayList<String>();
        //DefaultListModel model = (DefaultListModel) Resultat.getModel();
        if (Cerques.getSelectedItem() == "Llista de titols d'un autor") {
            res = _ctrlPresentacio.llistaTitolsPerAutor(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
            if (res == null) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Titols per autor", "No hem trobat titols amb aquest autor", strBotones, 1);
                System.out.println("Titols autor, null: " + isel + " " + strBotones[isel]);
            }
            else {
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i));
                    Resultat.append("\n");
                }
            }
        } else if (Cerques.getSelectedItem() == "Llista d'autors que comencen per un prefix") {
            res = _ctrlPresentacio.llistaAutorsPerPrefix(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
            if (res == null) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Autors donat un prefix", "No hi ha autors amb aquest prefix", strBotones, 1);
                System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
            } else {
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i));
                    Resultat.append("\n");
                }
            }
        }
    }

    /**
     * Funció que captura l'acció del desplegable Cerques i actualitza el desplegable Sorting
     * @param event acció que es captura al seleccionar una opció del desplegable Cerques
     */
    public void actionPerformed_ComboBoxCerques(ActionEvent event) {
        if (String.valueOf(Cerques.getSelectedItem()) == "Llista de titols d'un autor") {
            Sorting.removeAllItems();
            Sorting.addItem("TIT_DESC");
            Sorting.addItem("TIT_ASC");
        }
        else if (String.valueOf(Cerques.getSelectedItem()) == "Llista d'autors que comencen per un prefix") {
            Sorting.removeAllItems();
            Sorting.addItem("AUT_DESC");
            Sorting.addItem("AUT_ASC");
        }
    }

}
