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

import static java.lang.Character.isWhitespace;

/**
 * Representa la vista on es selecciona un document
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaSeleccionarDocument extends JFrame {
    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Llista que conté el que es mostrarà a l'àrea de text
     */
    private DefaultListModel<String> model = new DefaultListModel<>();
    /**
     * Àrea de text on es mostren els documents a seleccionar
     */
    private JList<String> Documents;
    /**
     * Botó per seleccionar un document
     */
    private JButton Seleccionar;
    /**
     * Botó per cancel·lar
     */
    private JButton Cancellar;
    /**
     * Panell que conté el botó per seleccionar i per cancel·lar
     */
    private JPanel panelOpcions;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelDocuments;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaSeleccionarDocument
     *
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaSeleccionarDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        Documents.setModel(model);

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Selecció d'un document");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) {
            Seleccionar.setEnabled(false);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Seleccionar documents", "No hi ha documents per seleccionar", strBotones, 1);
            System.out.println("No hi ha docs per seleccionar: " + isel + " " + strBotones[isel]);

        } else {
            for (int i = 0; i < resultat.size(); i += 3) {
                model.addElement(resultat.get(i) + " | " + resultat.get(i + 1) + " | " + resultat.get(i + 2));
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Tancant vistaSeleccionarDocument");
                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        });

        Seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonSeleccionar(e);
            }
        });
        Cancellar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonCancellar(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Seleccionar i crida a la funció seleccionarDocument del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Seleccionar
     */
    public void actionPerformed_buttonSeleccionar(ActionEvent event) {
        if (Documents.getSelectedValue() != null) {
            String resultat = String.valueOf(Documents.getSelectedValue());
            int i = 0;
            while (!isWhitespace(resultat.charAt(i))) ++i;
            int id = Integer.parseInt(resultat.substring(0, i));

            int codi = _ctrlPresentacio.seleccionarDocument(id);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            if (codi == -50) {
                int isel = vistaDialogo.setDialogo(frame, "Seleccionar document", "Hi ha hagut un problema amb disc al seleccionar el document", strBotones, 0);
                System.out.println("Error seleccio document persistencia: " + isel + " " + strBotones[isel]);

            } else if (codi == -20) {
                int isel = vistaDialogo.setDialogo(frame, "Seleccionar document", "No existeix aquest document", strBotones, 0);
                System.out.println("Error seleccio document no existent: " + isel + " " + strBotones[isel]);

            } else {
                System.out.println("Doc seleccionat: " + resultat);

                _ctrlPresentacio.activarPagPrincipal();
                frame.dispose();
                dispose();
            }
        } else {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Document no seleccionat", "Has de seleccionar un document perquè tingui efecte", strBotones, 1);
            System.out.println("Error document no seleccionat: " + isel + " " + strBotones[isel]);
        }
    }

    /**
     * Funció que captura l'acció del botó Cancel·lar i crida a la funció activarPagPrincipal del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Cancel·lar
     */
    public void actionPerformed_buttonCancellar(ActionEvent event) {
        System.out.println("Cancelar vistaSeleccionarDocument");
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
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
        panel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        labelDocuments = new JLabel();
        Font labelDocumentsFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelDocuments.getFont());
        if (labelDocumentsFont != null) labelDocuments.setFont(labelDocumentsFont);
        labelDocuments.setText("Selecciona un document:");
        panel.add(labelDocuments, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Seleccionar = new JButton();
        Seleccionar.setText("Seleccionar");
        panelOpcions.add(Seleccionar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Cancellar = new JButton();
        Cancellar.setText("Cancel·lar");
        panelOpcions.add(Cancellar, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel.add(scrollPane, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(250, 150), null, 0, false));
        Documents = new JList();
        scrollPane.setViewportView(Documents);
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
