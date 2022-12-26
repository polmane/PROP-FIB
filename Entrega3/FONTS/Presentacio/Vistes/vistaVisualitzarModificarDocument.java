package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Representa la vista per visualitzar o modificar un document
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaVisualitzarModificarDocument extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Àrea de text que mostra l'autor del document seleccionat
     */
    private JTextField Autor;
    /**
     * Àrea de text que mostra el títol del document seleccionat
     */
    private JTextField Titol;
    /**
     * Panell que conté els botons per modificar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Botó que permet modificar el document seleccionat
     */
    private JButton Modificar;
    /**
     * Botó per tornar a la pàgina principal
     */
    private JButton Enrere;
    /**
     * Botó per guardar el títol modificat
     */
    private JButton GuardarTitol;
    /**
     * Àrea de text que mostra el contingut del document seleccionat
     */
    private JTextArea Contingut;
    /**
     * Etiqueta que representa l'autor del document seleccionat
     */
    private JLabel labelAutor;
    /**
     * Etiqueta que representa el títol del document seleccionat
     */
    private JLabel labelTitol;
    /**
     * Etiqueta que representa el contingut del document seleccionat
     */
    private JLabel labelContingut;
    /**
     * Botó per guardar l'autor modificat
     */
    private JButton GuardarAutor;
    /**
     * Botó per guardar el contingut modificat
     */
    private JButton GuardarContingut;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaVisualitzarModificarDocument
     *
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaVisualitzarModificarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Visualització i modificació d'un document");

        Contingut.setLineWrap(true);
        Contingut.setWrapStyleWord(true);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        GuardarAutor.setVisible(false);
        GuardarTitol.setVisible(false);
        GuardarContingut.setVisible(false);

        ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
        String s = document.get(0);
        if (s == "-31") {
            Autor.setText("");
            Titol.setText("");
            Contingut.setText("");
        } else {
            Autor.setText(document.get(1));
            Titol.setText(document.get(2));
            Contingut.setText(document.get(3));
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaVisualitzarModificarDocument");
                frame.dispose();
                dispose();
            }
        });

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonModificar(e);
            }
        });

        GuardarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarAutor(e);
            }
        });

        GuardarTitol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarTitol(e);
            }
        });

        GuardarContingut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonGuardarContingut(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        //Botó Enrere amb mutació a Cancel·lar
        if (Enrere.getText() == "Enrere") {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();

        } else {
            ArrayList<String> document = _ctrlPresentacio.toStringDocActiu();
            String s = document.get(0);
            if (s != "-31") {
                Autor.setText(document.get(1));
                Titol.setText(document.get(2));
                Contingut.setText(document.get(3));
            }
            Autor.setEditable(false);
            Titol.setEditable(false);
            Contingut.setEditable(false);

            GuardarTitol.setVisible(false);
            GuardarAutor.setVisible(false);
            GuardarContingut.setVisible(false);

            Modificar.setEnabled(true);
            Enrere.setText("Enrere");
            Modificar.setText("Modificar");
        }
    }

    /**
     * Funció que captura l'acció del botó Modificar i fa editables els tres camps de text
     *
     * @param event acció que es captura al clicar el botó Modificar
     */
    public void actionPerformed_buttonModificar(ActionEvent event) {
        if (Modificar.getText() == "Modificar") {
            Autor.setEditable(true);
            Titol.setEditable(true);
            Contingut.setEditable(true);
            GuardarTitol.setVisible(true);
            GuardarAutor.setVisible(true);
            GuardarContingut.setVisible(true);
            Modificar.setText("Pàgina principal");
            Enrere.setText("Cancel·lar");
        } else {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarAutor i crida a la funció modificarAutor del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó GuardarAutor
     */
    public void actionPerformed_buttonGuardarAutor(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarAutor(Autor.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "Error autor", "No s'ha introduït cap autor", strBotones, 0);
            System.out.println("Modif autor, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Error autor", "Ja existeix un document amb aquest autor (i títol)", strBotones, 0);
            System.out.println("Modif autor, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);
        } else {
            GuardarAutor.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar autor", "Autor modificat correctament", strBotones, 1);
            System.out.println("Autor modificat: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarTitol i crida a la funció modificarTitol del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó GuardarTitol
     */
    public void actionPerformed_buttonGuardarTitol(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarTitol(Titol.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "Error titol", "No s'ha introduït cap títol", strBotones, 0);
            System.out.println("Modif titol, buit: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "Error títol", "Ja existeix un document amb aquest títol (i autor)", strBotones, 0);
            System.out.println("Modif titol, doc amb autor i titol repetit: " + isel + " " + strBotones[isel]);

        } else {
            GuardarTitol.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar títol", "Títol modificat correctament", strBotones, 1);
            System.out.println("Titol modificat: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que captura l'acció del botó GuardarContingut i crida a la funció modificarContingut del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó GuardarContingut
     */
    public void actionPerformed_buttonGuardarContingut(ActionEvent event) {
        Enrere.setEnabled(false);
        int codi = _ctrlPresentacio.modificarContingut(Contingut.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -50) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "Error al modificar el contingut a disc", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else if (codi == -31) {
            int isel = vistaDialogo.setDialogo(frame, "Error modificar", "No hi ha cap document seleccionat", strBotones, 0);
            System.out.println("Modif autor, doc no sel: " + isel + " " + strBotones[isel]);

        } else {
            GuardarContingut.setEnabled(false);
            int isel = vistaDialogo.setDialogo(frame, "Mofificar contingut", "Contingut modificat correctament", strBotones, 1);
            System.out.println("Contingut modificat: " + isel + " " + strBotones[isel]);
        }
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
        panel.setLayout(new GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Modificar = new JButton();
        Modificar.setText("Modificar");
        panelOpcions.add(Modificar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enrere = new JButton();
        Enrere.setText("Enrere");
        panelOpcions.add(Enrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Titol = new JTextField();
        Titol.setEditable(false);
        panel.add(Titol, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelAutor = new JLabel();
        Font labelAutorFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelAutor.getFont());
        if (labelAutorFont != null) labelAutor.setFont(labelAutorFont);
        labelAutor.setText("Autor");
        panel.add(labelAutor, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelTitol = new JLabel();
        Font labelTitolFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelTitol.getFont());
        if (labelTitolFont != null) labelTitol.setFont(labelTitolFont);
        labelTitol.setText("Titol");
        panel.add(labelTitol, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelContingut = new JLabel();
        Font labelContingutFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelContingut.getFont());
        if (labelContingutFont != null) labelContingut.setFont(labelContingutFont);
        labelContingut.setText("Contingut");
        panel.add(labelContingut, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        GuardarAutor = new JButton();
        GuardarAutor.setEnabled(true);
        GuardarAutor.setText("Guardar");
        panel.add(GuardarAutor, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GuardarTitol = new JButton();
        GuardarTitol.setEnabled(true);
        GuardarTitol.setText("Guardar");
        panel.add(GuardarTitol, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GuardarContingut = new JButton();
        GuardarContingut.setEnabled(true);
        GuardarContingut.setText("Guardar");
        panel.add(GuardarContingut, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel.add(scrollPane, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(250, 150), null, 0, false));
        Contingut = new JTextArea();
        Contingut.setEditable(false);
        scrollPane.setViewportView(Contingut);
        Autor = new JTextField();
        Autor.setEditable(false);
        panel.add(Autor, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
