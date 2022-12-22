package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Representa la vista de per modificar una expressió
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaModificarExpressio extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Àrea de text que mostra l'expressió seleccionada
     */
    private JTextField Expressio;
    /**
     * Botó per guardar l'expressió modificada
     */
    private JButton Guardar;
    /**
     * Botó per anar a la vista de gestió d'expressions
     */
    private JButton Enrere;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaModificarExpressio
     * @param pCtrlPresentacio Controlador de Presentació
     */
    public vistaModificarExpressio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Modificar expressió");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> expressio = _ctrlPresentacio.toStringExpActiva();
        String s = expressio.get(0);
        if (s == "-31") {
            Expressio.setText("");
        } else {
            Expressio.setText(expressio.get(1));
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaModificarExpresio");
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
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardar(e);
            }

        });
    }

    /**
     * Funció que captura l'acció del botó Guardar i crida a la funció modificarExpressio del controlador de Presentació
     * @param event acció que es captura al clicar el botó Guardar
     */
    public void actionPerformed_buttonGuardar(ActionEvent event) {
        int codi = _ctrlPresentacio.modificarExpressio(Expressio.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -50) {
            int isel = vistaDialogo.setDialogo(frame, "Modificar expressió", "L'expressió no s'ha pogut modificar correctament", strBotones, 0);
            System.out.println("Error expressió igual: " + isel + " " + strBotones[isel]);
        } else if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Modificar expressió", "No hi ha cap expressió seleccionada", strBotones, 0);
            System.out.println("Error expressió igual: " + isel + " " + strBotones[isel]);
        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Modificar expressió", "Ja existeix una expressió igual en el directori", strBotones, 0);
            System.out.println("Error expressió igual: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "Modificar expressió", "S'ha d'introduir un valor vàlid com a expressió", strBotones, 0);
            System.out.println("Error expressió nula: " + isel + " " + strBotones[isel]);

        } else if (codi == -10) {
            _ctrlPresentacio.activarGestioExpressio();
            frame.dispose();
            dispose();
        }
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarGestioExpressio del controlador de Presentació
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarGestioExpressio();
        frame.dispose();
        dispose();
    }

}
