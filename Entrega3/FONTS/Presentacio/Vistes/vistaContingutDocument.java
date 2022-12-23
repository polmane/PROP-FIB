package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Representa la vista de la pàgina on es fa la cerca del contingut d'un document
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaContingutDocument extends JFrame {
    /**
     * Controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell de la vista, que conté tots els elements
     */
    private JPanel panel;
    /**
     * Camp de text per inserir l'autor
     */
    private JTextField Autor;
    /**
     * Camp de text per inserir el títol
     */
    private JTextField Titol;
    /**
     * Etiqueta per identificar el component Autor
     */
    private JLabel labelAutor;
    /**
     * Etiqueta per identificar el component Titol
     */
    private JLabel labelTitol;
    /**
     * Àrea de text per inserir el contingut resultant
     */
    private JTextArea Contingut;
    /**
     * Etiqueta per identificar el component Contingut
     */
    private JLabel labelContingut;
    /**
     * Botó per  realitzar la cerca
     */
    private JButton Buscar;
    /**
     * Botó per tornar a la pàgina principal
     */
    private JButton Enrere;
    /**
     * Panell que conté els botons Buscar i Enrere
     */
    private JPanel panelOpcions;
    /**
     * Panell "scroll" per al resultat
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la VistaContingutDocument
     * @param pCtrlPresentacio Controlador de Presentació
     */
    public vistaContingutDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar el contingut d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Contingut.setLineWrap(true);
        Contingut.setWrapStyleWord(true);

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
    }

    /**
     * Acció del botó Buscar, que crida a la funció cercaPerAutoriTitol del controlador de presentació per realitzar la cerca
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        String contingut = _ctrlPresentacio.cercaPerAutoriTitol(Autor.getText(), Titol.getText());
        System.out.println(contingut);

        if (contingut != null) {
            Contingut.setText(contingut);
        }
        else {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Error cerca contingut", "No s'ha trobat cap document amb aquest tiítol i autor \n Vés amb compte amb els espai en blanc", strBotones, 0);
            System.out.println("Error crear exp buida: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que implementa l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }

}
