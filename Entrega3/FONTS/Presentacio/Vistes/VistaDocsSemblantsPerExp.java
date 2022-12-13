package Presentacio.Vistes;

import java.awt.event.*;
import javax.swing.*;

public class VistaDocsSemblantsPerExp extends JFrame{
    private JPanel panel;
    private JComboBox Expresio;
    private JButton Busar;
    private JButton Enrere;
    private JTextPane Docs;

    private JFrame frame = new JFrame("JFrame");

    public VistaDocsSemblantsPerExp() {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Comparació de documents");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Busar.addActionListener(new ActionListener() {
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
