package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaCrearExpressio extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField Expressio;
    private JButton Crear;
    private JButton Enrere;

    public vistaCrearExpressio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Crear un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //_ctrlPresentacio.activarPagPrincipal(); ES LA PAGINA DE GESTIO EXPRESSIO
                System.out.println("Tancant vistaDocumentsRellevants");
                dispose();
            }
        });
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.afegirExp(Expressio.getText());
                Expressio.setText("");
                System.out.println(Expressio.getText());
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaGestioExpressio();
                dispose();
            }
        });
    }

}
