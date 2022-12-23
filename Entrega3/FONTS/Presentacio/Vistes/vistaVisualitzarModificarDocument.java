package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Representa la vista per visualitzar o modificar un document
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaVisualitzarModificarDocument extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Àrea de text que mostra l'autor del document seleccionat
     */
    private JTextField Autor;
    /**
     * Àrea de text que mostra el títol del document seleccionat
     */
    private JTextField Titol;
    /**
     * Panell que conté els botons per modificar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Botó que permet modificar el document seleccionat
     */
    private JButton Modificar;
    /**
     * Botó per tornar a la pàgina principal
     */
    private JButton Enrere;
    /**
     * Botó per guardar el títol modificat
     */
    private JButton GuardarTitol;
    /**
     * Àrea de text que mostra el contingut del document seleccionat
     */
    private JTextArea Contingut;
    /**
     * Etiqueta que representa l'autor del document seleccionat
     */
    private JLabel labelAutor;
    /**
     * Etiqueta que representa el títol del document seleccionat
     */
    private JLabel labelTitol;
    /**
     * Etiqueta que representa el contingut del document seleccionat
     */
    private JLabel labelContingut;
    /**
     * Botó per guardar l'autor modificat
     */
    private JButton GuardarAutor;
    /**
     * Botó per guardar el contingut modificat
     */
    private JButton GuardarContingut;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaVisualitzarModificarDocument
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaVisualitzarModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Visualització i modificació d'un document");

        Contingut.setLineWrap(true);
        Contingut.setWrapStyleWord(true);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        GuardarAutor.setVisible(false);
        GuardarTitol.setVisible(false);
        GuardarContingut.setVisible(false);

        ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
        String s = document.get(0);
        if (s == "-31") {
            Autor.setText("");
            Titol.setText("");
            Contingut.setText("");
        } else {
            Autor.setText(document.get(1));
            Titol.setText(document.get(2));
            Contingut.setText(document.get(3));
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaVisualitzarModificarDocument");
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

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonModificar(e);
            }
        });

        GuardarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarAutor(e);
            }
        });

        GuardarTitol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarTitol(e);
            }
        });

        GuardarContingut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarContingut(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        //Botó Enrere amb mutació a Cancel·lar
        if (Enrere.getText() == "Enrere") {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();

        } else {
            ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
            String s = document.get(0);
            if (s != "-31") {
                Autor.setText(document.get(1));
                Titol.setText(document.get(2));
                Contingut.setText(document.get(3));
            }
            Autor.setEditable(false);
            Titol.setEditable(false);
            Contingut.setEditable(false);

            GuardarTitol.setVisible(false);
            GuardarAutor.setVisible(false);
            GuardarContingut.setVisible(false);

            Modificar.setEnabled(true);
            Enrere.setText("Enrere");
            Modificar.setText("Modificar");
        }
    }

    /**
     * Funció que captura l'acció del botó Modificar i fa editables els tres camps de text
     * @param event acció que es captura al clicar el botó Modificar
     */
    public void actionPerformed_buttonModificar(ActionEvent event) {
        if (Modificar.getText() == "Modificar") {
            Autor.setEditable(true);
            Titol.setEditable(true);
            Contingut.setEditable(true);
            GuardarTitol.setVisible(true);
            GuardarAutor.setVisible(true);
            GuardarContingut.setVisible(true);
            Modificar.setText("Confirmar");
            Enrere.setText("Cancel·lar");
        } else {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarAutor i crida a la funció modificarAutor del controlador de Presentació
     * @param event acció que es captura al clicar el botó GuardarAutor
     */
    public void actionPerformed_buttonGuardarAutor(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarAutor(Autor.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "Error autor", "No s'ha introduït cap autor", strBotones, 0);
            System.out.println("Modif autor, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Error autor", "Ja existeix un document amb aquest autor (i títol)", strBotones, 0);
            System.out.println("Modif autor, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
        } else {
            GuardarAutor.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar autor", "Autor modificat correctament", strBotones, 1);
            System.out.println("Autor modificat: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarTitol i crida a la funció modificarTitol del controlador de Presentació
     * @param event acció que es captura al clicar el botó GuardarTitol
     */
    public void actionPerformed_buttonGuardarTitol(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarTitol(Titol.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "Error titol", "No s'ha introduït cap títol", strBotones, 0);
            System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Error títol", "Ja existeix un document amb aquest títol (i autor)", strBotones, 0);
            System.out.println("Modif titol, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);

        } else {
            GuardarTitol.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar títol", "Títol modificat correctament", strBotones, 1);
            System.out.println("Titol modificat: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarContingut i crida a la funció modificarContingut del controlador de Presentació
     * @param event acció que es captura al clicar el botó GuardarContingut
     */
    public void actionPerformed_buttonGuardarContingut(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarContingut(Contingut.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -50) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "Error al modificar el contingut a disc", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else {
            GuardarContingut.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar contingut", "Contingut modificat correctament", strBotones, 1);
            System.out.println("Contingut modificat: " + isel + " " + strBotones[isel]);
        }
    }

}
