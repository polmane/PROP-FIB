package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class vistaRecuperarSessio extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;
    private JButton Recuperar;
    private JButton Crear;
    private JLabel text;
    private JPanel panel;

    public vistaRecuperarSessio() {
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

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
                new vistaRecuperarSessio();
            }
        });
    }
}
