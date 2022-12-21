package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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
    private JComboBox Sorting;
    private JLabel labelSorting;
    private JTextArea Resultat;
    private JFrame frame = new JFrame("JFrame");


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
                frame.dispose();
                dispose();
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");
                List<String> res = new ArrayList<String>();
                //DefaultListModel model = (DefaultListModel) Resultat.getModel();
                if (Cerques.getSelectedItem() == "Llista de titols d'un autor") {
                    res = _ctrlPresentacio.llistaTitolsPerAutor(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
                    if (res == null) {
                        VistaDialogo vistaDialogo = new VistaDialogo();
                        String[] strBotones = {"Ok"};
                        int isel = vistaDialogo.setDialogo(frame, "Titols per autor", "No hem trobat titols amb aquest autor", strBotones, 1);
                        System.out.println("Titols autor, null: " + isel + " " + strBotones[isel]);
                    }
                    else {
                        for (int i = 0; i < res.size(); ++i) {
                            Resultat.append(res.get(i));
                            Resultat.append("\n");
                        }
                    }
                } else if (Cerques.getSelectedItem() == "Llista d'autors que comencen per un prefix") {
                    res = _ctrlPresentacio.llistaAutorsPerPrefix(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
                    if (res == null) {
                        VistaDialogo vistaDialogo = new VistaDialogo();
                        String[] strBotones = {"Ok"};
                        int isel = vistaDialogo.setDialogo(frame, "Autors donat un prefix", "No hi ha autors amb aquest prefix", strBotones, 1);
                        System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
                    } else {
                        for (int i = 0; i < res.size(); ++i) {
                            Resultat.append(res.get(i));
                            Resultat.append("\n");
                        }
                    }
                }
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
        Cerques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(Cerques.getSelectedItem()) == "Llista de titols d'un autor") {
                    Sorting.removeAllItems();
                    Sorting.addItem("TIT_DESC");
                    Sorting.addItem("TIT_ASC");
                }
                else if (String.valueOf(Cerques.getSelectedItem()) == "Llista d'autors que comencen per un prefix") {
                    Sorting.removeAllItems();
                    Sorting.addItem("AUT_DESC");
                    Sorting.addItem("AUT_ASC");
                }
            }
        });
    }

}
