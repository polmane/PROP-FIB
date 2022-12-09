package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaAfegirExpressio extends JFrame {
    private JButton afegirExpressió;
    private JPanel panel;
    private JTextField introduirExpressió;

    private JFrame frame = new JFrame("JFrame");

    public VistaAfegirExpressio() {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina per afegir una expressió");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        afegirExpressió.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(introduirExpressió.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït cap expressió");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha de introduir un valor valid com a expressió");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else {
                    CtrlPresentacio.afegirExp(introduirExpressió.getText());
                    CtrlPresentacio.PagPrincipal();
                    setVisible(false);
                }
            }
        });
    }
}
