package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInfoDocument extends JFrame{

    private CtrlPresentacio pcrpe;
    private JPanel panel;
    private JTextField Info;
    private JButton Buscar;
    private JButton Enrere;
    private JComboBox cerques;
    private JTextPane Resultat;

    private JFrame frame = new JFrame("JFrame");

    public VistaInfoDocument(int id, String autor, String titol, String contingut, CtrlPresentacio cpre) {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Cerca d'informació d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pcrpe = cpre;


        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcrpe.vistaPaginaOpcions(id, autor, titol, contingut);
                setVisible(false);
            }
        });
    }
}
