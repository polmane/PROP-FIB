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

    private JFrame frame = new JFrame("JFrame");

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

                System.out.println("Tancant vistaCrearExpressio");
                _ctrlPresentacio.activarGestioExpressio();
                frame.dispose();
                dispose();
            }
        });
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.afegirExp(Expressio.getText());
                if (codi == 30) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error crear expressio", "S'ha d'introduir un valor vàlid", strBotones, 0);
                    System.out.println("Error crear exp buida: " + isel + " " + strBotones[isel]);
                }
                else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error crear expressio", "Ja existeix aquesta expressió", strBotones, 0);
                    System.out.println("error ja existeix exp: " + isel + " " + strBotones[isel]);
                }
                else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Alta expressio", "Expressió afegida correctament", strBotones, 1);
                    System.out.println("exp afegida: " + isel + " " + strBotones[isel]);
                    System.out.println(Expressio.getText());
                }
                Expressio.setText("");

            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tancant vistaCrearExpressio");
                _ctrlPresentacio.activarGestioExpressio();
                frame.dispose();
                dispose();
            }
        });
    }

}
