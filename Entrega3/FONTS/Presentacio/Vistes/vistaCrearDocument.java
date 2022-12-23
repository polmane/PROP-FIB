package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Representa la vista de la pàgina on es crea un nou document des de l'aplicació
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaCrearDocument extends JFrame {
    /**
     * Controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els components de la vista
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
     * Àrea de text per introduir el contingut
     */
    private JTextArea Contingut;
    /**
     * Botó per crear el document
     */
    private JButton Crear;
    /**
     * Botó per tornar a la vista principal
     */
    private JButton Enrere;
    /**
     * Etiqueta per indicar el camp Contingut
     */
    private JLabel labelContingut;
    /**
     * Etiqueta per indicar el camp Títol
     */
    private JLabel labelTitol;
    /**
     * Etiqueta per indicar el camp Autor
     */
    private JLabel labelAutor;
    /**
     * Panell de "scroll"
     */
    private JScrollPane scrollPane;
    /**
     * Panell dels botons Crear i Enrere
     */
    private JPanel panelOpcions;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");


    public vistaCrearDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Crear un document");

        Contingut.setLineWrap(true);
        Contingut.setWrapStyleWord(true);

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
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonCrear(e);
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
     * Funció que implementa l'acció quan es prem el botó Crear, que crida a la funció crearDocument del controlador de presentació
     * @param event acció que es captura al clicar el botó Crear
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        int codi = _ctrlPresentacio.crearDocument(Autor.getText(), Titol.getText(), Contingut.getText());
        if (codi == 30) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "No s'ha afegit el document", "Titol o autor nuls", strBotones, 1);
            System.out.println("Error CrearDoc nuls: " + isel + " " + strBotones[isel]);

        } else if (codi == 20) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "No s'ha afegit el document", "Ja existeix un document en el directori \n amb aquest títol i autor", strBotones, 1);
            System.out.println("Error Crear Doc ja existeix doc amb titol i autor: " + isel + " " + strBotones[isel]);

        } else {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();
        }
    }

    /**
     * Funció que implementa l'acció quan es prem el botó Enrere, i activa de nou la pagina principal
     * (activarPagPrincipal del controlador de Presentació)
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }

}
