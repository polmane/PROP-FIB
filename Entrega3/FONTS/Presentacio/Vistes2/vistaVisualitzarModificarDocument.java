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

    private JFrame frame = new JFrame("JFrame");

    public vistaVisualitzarModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'un document");

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
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("No s'ha introduït cap autor");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (codi == 20) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 700, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("Ja existeix un document en el directori amb aquest títol i autor");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                }
                int codi1 = _ctrlPresentacio.modificarTitol(Titol.getText());
                if (codi1 == 30) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("No s'ha introduït cap títol");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (codi1 == 20) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 700, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("Ja existeix un document en el directori amb aquest títol i autor");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                }
                int codi2 = _ctrlPresentacio.modificarContingut(Contingut.getText());
                System.out.println("modificant Document");
                _ctrlPresentacio.activarPagPrincipal();
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
