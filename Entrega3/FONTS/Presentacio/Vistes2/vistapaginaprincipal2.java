package Presentacio.Vistes2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vistapaginaprincipal2 extends JFrame{
    private JPanel panel;
    private JButton crearDocumentButton;
    private JButton importarButton;
    private JButton gestionarExpressionsButton;
    private JTextField document;
    private JLabel etiqueta_doc_sel;
    private JButton visualitzarModificarButton;
    private JButton eliminarDocumentSeleccionatButton;
    private JButton seleccionarDocumentButton;
    private JButton exportarButton;
    private JComboBox cerquesBox;
    private JLabel etiqueta_cerques;
    private JButton importar;

    public vistapaginaprincipal2() {
        setContentPane(panel);
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Pàgina principal");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        importar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
