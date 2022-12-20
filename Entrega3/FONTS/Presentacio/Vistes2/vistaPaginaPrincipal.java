package Presentacio.Vistes2;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    private JFrame frame = new JFrame("JFrame");

    public vistaPaginaPrincipal(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Pàgina principal");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        RefreshDocumentSeleccionatPagPrin();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                setVisible(false);
                System.out.println("Tancant aplicacio, guardant estat");

                System.out.println("Aplicacio tancada");
                frame.dispose();
                dispose();
            }
        });


        crearDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaCrearDocument();
                System.out.println("Obrint VistaCrearDocument");
            }
        });
        seleccionarDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaSeleccionarDocument();
                System.out.println("Obrint VistaSeleccionaDocument");

            }
        });

        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Importar FileChooser");
            }
        });
        gestionarExpressionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaGestioExpressio();
                System.out.println("Obrint VistaGestioExpressio");
            }
        });
        visualitzarModificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaVisualitzarModificarDocument();
                System.out.println("Obrint VistaVisualitzarModificarDocument");
            }
        });

        eliminarDocumentSeleccionatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = RefreshDocumentSeleccionatPagPrin();
                int codi =_ctrlPresentacio.eliminarDocument(id);
                System.out.println(id);
                if (codi == 31) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error a l'eliminar","No has seleccionat cap document",strBotones,1);
                    System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);

                } else if (codi == 20) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Error a l'eliminar","Document no reconegut",strBotones,1);
                    System.out.println("Error eliminar doc no reconegut: " + isel + " " + strBotones[isel]);

                } else if (codi == 11) {
                    VistaDialogo vistaDialogo = new VistaDialogo();
                    String[] strBotones = {"Ok"};
                    int isel = vistaDialogo.setDialogo(frame,"Eliminar document","Document eliminat",strBotones,2);
                    System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);
                }
                RefreshDocumentSeleccionatPagPrin();
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

    public int RefreshDocumentSeleccionatPagPrin() {
        ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
        String s = document.get(0);
        if (s == "-31") {
            autor.setText("");
            titol.setText("");
            visualitzarModificarButton.setEnabled(false);
            eliminarDocumentSeleccionatButton.setEnabled(false);
            exportarButton.setEnabled(false);
        } else {
            autor.setText(document.get(1));
            titol.setText(document.get(2));
            System.out.println(document.get(3));
            visualitzarModificarButton.setEnabled(true);
            eliminarDocumentSeleccionatButton.setEnabled(true);
            exportarButton.setEnabled(true);
        }
        int resultat = Integer.parseInt(s);
        return resultat;
    }

    public void hacerVisible() {
        //this.pack();
        this.setVisible(true);
    }

    public void activar() {
        this.setEnabled(true);
        this.toFront();
        RefreshDocumentSeleccionatPagPrin();
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
