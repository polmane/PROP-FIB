package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaPagPrincipal extends JFrame{
    private JButton crearDocument;
    private JButton afegirExpressió;
    private JButton obrirDocument;
    private JPanel panel;

    public VistaPagPrincipal() {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina principal");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        crearDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaCrearDocument();
                setVisible(false);
            }
        });

        afegirExpressió.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaAfegirExpressio();
                setVisible(false);
            }
        });

        obrirDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.openButtonText", "Obrir");
                UIManager.put("FileChooser.cancelButtonText", "Cancel·lar");
                CtrlPresentacio.vistaCarregarDocument();
                setVisible(false);
            }
        });
    }
}
