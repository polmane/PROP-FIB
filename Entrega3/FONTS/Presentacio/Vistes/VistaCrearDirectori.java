package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class VistaCrearDirectori extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JButton NouDirectori;
    private JTextField idDir;
    private JLabel Titol;

    private JFrame frame = new JFrame("JFrame");

    public VistaCrearDirectori(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        NouDirectori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idDir.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït un identificador per el directori");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha de introduir un valor valid com a identificador");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else {
                    _ctrlPresentacio.crearDirectori(Integer.parseInt(idDir.getText()));
                    setVisible(false);
                }

            }
        });
    }

}
