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
                    dispose();
                }
                else {
                    //TODO: enviar missatge que el directori no s'ha pogut recuperar correctament o que no hi havia directori per recuperar
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
                    dispose();
                }
                else {
                    //TODO: enviar un missatge perquè el directori no s'ha creat correctament
                }
            }
        });
    }

}
