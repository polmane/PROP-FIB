package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

/**
 * Representa la vista per crear o recuperar una sessió
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaRecuperarSessio extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;

    /**
     * Botó per recuperar la sessió
     */
    private JButton Recuperar;

    /**
     * Botó per crear una sessió
     */
    private JButton Crear;

    /**
     * Label per indicar que vol fer l'usuari
     */
    private JLabel text;

    /**
     * Panel que conté tots els elements de la vista
     */
    private JPanel panel;

    /**
     * Finestra que apareix quan es realitza una acció
     */
    private JFrame frame = new JFrame("Frame");

    /**
     * Creadora de la vistaRecuperarSessio
     * @param pCtrlPresentacio Controlador de Presentció
     */
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
                actionPerformed_buttonRecuperar(e);
            }
        });

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonRecuperar(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó crear
     * @param event
     */
    public void actionPerformed_buttonRecuperar(ActionEvent event){
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

    public void actionPerformed_buttonCrear(ActionEvent event) {
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

}
