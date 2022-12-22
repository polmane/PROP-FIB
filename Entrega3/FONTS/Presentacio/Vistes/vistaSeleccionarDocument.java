package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.Character.isWhitespace;

public class vistaSeleccionarDocument extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> Documents;
    private JButton Seleccionar;
    private JButton Cancellar;
    private JPanel panelOpcions;
    private JLabel labelDocuments;
    private JScrollPane scrollPane;
    private JFrame frame = new JFrame("JFrame");

    public vistaSeleccionarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        Documents.setModel(model);

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Selecció d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) {
            Seleccionar.setEnabled(false);
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Seleccionar documents", "No hi ha documents per seleccionar", strBotones, 1);
            System.out.println("No hi ha docs per seleccionar: " + isel + " " + strBotones[isel]);
        } else {
            for (int i = 0; i < resultat.size(); i += 3) {
                model.addElement(resultat.get(i) + " | " + resultat.get(i + 1) + " | " + resultat.get(i + 2));
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Tancant vistaSeleccionarDocument");
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });

        Seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Documents.getSelectedValue() != null) {
                    String resultat = String.valueOf(Documents.getSelectedValue());
                    int i = 0;
                    while(!isWhitespace(resultat.charAt(i))) ++i;
                    int id = Integer.parseInt(resultat.substring(0, i));
                    int codi = _ctrlPresentacio.seleccionarDocument(id);
                    if (codi == -50) //TODO
                    System.out.println("Doc seleccionat: " + resultat);

                    _ctrlPresentacio.activarPagPrincipal();
                    frame.dispose();
                    dispose();
                } else {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame, "Document no seleccionat", "Has de seleccionar un document perquè tingui efecte", strBotones, 1);
                    System.out.println("Error seleccio document null: " + isel + " " + strBotones[isel]);
                }
            }
        });
        Cancellar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Cancelar vistaSeleccionarDocument");
                frame.dispose();
                dispose();
            }
        });
    }

}
