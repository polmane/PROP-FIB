package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class vistaGestioExpressio extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JButton Crear;
    private JButton Eliminar;
    private JButton Modificar;
    private JComboBox Expresions;
    private JButton Cercar;
    private JButton Enrere;
    private JLabel labelInfo;
    private JPanel panelSelect;
    private JPanel panelBuscar;
    private JPanel panelOpcions;
    private JList Resultat;
    private JLabel labelResultat;

    public vistaGestioExpressio() {
        //_ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'una expressió");

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
                new vistaGestioExpressio();
            }
        });
    }
}
