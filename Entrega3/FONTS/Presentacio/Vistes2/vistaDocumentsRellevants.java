package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaDocumentsRellevants extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField textField2;
    private JRadioButton BOOLRadioButton;
    private JRadioButton TFIDFRadioButton;
    private JComboBox comboBox2;
    private JButton Buscar;
    private JButton Enrere;
    private JPanel panelOpcions;
    private JLabel labelResultat;
    private JLabel labelMetode;
    private JTextField Paraules;
    private JList Resultat;

    public vistaDocumentsRellevants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents més rellevants segons una sèrie de paraules");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsRellevants");
                dispose();
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
                new vistaDocumentsRellevants(new CtrlPresentacio());
            }
        });
    }
}
