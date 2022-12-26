package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representa la vista de la pàgina principal
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaPaginaPrincipal extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Botó que obra la vistaCreardocument
     */
    private JButton crearDocumentButton;
    /**
     * Botó per importar un o varis documents
     */
    private JButton importarButton;
    /**
     * Botó que obra la vistaGestioExpressio
     */
    private JButton gestionarExpressionsButton;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel etiqueta_doc_sel;
    /**
     * Botó que obra la vistaVisualitzarModificarDocument
     */
    private JButton visualitzarModificarButton;
    /**
     * Botó que elimina un document
     */
    private JButton eliminarDocumentSeleccionatButton;
    /**
     * Botó que obra la vistaSeleccionarDocument
     */
    private JButton seleccionarDocumentButton;
    /**
     * Botó per exportar un document
     */
    private JButton exportarButton;
    /**
     * Desplegable per indicar les diferents cerques que es poden realitzar
     */
    private JComboBox cerquesBox;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel etiqueta_cerques;
    /**
     * Panell que conté els dos camps de text on apareixen l'autor i el títol del document seleccionat
     */
    private JPanel panelDocument;
    /**
     * Panell que conté else botons per crear un document, importar, seleccionar un document i gestionar expressions
     */
    private JPanel panelGestio;
    /**
     * Panell que conté else botons per eliminar un document, exportar i
     * visualitzar un document i el desplegable per triar el format per exportar
     */
    private JPanel panelOpcions;
    /**
     * Etiqueta que indica l'autor del document seleccionat
     */
    private JLabel labelAutor;
    /**
     * Etiqueta que indica el títol del document seleccionat
     */
    private JLabel labelTitol;
    /**
     * Camp de text on apareix l'autor del document seleccionat
     */
    private JTextField autor;
    /**
     * Camp de text on apareix el títol del document seleccionat
     */
    private JTextField titol;
    /**
     * Desplegable per triar el format al exportar
     */
    private JComboBox format;
    /**
     * Explorador d'arxius
     */
    private JFileChooser file_chooser;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaPaginaPrincipal
     *
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaPaginaPrincipal(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(false);
        setTitle("Pàgina principal del Gestor de Documents");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        RefreshDocumentSeleccionatPagPrin();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                setVisible(false);
                System.out.println("Tancant aplicacio, guardant estat");

                int codi = _ctrlPresentacio.guardarEstat();
                if (codi == -10) {
                    System.out.println("Estat guardat amb exit");
                } else {
                    System.out.println("Estat NO guardat correctament");
                }
                System.out.println("Aplicacio tancada");
                frame.dispose();
                dispose();
            }
        });

        crearDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonCrear(e);
            }
        });
        seleccionarDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonSelecionar(e);
            }
        });

        gestionarExpressionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGestionarExpressions(e);
            }
        });
        visualitzarModificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonVisualitzarModificar(e);
            }
        });

        eliminarDocumentSeleccionatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEliminarDocument(e);
            }
        });

        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonImportar(e);
            }
        });

        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonExportar(e);
            }
        });
        cerquesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_ComboBoxCerques(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó crearDocumentButton i crida a la funció ObrirVistaCrearDocument del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó crearDocumentButton
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaCrearDocument();
        System.out.println("Obrint VistaCrearDocument");
    }

    /**
     * Funció que captura l'acció del botó seleccionarDocumentButton i crida a la funció ObrirVistaSeleccionarDocument del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó seleccionarDocumentButton
     */
    public void actionPerformed_buttonSelecionar(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaSeleccionarDocument();
        System.out.println("Obrint VistaSeleccionaDocument");
    }

    /**
     * Funció que captura l'acció del botó gestionarExpressionsButton i crida a la funció ObrirVistaGestioExpressio del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó gestionarExpressionsButton
     */
    public void actionPerformed_buttonGestionarExpressions(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaGestioExpressio();
        System.out.println("Obrint VistaGestioExpressio");
    }

    /**
     * Funció que captura l'acció del botó visualitzarModificarButton i crida a la funció ObrirVistaVisualitzarModificar del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó visualitzarModificarButton
     */
    public void actionPerformed_buttonVisualitzarModificar(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaVisualitzarModificarDocument();
        System.out.println("Obrint VistaVisualitzarModificarDocument");
    }

    /**
     * Funció que captura l'acció del botó eliminarDocummentSeleccionatButton i crida a la funció eliminarDocument del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó eliminarDocumentSeleccionatButton
     */
    public void actionPerformed_buttonEliminarDocument(ActionEvent event) {
        int id = RefreshDocumentSeleccionatPagPrin();
        int codi = _ctrlPresentacio.eliminarDocument(id);

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -50) {
            int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar", "Error a l'eliminar de disc", strBotones, 0);
            System.out.println("Error eliminar disc: " + isel + " " + strBotones[isel]);

        } else if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar", "No has seleccionat cap document", strBotones, 0);
            System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar", "Identificador del document no reconegut", strBotones, 0);
            System.out.println("Error eliminar doc no reconegut: " + isel + " " + strBotones[isel]);

        } else if (codi > -1 || codi == -11 || codi == -10) {
            int isel = vistaDialogo.setDialogo(frame, "Eliminar document", "Document eliminat amb exit", strBotones, 1);
            System.out.println("Document eliminat!: " + isel + " " + strBotones[isel]);
        }
        RefreshDocumentSeleccionatPagPrin();
    }

    /**
     * Funció que captura l'acció del botó importarButton i obra un explorador d'arxius per seleccionar un o varis documents
     *
     * @param event acció que es captura al clicar el botó importarButton
     */
    public void actionPerformed_buttonImportar(ActionEvent event) {
        JFrame parentFrame = new JFrame();
        file_chooser = new JFileChooser();
        System.out.println("Importar FileChooser");

        file_chooser.setDialogTitle("Importar fitxer");
        file_chooser.removeChoosableFileFilter(file_chooser.getAcceptAllFileFilter());
        file_chooser.addChoosableFileFilter(new FileNameExtensionFilter("(.txt), (.xml), (.prop)", "txt", "xml", "prop"));
        file_chooser.setMultiSelectionEnabled(true);

        int returnVal = file_chooser.showOpenDialog(parentFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = file_chooser.getSelectedFiles();
            ArrayList<String> paths = new ArrayList<>();
            for (File file : files) {
                paths.add(file.getAbsolutePath());
            }
            System.out.println("Importar següents documents: " + paths);
            int codi = _ctrlPresentacio.importarDocument(paths);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            if (codi == -10) {
                RefreshDocumentSeleccionatPagPrin();

            } else if (codi == -50) {
                int isel = vistaDialogo.setDialogo(frame, "Error a l'importar", "Algun dels fitxers té un format\n que el sistema no suporta.\n Compte! Els fitxers anteriors a aquest s'han carregat correctament", strBotones, 0);
                System.out.println("Error importar, llegir fitxer: " + isel + " " + strBotones[isel]);

            } else {
                int isel = vistaDialogo.setDialogo(frame, "Error a l'importar", "El fitxer amb path \n" + paths.get(codi) + "\n ja existeix.\n Compte! Si era una importació múltiple, els\n fitxers anteriors a aquest s'han carregat correctament", strBotones, 0);
                System.out.println("Error importar ja existeix algun document: " + isel + " " + strBotones[isel]);
            }

            System.out.println(paths);
        } else {
            System.out.println("Cancel·lant importacio");
        }
        parentFrame.dispose();
    }

    /**
     * Funció que captura l'acció del botó exportarButton i obra un explorador d'arxius per exportar un document
     *
     * @param event acció que es captura al clicar el botó exportarButton
     */
    public void actionPerformed_buttonExportar(ActionEvent event) {
        System.out.println("Exportar FileChooser");
        JFrame parentFrame = new JFrame();
        file_chooser = new JFileChooser();

        file_chooser.setDialogTitle("Escull la carpeta on exportar el fitxer");
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        file_chooser.setAcceptAllFileFilterUsed(false);

        int returnVal = file_chooser.showSaveDialog(parentFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = file_chooser.getSelectedFile();
            String path = file.getAbsolutePath();

            String f = String.valueOf(format.getSelectedItem());

            int codi = _ctrlPresentacio.exportarDocument(f, path);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            if (codi > -1) {
                int isel = vistaDialogo.setDialogo(frame, "Exportar", "Document exportat correctament", strBotones, 1);
                System.out.println("Exportar correcte: " + isel + " " + strBotones[isel]);

            } else {
                int isel = vistaDialogo.setDialogo(frame, "Error a l'exportar", "No existeix aquesta carpeta", strBotones, 0);
                System.out.println("Error a l'exportar: " + isel + " " + strBotones[isel]);

            }
            System.out.println(path + " " + f);

        } else {
            System.out.println("Cancel·lant exportació");
        }
        parentFrame.dispose();
    }

    /**
     * Funció que captura l'acció del desplegable cerquesBox i obra la vista adient a cada opció del desplegable
     *
     * @param event acció que es captura al seleccionar una opció del desplegable cerquesBox
     */
    public void actionPerformed_ComboBoxCerques(ActionEvent event) {
        if (cerquesBox.getSelectedItem() == "Contingut d'un document") {
            _ctrlPresentacio.ObrirVistaContingutDocument();
            desactivar();
        } else if (cerquesBox.getSelectedItem() == "Llista de títols d'un autor o d'autors que comencen per un prefix") {
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


    /**
     * Funció que refresca la pàgina principal per fer visible l'autor i el títol del document seleccionat
     *
     * @return retorna -31 si no hi ha cap document seleccionat, i l'identificaor del document seleccionat si no hi ha error
     */
    public int RefreshDocumentSeleccionatPagPrin() {
        ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
        String s = document.get(0);
        if (s == "-31") {
            autor.setText("");
            titol.setText("");
            visualitzarModificarButton.setEnabled(false);
            eliminarDocumentSeleccionatButton.setEnabled(false);
            exportarButton.setEnabled(false);
            format.setEnabled(false);
        } else {
            autor.setText(document.get(1));
            titol.setText(document.get(2));
            System.out.println("[PagPrin]DocActiu: " + s + " | " + document.get(1) + " | " + document.get(2));
            visualitzarModificarButton.setEnabled(true);
            eliminarDocumentSeleccionatButton.setEnabled(true);
            exportarButton.setEnabled(true);
            format.setEnabled(true);
        }
        int resultat = Integer.parseInt(s);
        return resultat;
    }

    /**
     * Funció que fa visible la finestra
     */
    public void hacerVisible() {
        this.setVisible(true);
    }

    /**
     * Funció que activa la finestra
     */
    public void activar() {
        this.setEnabled(true);
        this.toFront();
        RefreshDocumentSeleccionatPagPrin();
    }

    /**
     * Funció que desactiva la finestra
     */
    public void desactivar() {
        this.setEnabled(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(8, 6, new Insets(0, 0, 0, 0), -1, -1));
        panelDocument = new JPanel();
        panelDocument.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelDocument, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        etiqueta_doc_sel = new JLabel();
        etiqueta_doc_sel.setText("Document seleccionat");
        panelDocument.add(etiqueta_doc_sel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelAutor = new JLabel();
        labelAutor.setText("Autor:");
        panelDocument.add(labelAutor, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelTitol = new JLabel();
        labelTitol.setText("Títol:");
        panelDocument.add(labelTitol, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        autor = new JTextField();
        autor.setEditable(false);
        panelDocument.add(autor, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titol = new JTextField();
        titol.setEditable(false);
        panelDocument.add(titol, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelGestio = new JPanel();
        panelGestio.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelGestio, new GridConstraints(1, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        crearDocumentButton = new JButton();
        crearDocumentButton.setText("Crear document");
        panelGestio.add(crearDocumentButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        importarButton = new JButton();
        importarButton.setText("Importar");
        panelGestio.add(importarButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gestionarExpressionsButton = new JButton();
        gestionarExpressionsButton.setText("Gestionar expressions");
        panelGestio.add(gestionarExpressionsButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        seleccionarDocumentButton = new JButton();
        seleccionarDocumentButton.setText("Seleccionar document");
        panelGestio.add(seleccionarDocumentButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelGestio.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelGestio.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelGestio.add(spacer3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelGestio.add(spacer4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelGestio.add(spacer5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        visualitzarModificarButton = new JButton();
        visualitzarModificarButton.setText("Visualitzar/Modificar");
        panelOpcions.add(visualitzarModificarButton, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelOpcions.add(spacer6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panelOpcions.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panelOpcions.add(spacer8, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        exportarButton = new JButton();
        exportarButton.setText("Exportar");
        panelOpcions.add(exportarButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarDocumentSeleccionatButton = new JButton();
        eliminarDocumentSeleccionatButton.setText("Eliminar document seleccionat");
        panelOpcions.add(eliminarDocumentSeleccionatButton, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        format = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("TXT");
        defaultComboBoxModel1.addElement("XML");
        defaultComboBoxModel1.addElement("PROP");
        format.setModel(defaultComboBoxModel1);
        panelOpcions.add(format, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cerquesBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Llista de títols d'un autor o d'autors que comencen per un prefix");
        defaultComboBoxModel2.addElement("Contingut d'un document");
        defaultComboBoxModel2.addElement("Documents semblants");
        defaultComboBoxModel2.addElement("Documents rellevants");
        cerquesBox.setModel(defaultComboBoxModel2);
        panel.add(cerquesBox, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        etiqueta_cerques = new JLabel();
        etiqueta_cerques.setText("Seleccionar la cerca desitjada:");
        panel.add(etiqueta_cerques, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel.add(spacer9, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panel.add(spacer10, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel.add(spacer11, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        panel.add(spacer12, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panel.add(spacer13, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        panel.add(spacer14, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
