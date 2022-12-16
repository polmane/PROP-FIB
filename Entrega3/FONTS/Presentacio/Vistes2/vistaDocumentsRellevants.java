package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class vistaDocumentsRellevants extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField textField2;
    private JRadioButton BOOLRadioButton;
    private JRadioButton TFIDFRadioButton;
    private JComboBox comboBox2;
    private JTextArea Resultat;
    private JButton Buscar;
    private JButton Enrere;
    private JPanel panelOpcions;
    private JLabel labelResultat;
    private JLabel labelMetode;
    private JTextField Paraules;

    public vistaDocumentsRellevants() {
        //_ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents més rellevants segons una sèrie de paraules");

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
                new vistaDocumentsRellevants();
            }
        });
    }
}
