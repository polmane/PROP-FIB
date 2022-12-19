package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;

public class vistaPaginaPrincipal extends JFrame{
    private CtrlPresentacio _ctrlPresentacio;
    private JPanel panel;
    private JButton crearDocumentButton;
    private JButton importarButton;
    private JButton gestionarExpressionsButton;
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
    private JLabel labelAutor;
    private JLabel labelTitol;
    private JTextField autor;
    private JTextField titol;

    private JFileChooser file_chooser;

    public vistaPaginaPrincipal(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Pàgina principal");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if(autor.getText() == null || titol.getText() == null) {
            visualitzarModificarButton.setEnabled(false);
        } else visualitzarModificarButton.setEnabled(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                setVisible(false);
                System.out.println("Tancant aplicacio, guardant estat");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Aplicacio tancada");
                dispose();
            }
        });

        crearDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaCrearDocument();
            }
        });
        seleccionarDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaSeleccionarDocument();
            }
        });

        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exportar FileChooser");
            }
        });
        gestionarExpressionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaGestioExpressio();
            }
        });
        visualitzarModificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaVisualitzarModificarDocument();
            }
        });

        eliminarDocumentSeleccionatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //TODO comprovar que hi hagi algun seleccionat: _ctrlDomin.getIdDocSeleccionat?
                //int codi =_ctrlPresentacio.eliminarDocument();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exportar FileChooser");
            }
        });
        cerquesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cerquesBox.getSelectedItem() == "Contingut d'un document") {
                    _ctrlPresentacio.ObrirVistaContingutDocument();
                    desactivar();
                } else if (cerquesBox.getSelectedItem() == "Llista de títols d'un autor" || cerquesBox.getSelectedItem() == "Llista d'autors que comencen per un prefix") {
                    _ctrlPresentacio.ObrirVistaCerques();
                    desactivar();
                } else if (cerquesBox.getSelectedItem() == "Documents semblants") {
                    _ctrlPresentacio.ObrirVistaDocumentsSemblants();
                    desactivar();
                } else if (cerquesBox.getSelectedItem() == "Documents rellevants") {
                    _ctrlPresentacio.ObrirVistaDocumentsRellevants();
                    desactivar();
                }
            }
        });
    }

    public void hacerVisible() {
        //this.pack();
        this.setVisible(true);
    }

    public void activar() {
        this.setEnabled(true);
        this.toFront();
    }

    public void desactivar() {
        this.setEnabled(false);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new vistaPaginaPrincipal(new CtrlPresentacio());
            }
        });
    }

}
