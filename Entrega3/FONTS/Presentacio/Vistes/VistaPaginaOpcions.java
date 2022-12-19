package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import java.awt.event.*;
import javax.swing.*;

public class VistaPaginaOpcions extends JFrame {
    private CtrlPresentacio _ctrlPresentacio;
    private JTextField Autor;
    private JTextField Titol;
    private JTextField Contingut;
    private JButton Eliminar;
    private JButton Modificar;
    private JButton Enrere;
    private JComboBox Cerques;
    private JLabel TitolCerques;
    private JPanel panel;

    public VistaPaginaOpcions(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina d'opcions per a un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int id = 0;
        _ctrlPresentacio.seleccionarDocument(id);
        String autor = "pol";
        Autor.setText(autor);
        String titol = "mañe";
        Titol.setText(titol);
        String contingut = "roiger";
        Contingut.setText(contingut);

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
            }
        });


        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                _ctrlPresentacio.eliminarDocument(id);
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //_ctrlPresentacio.vistaModificarDocument(id, autor, titol, contingut);
                setVisible(false);
            }
        });
    }
}
