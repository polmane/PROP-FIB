package Presentacio.Vistes2;

import Domini.Classes.Pair;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class vistaDocumentsSemblants extends JFrame {

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JRadioButton BOOLRadioButton;
    private JRadioButton TFIDFRadioButton;
    private JComboBox Sorting;
    private JButton Buscar;
    private JButton Enrere;
    private JLabel labelMetode;
    private JLabel labelK;
    private JLabel labelDocument;
    private JLabel labelResultat;
    private JPanel panelOpcions;
    private JComboBox Documents;
    private JSpinner k;
    private JTextArea Resultat;

    public vistaDocumentsSemblants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents semblants");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsSemblants");
                dispose();
            }
        });

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();

        for (int i = 0; i < resultat.size(); i+=3) {
            Documents.addItem(resultat.get(i));
        }

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                dispose();
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");
                List<Pair<String, String>> res;
                if (BOOLRadioButton.isSelected()) {
                    res = _ctrlPresentacio.compararDocuments("BOOL" ,String.valueOf(Sorting.getSelectedItem()), Integer.parseInt(String.valueOf(k.getValue())), Integer.parseInt(String.valueOf(Documents.getSelectedItem())));
                } else {
                    res = _ctrlPresentacio.compararDocuments("TF_IDF" ,String.valueOf(Sorting.getSelectedItem()), Integer.parseInt(String.valueOf(k.getValue())), Integer.parseInt(String.valueOf(Documents.getSelectedItem())));
                }
                for(int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i).first());
                    Resultat.append(" ");
                    Resultat.append(res.get(i).second());
                    Resultat.append("\n");
                }

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
                new vistaDocumentsSemblants(new CtrlPresentacio());
            }
        });
    }
}
