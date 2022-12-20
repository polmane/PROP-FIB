package Presentacio.Vistes2;

import Domini.Classes.Document;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class vistaVisualitzarModificarDocument extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JPanel panelOpcions;
    private JButton Modificar;
    private JButton Enrere;
    private JButton Guardar;
    private JTextArea Contingut;
    private JLabel labelAutor;
    private JLabel labelTitol;
    private JLabel labelContingut;
    private JButton button1;
    private JButton button2;
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
        Guardar.setEnabled(false);

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
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.modificarAutor(Autor.getText());
                if (codi == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error autor","No s'ha introduït cap autor",strBotones,1);
                    System.out.println("Modif autor, buit: " + isel + " " + strBotones[isel]);

                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error autor","Ja existeix un document amb aquest autor i títol",strBotones,1);
                    System.out.println("Modif autor, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
                }
                int codi1 = _ctrlPresentacio.modificarTitol(Titol.getText());
                if (codi1 == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error titol","No s'ha introduït cap títol",strBotones,1);
                    System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

                } else if (codi1 == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error títol","Ja existeix un document amb aquest títol i autor",strBotones,1);
                    System.out.println("Modif titol, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
                }
                int codi2 = _ctrlPresentacio.modificarContingut(Contingut.getText());
                System.out.println("modificant Document");

                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });
        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autor.setEditable(true);
                Titol.setEditable(true);
                Contingut.setEditable(true);
                Guardar.setEnabled(true);
                Modificar.setEnabled(false);
                Enrere.setText("Cancel·lar");
            }
        });
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistaVisualitzarModificarDocument(new CtrlPresentacio());
            }
        });
    }
}
