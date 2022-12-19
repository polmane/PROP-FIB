package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaCrearDocument extends JFrame{
    private CtrlPresentacio _ctrlPresentacio;
    private JTextField Autor;
    private JTextField Titol;
    private JTextField Contingut;
    private JButton Crear;
    private JPanel panel;
    private JTextField Format;

    private JFrame frame = new JFrame("JFrame");

    public VistaCrearDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Autor.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït cap autor");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha d'introduir un valor valid com a autor");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (Titol.getText().length() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït cap titol");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha d'introduir un valor valid com a titol");
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
                    int codi = _ctrlPresentacio.crearDocument(Autor.getText(), Titol.getText(), Contingut.getText());
                    if (codi == 10) {System.out.println("Document afegit i seleccionat correctament. ");}
                    else if (codi == 20){
                        System.out.println("ERROR: Ja existeix un document amb autor i titol donats, NO s'ha afegit cap nou document");
                    }
                    else {
                        System.out.println("ERROR: Autor i/o titol no vàlids, escrigui un string!");
                    }
                    //_ctrlPresentacio.vistaPaginaOpcions(codi, Autor.getText(), Titol.getText(), Contingut.getText());
                    setVisible(false);
                }
            }
        });
    }
}
