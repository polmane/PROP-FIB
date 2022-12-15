package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInfoDocument extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField Info;
    private JButton Buscar;
    private JButton Enrere;
    private JComboBox cerques;
    private JTextPane Resultat;

    private JFrame frame = new JFrame("JFrame");

    public VistaInfoDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Cerca d'informació d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= 0;
                String autor = "Pol";
                String titol = "mañe";
                String contingut = "Roiger";
                _ctrlPresentacio.vistaPaginaOpcions(id, autor, titol, contingut);
                setVisible(false);
            }
        });
    }
}
