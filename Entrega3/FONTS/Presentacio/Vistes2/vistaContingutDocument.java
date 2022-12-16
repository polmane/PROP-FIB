package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class vistaContingutDocument extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JLabel labelAutor;
    private JLabel labelTitol;
    private JTextArea Contingut;
    private JLabel labelContingut;
    private JButton Buscar;
    private JButton Enrere;
    private JPanel panelOpcions;

    public vistaContingutDocument() {
        //_ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar el contingut d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistaContingutDocument();
            }
        });
    }
}
