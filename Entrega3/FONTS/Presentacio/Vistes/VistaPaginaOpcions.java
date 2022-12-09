package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class VistaPaginaOpcions extends JFrame {

    private JTextField Autor;
    private JTextField Titol;
    private JTextField Contingut;
    private JButton Eliminar;
    private JButton Modificar;
    private JButton Enrere;
    private JComboBox Cerques;
    private JLabel TitolCerques;
    private JPanel panel;

    public VistaPaginaOpcions(int id) {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina per afegir una expressió");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Autor.setText("pol");

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.PagPrincipal();
                setVisible(false);
            }
        });


        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.eliminarDocument(id);
                CtrlPresentacio.PagPrincipal();
                setVisible(false);
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaModificarDocument(id);
                setVisible(false);
            }
        });
    }
}
