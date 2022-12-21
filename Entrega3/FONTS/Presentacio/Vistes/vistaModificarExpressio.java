package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class vistaModificarExpressio extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JTextField Expressio;
    private JButton Guardar;
    private JButton Enrere;

    private JFrame frame = new JFrame("JFrame");

    public vistaModificarExpressio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> expressio = _ctrlPresentacio.toStringExpActiva();
        String s = expressio.get(0);
        if (s == "-31") {
            Expressio.setText("");
        } else {
            Expressio.setText(expressio.get(1));
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaModificarExpresio");
                frame.dispose();
                dispose();
            }
        });


        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarGestioExpressio();
                frame.dispose();
                dispose();
            }
        });
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.modificarExpressio(Expressio.getText());
                if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Modificar Expressió", "Ja existeix una expressió igual en el directori", strBotones, 2);
                    System.out.println("Error expressió igual: " + isel + " " + strBotones[isel]);
                } else if (codi == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Modificar Expressió", "S'ha d'introduir un valor vàlid com a paràmetre", strBotones, 2);
                    System.out.println("Error expressió nula: " + isel + " " + strBotones[isel]);
                } else if (codi == 10) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Modificar Expressió", "Expressió modificada corectament", strBotones, 2);
                    System.out.println("Expressió modificada: " + isel + " " + strBotones[isel]);

                    _ctrlPresentacio.activarGestioExpressio();
                    frame.dispose();
                    dispose();
                }

            }

        });
    }

}
