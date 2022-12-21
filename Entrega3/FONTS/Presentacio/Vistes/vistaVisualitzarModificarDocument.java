package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class vistaVisualitzarModificarDocument extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JPanel panelOpcions;
    private JButton Modificar;
    private JButton Enrere;
    private JButton GuardarTitol;
    private JTextArea Contingut;
    private JLabel labelAutor;
    private JLabel labelTitol;
    private JLabel labelContingut;
    private JButton GuardarAutor;
    private JButton GuardarContingut;
    private JScrollPane scrollPane;

    private JFrame frame = new JFrame("JFrame");

    public vistaVisualitzarModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'un document");

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
                if (Enrere.getText() == "Enrere") {
                    _ctrlPresentacio.activarPagPrincipal();
                    frame.dispose();
                    dispose();
                } else {
                    Autor.setEditable(false);
                    Titol.setEditable(false);
                    Contingut.setEditable(false);
                    GuardarTitol.setVisible(false);
                    GuardarAutor.setVisible(false);
                    GuardarContingut.setVisible(false);
                    Modificar.setEnabled(true);
                    Enrere.setText("Enrere");
                }
            }
        });
        GuardarTitol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.modificarTitol(Titol.getText());
                if (codi == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error titol", "No s'ha introduït cap títol", strBotones, 0);
                    System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error títol", "Ja existeix un document amb aquest títol i autor", strBotones, 0);
                    System.out.println("Modif titol, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
                } else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Mofificar títol", "Títol modificat correctament", strBotones, 1);
                    System.out.println("Titol modificat: " + isel + " " + strBotones[isel]);
                }


            }
        });
        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
        GuardarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.modificarAutor(Autor.getText());
                if (codi == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error autor", "No s'ha introduït cap autor", strBotones, 0);
                    System.out.println("Modif autor, buit: " + isel + " " + strBotones[isel]);

                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error autor", "Ja existeix un document amb aquest autor i títol", strBotones, 0);
                    System.out.println("Modif autor, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
                } else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Mofificar autor", "Autor modificat correctament", strBotones, 1);
                    System.out.println("Autor modificat: " + isel + " " + strBotones[isel]);
                }
            }
        });
        GuardarContingut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.modificarContingut(Contingut.getText());
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Mofificar contingut", "Contingut modificat correctament", strBotones, 1);
                System.out.println("Contingut modificat: " + isel + " " + strBotones[isel]);
            }
        });
    }

}
