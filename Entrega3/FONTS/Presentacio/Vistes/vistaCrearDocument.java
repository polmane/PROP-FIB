package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaCrearDocument extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JTextArea Contingut;
    private JButton Crear;
    private JButton Enrere;
    private JLabel labelContingut;
    private JLabel labelTitol;
    private JLabel labelAutor;
    private JScrollPane scrollPane;
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
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });
    }

}
