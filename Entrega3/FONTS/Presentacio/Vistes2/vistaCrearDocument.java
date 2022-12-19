package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaCrearDocument extends JFrame{

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
    private JFrame frame = new JFrame("JFrame");


    public vistaCrearDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Crear un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaCrearDocument");
                dispose();
            }
        });
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.crearDocument(Autor.getText(), Titol.getText(), Contingut.getText());
                if (codi == 30) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("Títol o autor nuls");
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
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
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
                new vistaCrearDocument(new CtrlPresentacio());
            }
        });
    }
}
