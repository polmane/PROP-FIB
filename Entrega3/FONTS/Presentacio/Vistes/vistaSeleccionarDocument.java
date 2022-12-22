package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.Character.isWhitespace;

/**
 * Representa la vista on es selecciona un document
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaSeleccionarDocument extends JFrame {
    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Llista que conté el que es mostrarà a l'àrea de text
     */
    private DefaultListModel<String> model = new DefaultListModel<>();
    /**
     * Àrea de text on es mostren els documents a seleccionar
     */
    private JList<String> Documents;
    /**
     * Botó per seleccionar un document
     */
    private JButton Seleccionar;
    /**
     * Botó per cancel·lar
     */
    private JButton Cancellar;
    /**
     * Panell que conté el botó per seleccionar i per cancel·lar
     */
    private JPanel panelOpcions;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelDocuments;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaSeleccionarDocument
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaSeleccionarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        Documents.setModel(model);

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Selecció d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) {
            Seleccionar.setEnabled(false);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Seleccionar documents", "No hi ha documents per seleccionar", strBotones, 1);
            System.out.println("No hi ha docs per seleccionar: " + isel + " " + strBotones[isel]);

        } else {
            for (int i = 0; i < resultat.size(); i += 3) {
                model.addElement(resultat.get(i) + " | " + resultat.get(i + 1) + " | " + resultat.get(i + 2));
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Tancant vistaSeleccionarDocument");
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });

        Seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonSeleccionar(e);
            }
        });
        Cancellar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonCancellar(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Seleccionar i crida a la funció seleccionarDocument del controlador de Presentació
     * @param event acció que es captura al clicar el botó Seleccionar
     */
    public void actionPerformed_buttonSeleccionar(ActionEvent event) {
        if (Documents.getSelectedValue() != null) {
            String resultat = String.valueOf(Documents.getSelectedValue());
            int i = 0;
            while(!isWhitespace(resultat.charAt(i))) ++i;
            int id = Integer.parseInt(resultat.substring(0, i));

            int codi = _ctrlPresentacio.seleccionarDocument(id);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            if (codi == -50) {
                int isel = vistaDialogo.setDialogo(frame, "Seleccionar document", "Hi ha hagut un problema amb disc al seleccionar el document", strBotones, 0);
                System.out.println("Error seleccio document persistencia: " + isel + " " + strBotones[isel]);

            } else if (codi == -20) {
                int isel = vistaDialogo.setDialogo(frame, "Seleccionar document", "No existeix aquest document", strBotones, 0);
                System.out.println("Error seleccio document no existent: " + isel + " " + strBotones[isel]);

            } else {
                System.out.println("Doc seleccionat: " + resultat);

                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        } else {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Document no seleccionat", "Has de seleccionar un document perquè tingui efecte", strBotones, 1);
            System.out.println("Error document no seleccionat: " + isel + " " + strBotones[isel]);
        }
    }
    /**
     * Funció que captura l'acció del botó Cancel·lar i crida a la funció activarPagPrincipal del controlador de Presentació
     * @param event acció que es captura al clicar el botó Cancel·lar
     */
    public void actionPerformed_buttonCancellar(ActionEvent event) {
        System.out.println("Cancelar vistaSeleccionarDocument");
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }
}
