package Presentacio.Vistes2;

import Domini.Classes.Document;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class vistaGestioExpressio extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JButton Crear;
    private JButton Eliminar;
    private JButton Modificar;
    private JComboBox Expresions;
    private JButton Buscar;
    private JButton Enrere;
    private JLabel labelInfo;
    private JPanel panelSelect;
    private JPanel panelBuscar;
    private JPanel panelOpcions;
    private JLabel labelResultat;
    private JTextArea Resultat;

    private JFrame frame = new JFrame("JFrame");

    public vistaGestioExpressio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'una expressió");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarExpressions();
        if (resultat == null) Buscar.setEnabled(false);
        else {
            for (int i = 1; i < resultat.size(); i += 2) {
                Expresions.addItem(resultat.get(i));
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaGestioExpressio");
                dispose();
            }
        });

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaCrearExpressio();
                dispose();
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                dispose();
            }
        });
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");
                Expresions.removeItem(Expresions.getSelectedItem());

                int codi = _ctrlPresentacio.eliminarExpressio(0);
                if (codi == 11) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Eliminar expressió","Expressió eliminada",strBotones,2);
                    System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);
                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error a l'eliminar","Expressio no reconeguda",strBotones,1);
                    System.out.println("Error eliminar exp no reconegut: " + isel + " " + strBotones[isel]);
                }
            }
        });
        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaModificarExpressio();
                setVisible(false);
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");
                ArrayList<Document> res = _ctrlPresentacio.selectPerExpressio(0);
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i).getTitol());
                    Resultat.append(" ");
                    Resultat.append(res.get(i).getAutor());
                    Resultat.append("\n");
                }
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaModificarExpressio();
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
                new vistaGestioExpressio(new CtrlPresentacio());
            }
        });
    }
}
