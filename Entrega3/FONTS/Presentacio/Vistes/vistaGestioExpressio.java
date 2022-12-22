package Presentacio.Vistes;

import Domini.Classes.Pair;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isWhitespace;

public class vistaGestioExpressio extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JButton Crear;
    private JButton Eliminar;
    private JButton Modificar;
    private JComboBox Expressions;
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

        RefreshExpressionsGestio();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaGestioExpressio");
                frame.dispose();
                dispose();
            }
        });

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaCrearExpressio();
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");

                String info = String.valueOf(Expressions.getSelectedItem());
                int i = 0;
                while(!isWhitespace(info.charAt(i))) ++i;
                int id = Integer.parseInt(info.substring(0, i));
                System.out.println(id);

                int codi = _ctrlPresentacio.eliminarExpressio(id);
                System.out.println(codi);
                if (codi == 11 ||codi == 10) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Eliminar expressió", "Expressió eliminada", strBotones, 1);
                    System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);
                    Expressions.removeItem(Expressions.getSelectedItem());

                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar", "Expressio no reconeguda", strBotones, 0);
                    System.out.println("Error eliminar exp no reconegut: " + isel + " " + strBotones[isel]);
                }
            }
        });

        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");

                String info = String.valueOf(Expressions.getSelectedItem());
                int id = Integer.parseInt(info.substring(0, 1));

                List<Pair<String, String>> res = _ctrlPresentacio.selectPerExpressio(id);
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i).first());
                    Resultat.append(" | ");
                    Resultat.append(res.get(i).second());
                    Resultat.append("\n");
                }
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaModificarExpressio();
            }
        });
        Expressions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Expressions.getSelectedItem() != null) {
                    String info = String.valueOf(Expressions.getSelectedItem());
                    int id = Integer.parseInt(info.substring(0, 1));

                    int codi = _ctrlPresentacio.seleccionarExpressio(id);
                    System.out.println("Seleccionant expressio " + id + "; " +codi);
                }
                else {
                    Buscar.setEnabled(false);
                }
            }
        });
    }

    public void RefreshExpressionsGestio() {
        Expressions.removeAllItems();
        ArrayList<String> resultat = _ctrlPresentacio.llistarExpressions();
        if (resultat == null) {
            Buscar.setEnabled(false);
        }
        else {
            for (int i = 0; i < resultat.size()-1; i += 2) {
                Expressions.addItem(resultat.get(i) + " | " + resultat.get(i+1));
            }
            Buscar.setEnabled(true);
        };
    }

    public void activar() {
        this.setEnabled(true);
        this.toFront();
        RefreshExpressionsGestio();
    }

    public void desactivar() {
        this.setEnabled(false);
    }

}
