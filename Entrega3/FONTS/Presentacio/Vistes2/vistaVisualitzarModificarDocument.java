package Presentacio.Vistes2;

import Domini.Classes.Document;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vistaVisualitzarModificarDocument extends JFrame{

    private CtrlPresentacio _ctrlPresentacio;

    private JPanel panel;
    private JTextField Autor;
    private JTextField Titol;
    private JPanel panelOpcions;
    private JButton Modificar;
    private JButton Enrere;
    private JButton Guardar;
    private JTextArea Contingut;
    private JLabel labelAutor;
    private JLabel labelTitol;
    private JLabel labelContingut;

    public vistaVisualitzarModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaVisualitzarModificarDocument");
                dispose();
            }
        });

        Document d = _ctrlPresentacio._ctrlDomini._ctrlDirectori.getDocumentActiu();
        Autor.setText(d.autor);
        Titol.setText(d.titol);
        Contingut.setText(d.contingut);
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
            }
        });
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = _ctrlPresentacio.modificarAutor(Autor.getText());
                int id1 = _ctrlPresentacio.modificarTitol(Titol.getText());
                int id2 = _ctrlPresentacio.modificarContingut(Contingut.getText());
                System.out.println("modificant Document");
                _ctrlPresentacio.activarPagPrincipal();
                setVisible(false);
            }
        });
        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autor.setEditable(true);
                Titol.setEditable(true);
                Contingut.setEditable(true);
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
                new vistaVisualitzarModificarDocument(new CtrlPresentacio());
            }
        });
    }
}
