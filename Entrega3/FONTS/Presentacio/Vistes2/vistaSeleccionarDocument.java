package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class vistaSeleccionarDocument extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JComboBox Documents;
    private JButton Seleccionar;
    private JButton Cancellar;
    private JPanel panelOpcions;
    private JLabel labelDocuments;

    public vistaSeleccionarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Selecció d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistaSeleccionarDocument(new CtrlPresentacio());
            }
        });
    }
}
