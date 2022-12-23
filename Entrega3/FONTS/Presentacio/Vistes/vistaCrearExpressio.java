package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
* Representa la vista de la pàgina on es crea una nova expressió
* @author isaac.roma.granado
* @author pol.mane.roiger
*/
public class vistaCrearExpressio extends JFrame {
    /**
     * Controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els components de la vista
     */
    private JPanel panel;
    /**
     * Camp de text per inserir la vista
     */
    private JTextField Expressio;
    /**
     * Botó per donar d'alta l'expressió
     */
    private JButton Crear;
    /**
     * Botó per tornar enrere a la vista de Gestió d'expressions
     */
    private JButton Enrere;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaCrearExpressio
     * @param pCtrlPresentacio Controlador de Presentació
     */
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
                actionPerformed_buttonCrear(e);
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });
    }

    /**
     * Funció que implementa l'acció quan es prem el botó Crear, que crida a la funció afegirExpressió del controlador de presentació
     * @param event acció que es captura al clicar el botó Crear
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        int codi = _ctrlPresentacio.afegirExpressio(Expressio.getText());
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

    /**
     * Funció que implementa l'acció quan es prem el botó Enrere, i activa la pàgina de gestió d'expressions
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        System.out.println("Tancant vistaCrearExpressio");
        _ctrlPresentacio.activarGestioExpressio();
        frame.dispose();
        dispose();
    }
}
