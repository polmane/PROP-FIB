package FONTS.Presentacio.vistes;

import FONTS.Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.file;

public class VistaCrearDirectori extends JFrame {

    private final JPanel panel = new JPanel();

    private final JLabel titolVista = new JLabel("Pàgina principal");

    private final JButton BnouDirectori = new JButton("Crear Directori");

    private final JTextArea idDir = new JTextArea("Id del directori");

    public VistaCrearDirectori() {
        setBounds(500,300,500,300);
        setResizable(true);
        setTitle("Gestor de Documents PROP");

        titolVista.setBounds(10, 5, 120 ,30);
        add(titolVista);

        BnouDirectori.setBounds(150, 90, 200, 20);
        add(BnouDirectori);

        idDir.setBounds(175, 35, 200, 20);
        add(idDir);

        add(panel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener crearDirectori = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idDir.getText().lenght() == 0) {
                    JDialog error = new JDialog(frame, "Error: no s'ha introduït un identificador per el directori");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("S'ha de introduir un valor valid com a identificador");
                    txtError.setBounds(80, 20, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else {
                    CtrlPresentacio.crearDirectori(idDir.getText());
                    CtrlPresentacio.VistaPagPrincipal(idDir.getText());
                    setVisible(false);
                }
            }
        }

        BnouDirectori.addActionListener(crearDirectori);

    }
}