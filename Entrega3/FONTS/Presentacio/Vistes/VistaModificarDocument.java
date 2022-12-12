package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.event.*;
import javax.swing.*;

public class VistaModificarDocument extends JFrame{
    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JTextField Contingut;
    private JButton Guardar;

    private JFrame frame = new JFrame("JFrame");

    public VistaModificarDocument(int id, String autor, String titol, String contingut) {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Autor.setText(autor);
        Titol.setText(titol);
        Contingut.setText(contingut);
        Guardar.addActionListener(new ActionListener() {
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
                } /**else if (Format.getText().length() == 0) {
                 JDialog error = new JDialog(frame, "Error: no s'ha introduït cap format");
                 error.setBounds(800, 300, 400, 200);
                 error.setLayout(null);

                 JLabel txtError = new JLabel("S'ha de introduir un valor valid com a format");
                 txtError.setBounds(80, 20, 400, 40);
                 error.add(txtError);
                 error.setVisible(true);
                 } else if (Format.getText() != "prop" || formatTextArea.getText() != "xml" || formatTextArea.getText() != "txt") {
                 JDialog error = new JDialog(frame, "Error: no s'ha introduït un format valid");
                 error.setBounds(800, 300, 400, 200);
                 error.setLayout(null);

                 JLabel txtError = new JLabel("S'ha de introduir un valor valid com a format");
                 txtError.setBounds(80, 20, 400, 40);
                 error.add(txtError);
                 error.setVisible(true);*/
                else {
                    CtrlPresentacio.modificarAutor(Autor.getText());
                    CtrlPresentacio.modificarTitol(Titol.getText());
                    CtrlPresentacio.modificarContingut(Contingut.getText());
                    CtrlPresentacio.vistaPaginaOpcions(id, Autor.getText(), Titol.getText(), Contingut.getText());
                    setVisible(false);
                }
            }
        });
    }

}
