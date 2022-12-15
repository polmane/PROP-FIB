package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaPaginaOpcions extends JFrame {

    private JTextField Autor;
    private JTextField Titol;
    private JTextField Contingut;
    private JButton Eliminar;
    private JButton Modificar;
    private JButton Enrere;
    private JComboBox Cerques;
    private JLabel TitolCerques;
    private JPanel panel;

    public VistaPaginaOpcions(int id, String autor, String titol, String contingut) {
        setContentPane(panel);
        setBounds(500, 300, 700, 500);
        setResizable(true);
        setTitle("Pàgina d'opcions per a un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CtrlPresentacio.seleccionarDocument(id);
        Autor.setText(autor);
        Titol.setText(titol);
        Contingut.setText(contingut);

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaPagPrincipal();
                setVisible(false);
            }
        });


        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.eliminarDocument(id);
                CtrlPresentacio.vistaPagPrincipal();
                setVisible(false);
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaModificarDocument(id, autor, titol, contingut);
                setVisible(false);
            }
        });
        Cerques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Cerques.getSelectedItem().toString() == "Contingut d'un document") {
                    CtrlPresentacio.vistaContingutDocument(id, autor, titol, contingut);
                    setVisible(false);
                } else if (Cerques.getSelectedItem().toString() == "Informació d'un document") {
                    //CtrlPresentacio.vistaInfoDocument(id);
                    setVisible(false);
                } else if (Cerques.getSelectedItem().toString() == "Documents semblants") {
                    CtrlPresentacio.vistaDocsSemblants(id, autor, titol, contingut);
                    setVisible(false);
                } else {
                    CtrlPresentacio.vistaDocsSemblantsPerExp(id, autor, titol, contingut);
                    setVisible(false);
                }

            }
        });
    }
}
