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

        documentSeleccionat();

        crearDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _ctrlPresentacio.ObrirVistaCrearDocument();
                documentSeleccionat();
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
                int id = documentSeleccionat();
                int codi =_ctrlPresentacio.eliminarDocument(id);
                System.out.println(id);
                if (codi == 31) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("No has seleccionat cap document");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (codi == 20) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("Identificador de document no vàlid");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else if (codi == 11) {
                    JDialog error = new JDialog(frame, "Error");
                    error.setBounds(800, 300, 400, 200);
                    error.setLayout(null);

                    JLabel txtError = new JLabel("Document eliminat");
                    txtError.setBounds(150, 30, 400, 40);
                    error.add(txtError);
                    error.setVisible(true);
                } else {
                    autor.setText("");
                    titol.setText("");
                    visualitzarModificarButton.setEnabled(false);
                }
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

    public int documentSeleccionat() {
        ArrayList<String> document = _ctrlPresentacio.getIdDocSeleccionat();
        String s = document.get(0);
        if (s == "-31") {
            autor.setText("");
            titol.setText("");
            visualitzarModificarButton.setEnabled(false);
        } else {
            for (int i = 0; i < document.size(); ++i) {
                autor.setText(document.get(1));
                titol.setText(document.get(2));
                visualitzarModificarButton.setEnabled(true);
            }
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
