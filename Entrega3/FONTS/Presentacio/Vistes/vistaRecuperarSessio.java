package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class vistaRecuperarSessio extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;
    private JButton Recuperar;
    private JButton Crear;
    private JLabel text;
    private JPanel panel;
    private JFrame frame = new JFrame("Frame");

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
                int codi = _ctrlPresentacio.carregarEstat();
                if (codi == -10) {
                    System.out.println("Carregant sessio");
                    _ctrlPresentacio.activarPagPrincipal();
                    frame.dispose();
                    dispose();
                }
                else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error recuperar sessió", "No s'ha pogut recuperat cap sessió", strBotones, 0);
                    System.out.println("Error recuperar sessió: " + isel + " " + strBotones[isel]);
                }
            }
        });

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = _ctrlPresentacio.crearDirectori(0);
                if (codi == -10) {
                    System.out.println("Creant nou directori");
                    _ctrlPresentacio.activarPagPrincipal();
                    frame.dispose();
                    dispose();
                }
                else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error al crear el directori", "No s'ha pogut crear un directori", strBotones, 0);
                    System.out.println("Error crear directori: " + isel + " " + strBotones[isel]);
                    System.exit(-1);
                }
            }
        });
    }

}
