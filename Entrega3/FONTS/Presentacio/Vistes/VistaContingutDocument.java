package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.event.*;
import javax.swing.*;
public class VistaContingutDocument extends JFrame{
    CtrlPresentacio _ctrlPresentacio;
    private JTextField Autor;
    private JTextField Titol;
    private JTextPane Contingut;
    private JButton Buscar;
    private JPanel panel;
    private JButton Enrere;

    private JFrame frame = new JFrame("JFrame");

    public VistaContingutDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Autor.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït cap autor");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha de introduir un valor valid com a autor");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (Titol.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït cap titol");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha de introduir un valor valid com a titol");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else {
                    String contingut = _ctrlPresentacio.cercaPerAutoriTitol(Autor.getText(), Titol.getText());
                    Contingut.setText(contingut);
                }
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                String autor="pol";
                String titol = "mañe";
                String contingut="roiger";
                _ctrlPresentacio.vistaPaginaOpcions(id, autor, titol, contingut);
            }
        });
    }
}
