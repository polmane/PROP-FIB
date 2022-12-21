package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

public class vistaRecuperarSessio extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;
    private JButton Recuperar;
    private JButton Crear;
    private JLabel text;
    private JPanel panel;

    public vistaRecuperarSessio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestor de documents de PROP");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Recuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //_ctrlPresentacio.carregar
                System.out.println("Carregant sessio");
                _ctrlPresentacio.activarPagPrincipal();
                dispose();
            }
        });
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.crearDirectori(0);
                System.out.println("Creant nou directori");
                _ctrlPresentacio.activarPagPrincipal();
                dispose();
            }
        });
    }

}
