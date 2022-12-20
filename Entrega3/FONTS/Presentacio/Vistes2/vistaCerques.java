package Presentacio.Vistes2;

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
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> res = new ArrayList<String>();
                //DefaultListModel model = (DefaultListModel) Resultat.getModel();
                if (Cerques.getSelectedItem() == "Llista de titols d'un autor") {
                    res = _ctrlPresentacio.llistaTitolsPerAutor(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
                    for(int i = 0; i < res.size(); ++i) {
                        Resultat.append(res.get(i));
                        Resultat.append(" ");
                    }
                } else if (Cerques.getSelectedItem() == "Llista d'autors que comencen per un prefix") {
                    res = _ctrlPresentacio.llistaAutorsPerPrefix(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
                    for(int i = 0; i < res.size(); ++i) {
                        Resultat.append(res.get(i));
                        Resultat.append(" ");
                    }
                }
            }
        });

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
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
