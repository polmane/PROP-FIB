package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaDocsSemblants extends JFrame{
    private JPanel panel;
    private JTextField idDoc;
    private JTextField K;
    private JButton Buscar;
    private JTextPane Docs;
    private JComboBox Comparacio;
    private JComboBox Sorting;
    private JButton Enrere;

    private JFrame frame = new JFrame("JFrame");

    public VistaDocsSemblants() {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
