package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class vistaCrearModificarExpressio extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField Expressio;
    private JButton Crear;
    private JButton Modificar;

    public vistaCrearModificarExpressio() {
        //_ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Crear un document");

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
                new vistaCrearModificarExpressio();
            }
        });
    }
}
