package Presentacio.Vistes;

import Domini.Classes.Pair;
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
import java.util.List;
import java.util.Locale;

import static java.lang.Character.isWhitespace;

/**
 * Representa la vista de la pàgina on es fa la cerca dels documents semblants
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaDocumentsSemblants extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Botó per seleccionar el mètode BOOL de comparació
     */
    private JRadioButton BOOLRadioButton;
    /**
     * Botó per seleccionar el mètode TF_IDF de comparació
     */
    private JRadioButton TFIDFRadioButton;
    /**
     * Desplegable per seleccionar el mètode d'ordenació del resultat
     */
    private JComboBox Sorting;
    /**
     * Botó per realitzar la cerca
     */
    private JButton Buscar;
    /**
     * Botó que obra la pàgina principal
     */
    private JButton Enrere;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelMetode;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelK;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelDocument;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Panell que conté els boton de buscar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Desplegable que mostra els documents actius amb els que realitzar la cerca
     */
    private JComboBox Documents;
    /**
     * Mòdel del Spinner k
     */
    private SpinnerModel valors_k;
    /**
     * Àrea de text per introduïr el nombre de documents semblants a obtenir de la cerca
     */
    private JSpinner k;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPaneRes;
    /**
     * Element que permet fer scroll a l'àrea de text
     */
    private JScrollPane scrollPaneDocs;
    /**
     * Etiqueta per fer la pregunta sobre el camp Sorting
     */
    private JLabel labelSorting;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaDocumentsSemblants
     *
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaDocumentsSemblants(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;

        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Buscar documents semblants");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ArrayList<String> resultat = _ctrlPresentacio.llistarDocuments();
        if (resultat == null) {
            Buscar.setEnabled(false);
            k.setEnabled(false);

        } else if (resultat.size() == 1) {
            Buscar.setEnabled(false);
            k.setEnabled(false);
            Documents.addItem(resultat.get(0) + " | Autor: " + resultat.get(1) + " | Titol: " + resultat.get(2));

        } else {
            for (int i = 0; i < resultat.size(); i += 3) {
                Documents.addItem(resultat.get(i) + " | Autor: " + resultat.get(i + 1) + " | Titol: " + resultat.get(i + 2));
            }
            k.setEnabled(true);
            valors_k = new SpinnerNumberModel(0, 0, (resultat.size() / 3) - 1, 1);
            k.setModel(valors_k);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaDocumentsSemblants");
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
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
            }
        });
        TFIDFRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_radioButtonTF_IDF(e);
            }
        });
        BOOLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_radioButtonBOOL(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }

    /**
     * Funció que captura l'acció del botó Buscar i crida a la funció compararDocuments del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");
        int numdocs = 0;

        try {
            numdocs = Integer.parseInt(String.valueOf(k.getValue()));
        } catch (NumberFormatException excepcio) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "No s'ha introduit un valor correcte de documents", "El nombre de documents a obtenir \n ha de ser un nombre natural major que 0", strBotones, 0);
            System.out.println("Error valor de k: " + isel + " " + strBotones[isel]);
        }

        List<Pair<String, String>> res;
        String sort = String.valueOf(Sorting.getSelectedItem());
        String infoDoc = (String.valueOf(Documents.getSelectedItem()));

        int j = 0;
        while (!isWhitespace(infoDoc.charAt(j))) ++j;
        int idDoc = Integer.parseInt(infoDoc.substring(0, j));

        if (BOOLRadioButton.isSelected()) {
            res = _ctrlPresentacio.compararDocuments("BOOL", sort, numdocs, idDoc);

        } else {
            res = _ctrlPresentacio.compararDocuments("TF_IDF", sort, numdocs, idDoc);
        }
        if (res == null) {
            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            int isel = vistaDialogo.setDialogo(frame, "Cerca semblants ", "No hi ha resultats per aquests paràmetres", strBotones, 1);
            System.out.println("Resultat null de documents semblants: " + isel + " " + strBotones[isel]);
        } else {
            for (int i = 0; i < res.size(); ++i) {
                Resultat.append("Autor: " + res.get(i).first());
                Resultat.append(" | ");
                Resultat.append("Títol: " + res.get(i).second());
                Resultat.append("\n");
            }
        }
    }

    /**
     * Funció que captura l'acció del botó BOOLRadioButton i deixa de seleccionar el TFIDFRadioButton
     *
     * @param event acció que es captura al clicar el botó BOOLRadioButton
     */
    public void actionPerformed_radioButtonBOOL(ActionEvent event) {
        TFIDFRadioButton.setSelected(false);
        System.out.println("Botó BOOL");
    }

    /**
     * Funció que captura l'acció del botó TFIDFRadioButton i deixa de seleccionar el BOOLRadioButton
     *
     * @param event acció que es captura al clicar el botó TFIDFRadioButton
     */
    public void actionPerformed_radioButtonTF_IDF(ActionEvent event) {
        BOOLRadioButton.setSelected(false);
        System.out.println("Botó TFIDF");
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
        panel.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(3, 2, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelDocument = new JLabel();
        Font labelDocumentFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelDocument.getFont());
        if (labelDocumentFont != null) labelDocument.setFont(labelDocumentFont);
        labelDocument.setText("Introdueix el document que vol comparar");
        panel.add(labelDocument, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelK = new JLabel();
        Font labelKFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelK.getFont());
        if (labelKFont != null) labelK.setFont(labelKFont);
        labelK.setText("Introdueix el nombre de documents semblants que vol obtenir");
        panel.add(labelK, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BOOLRadioButton = new JRadioButton();
        Font BOOLRadioButtonFont = this.$$$getFont$$$(null, Font.BOLD, -1, BOOLRadioButton.getFont());
        if (BOOLRadioButtonFont != null) BOOLRadioButton.setFont(BOOLRadioButtonFont);
        BOOLRadioButton.setText("BOOL");
        panel.add(BOOLRadioButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TFIDFRadioButton = new JRadioButton();
        Font TFIDFRadioButtonFont = this.$$$getFont$$$(null, Font.BOLD, -1, TFIDFRadioButton.getFont());
        if (TFIDFRadioButtonFont != null) TFIDFRadioButton.setFont(TFIDFRadioButtonFont);
        TFIDFRadioButton.setSelected(true);
        TFIDFRadioButton.setText("TF-IDF");
        panel.add(TFIDFRadioButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Sorting = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("SIM_ASC");
        defaultComboBoxModel1.addElement("SIM_DESC");
        defaultComboBoxModel1.addElement("AUT_ASC");
        defaultComboBoxModel1.addElement("AUT_DESC");
        defaultComboBoxModel1.addElement("TIT_ASC");
        defaultComboBoxModel1.addElement("TIT_DESC");
        Sorting.setModel(defaultComboBoxModel1);
        panel.add(Sorting, new GridConstraints(4, 3, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMetode = new JLabel();
        Font labelMetodeFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelMetode.getFont());
        if (labelMetodeFont != null) labelMetode.setFont(labelMetodeFont);
        labelMetode.setText("Mètode de comparació:");
        panel.add(labelMetode, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSorting = new JLabel();
        Font labelSortingFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelSorting.getFont());
        if (labelSortingFont != null) labelSorting.setFont(labelSortingFont);
        labelSorting.setText("Com vol ordenar el resultat?");
        panel.add(labelSorting, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(9, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Buscar = new JButton();
        Buscar.setText("Buscar");
        panelOpcions.add(Buscar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enrere = new JButton();
        Enrere.setText("Enrere");
        panelOpcions.add(Enrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelResultat = new JLabel();
        Font labelResultatFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelResultat.getFont());
        if (labelResultatFont != null) labelResultat.setFont(labelResultatFont);
        labelResultat.setText("Resultat:");
        panel.add(labelResultat, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel.add(spacer5, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        k = new JSpinner();
        panel.add(k, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPaneDocs = new JScrollPane();
        panel.add(scrollPaneDocs, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(50, 15), null, 0, false));
        Documents = new JComboBox();
        scrollPaneDocs.setViewportView(Documents);
        scrollPaneRes = new JScrollPane();
        panel.add(scrollPaneRes, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Resultat = new JTextArea();
        Resultat.setEditable(false);
        Resultat.setEnabled(true);
        scrollPaneRes.setViewportView(Resultat);
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
