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
    private JScrollPane scrollPaneRes;
    private JScrollPane scrollPaneDocs;

    private JFrame frame = new JFrame("JFrame");

    public vistaDocumentsSemblants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar els documents semblants");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) Buscar.setEnabled(false);
        else {
            for (int i = 0; i < resultat.size(); i += 3) {
                Documents.addItem(resultat.get(i) + " | " + resultat.get(i + 1) + " | " + resultat.get(i + 2));
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsSemblants");
                frame.dispose();
                dispose();
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
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultat.setText("");
                int numdocs = 0;
                try {
                    numdocs = Integer.parseInt(String.valueOf(k.getValue()));
                } catch (NumberFormatException excepcio) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"No s'ha introduit un valor correcte de documents","El nombre de documents a obtenir \n ha de ser un nombre natural major que 0",strBotones,1);
                    System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
                }

                List<Pair<String, String>> res;
                String sort = String.valueOf(Sorting.getSelectedItem());
                String infoDoc = (String.valueOf(Documents.getSelectedItem()));
                int idDoc = Integer.parseInt(infoDoc.substring(0,1));

                if (BOOLRadioButton.isSelected()) {
                    res = _ctrlPresentacio.compararDocuments("BOOL" ,sort ,  numdocs, idDoc);

                } else {
                    res = _ctrlPresentacio.compararDocuments("TF_IDF" ,sort, numdocs, idDoc);
                }
                if (res == null) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Cerca semblants ","No hi ha resultats per aquests paràmetres",strBotones,2);
                    System.out.println("Resultat null de documents semblants: " + isel + " " + strBotones[isel]);
                }
                else {
                    for (int i = 0; i < res.size(); ++i) {
                        Resultat.append(res.get(i).first());
                        Resultat.append(" ");
                        Resultat.append(res.get(i).second());
                        Resultat.append("\n");
                    }
                }

            }
        });
        TFIDFRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BOOLRadioButton.setSelected(false);
                System.out.println("Botó TFIDF");
            }
        });
        BOOLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TFIDFRadioButton.setSelected(false);
                System.out.println("Botó BOOL");
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
