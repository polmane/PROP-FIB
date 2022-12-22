package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representa la vista de la pàgina principal
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
                }
                else {
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
     * @param event acció que es captura al clicar el botó crearDocumentButton
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaCrearDocument();
        System.out.println("Obrint VistaCrearDocument");
    }

    /**
     * Funció que captura l'acció del botó seleccionarDocumentButton i crida a la funció ObrirVistaSeleccionarDocument del controlador de Presentació
     * @param event acció que es captura al clicar el botó seleccionarDocumentButton
     */
    public void actionPerformed_buttonSelecionar(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaSeleccionarDocument();
        System.out.println("Obrint VistaSeleccionaDocument");
    }

    /**
     * Funció que captura l'acció del botó gestionarExpressionsButton i crida a la funció ObrirVistaGestioExpressio del controlador de Presentació
     * @param event acció que es captura al clicar el botó gestionarExpressionsButton
     */
    public void actionPerformed_buttonGestionarExpressions(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaGestioExpressio();
        System.out.println("Obrint VistaGestioExpressio");
    }

    /**
     * Funció que captura l'acció del botó visualitzarModificarButton i crida a la funció ObrirVistaVisualitzarModificar del controlador de Presentació
     * @param event acció que es captura al clicar el botó visualitzarModificarButton
     */
    public void actionPerformed_buttonVisualitzarModificar(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaVisualitzarModificarDocument();
        System.out.println("Obrint VistaVisualitzarModificarDocument");
    }

    /**
     * Funció que captura l'acció del botó eliminarDocummentSeleccionatButton i crida a la funció eliminarDocument del controlador de Presentació
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
                int isel = vistaDialogo.setDialogo(frame, "Error a l'importar", "El fitxer amb path \n"+ paths.get(codi)+"\n ja existeix.\n Compte! Els fitxers anteriors a aquest s'han carregat correctament", strBotones, 0);
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

            int codi = _ctrlPresentacio.exportarDocument(f,path);

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
            System.out.println("[PagPrin]DocActiu: "+ s + " | " + document.get(1) + " | " + document.get(2));
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

}
