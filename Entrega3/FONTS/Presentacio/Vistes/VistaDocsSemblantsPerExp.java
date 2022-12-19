package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaDocsSemblantsPerExp extends JFrame{
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JComboBox Expresio;
    private JButton Busar;
    private JButton Enrere;
    private JTextPane Docs;

    private JFrame frame = new JFrame("JFrame");

    public VistaDocsSemblantsPerExp(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
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
                int id= 0;
                String autor = "Pol";
                String titol = "mañe";
                String contingut = "Roiger";
                //_ctrlPresentacio.vistaPaginaOpcions(id, autor, titol, contingut);
                setVisible(false);
            }
        });
    }
}
