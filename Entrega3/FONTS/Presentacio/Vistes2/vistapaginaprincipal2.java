package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class vistapaginaprincipal2 extends JFrame{
    private CtrlPresentacio _ctrlPresentacio;
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
    private JPanel panelDocument;
    private JPanel panelGestio;
    private JPanel panelOpcions;
    private JButton importar;
    private JFileChooser file_chooser;

    public vistapaginaprincipal2() {
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
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
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistapaginaprincipal2();
            }
        });
    }

}
