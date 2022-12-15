package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaPagPrincipal extends JFrame{
    private CtrlPresentacio _ctrlPresentacio;
    private JButton crearDocument;
    private JButton afegirExpressio;
    private JButton obrirDocument;
    private JPanel panel;

    public VistaPagPrincipal(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina principal");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        crearDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.vistaCrearDocument();
            }
        });

        afegirExpressio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.vistaAfegirExpressio();
            }
        });

        obrirDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.openButtonText", "Obrir");
                UIManager.put("FileChooser.cancelButtonText", "Cancel·lar");
                _ctrlPresentacio.vistaCarregarDocument();
                setVisible(false);
            }
        });
    }
    public void hacerVisible() {
        this.pack();
        this.setVisible(true);
    }

    public void activar() {
        this.setEnabled(true);
    }

    public void desactivar() {
        this.setEnabled(false);
    }
}
