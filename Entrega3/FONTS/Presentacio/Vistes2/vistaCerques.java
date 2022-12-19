package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaCerques extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField Info;
    private JComboBox Cerques;
    private JButton Buscar;
    private JButton Enrere;
    private JLabel labelInfo;
    private JLabel labelResultat;
    private JPanel panelOpcions;
    private JList Resultat;

    public vistaCerques(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Cerca d'els titols d'un autor o dels autos que comencen per un prefix");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaCrearDocument");
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistaCerques(new CtrlPresentacio());
            }
        });
    }
}
