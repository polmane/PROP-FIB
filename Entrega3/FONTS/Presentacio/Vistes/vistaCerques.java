package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Representa la vista de la pàgina on es fa la cerca dels títols d'un autor o dels autors que comencen per un prefix
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaCerques extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Àrea de text per escriure el necessari per fer la cerca
     */
    private JTextField Info;
    /**
     * Desplegable per seleccionar la cerca a realitzar
     */
    private JComboBox Cerques;
    /**
     * Botó per realitzar la cerca
     */
    private JButton Buscar;
    /**
     * Botó per obrir la vistaPagPrincipal
     */
    private JButton Enrere;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelInfo;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Panell que conté els botons de buscar i enrere
     */
    private JPanel panelOpcions;
    /**
     * Desplegable per seleccionar el mètode d'ordenació del resultat
     */
    private JComboBox Sorting;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelSorting;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Panell "scroll" per al resultat
     */
    private JScrollPane scrollPane;
    /**
     * Etiqueta per indicar el tipus de cerca a realitzar (ComboBox Cerques
     */
    private JLabel labelCerques;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaCerques
     *
     * @param pCtrlPresentacio Controlador de Presentció
     */
    public vistaCerques(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Cerca dels titols d'un autor o dels autors que comencen per un prefix");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaCrearDocument");
                frame.dispose();
                dispose();
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
            }
        });

        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });
        Cerques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_ComboBoxCerques(e);
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
     * Funció que captura l'acció del botó Buscar i crida a la funció llistaTitolsPerAutor del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");
        List<String> res = new ArrayList<String>();

        if (Cerques.getSelectedItem() == "Llista de titols d'un autor") {
            res = _ctrlPresentacio.llistaTitolsPerAutor(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
            if (res == null) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Titols per autor", "No hem trobat titols amb aquest autor \n Vés amb compte amb els espai en blanc", strBotones, 1);
                System.out.println("Titols autor, null: " + isel + " " + strBotones[isel]);
            } else {
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i));
                    Resultat.append("\n");
                }
            }
        } else if (Cerques.getSelectedItem() == "Llista d'autors que comencen per un prefix") {
            res = _ctrlPresentacio.llistaAutorsPerPrefix(Info.getText(), String.valueOf(Sorting.getSelectedItem()));
            if (res == null) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Autors donat un prefix", "No hi ha autors amb aquest prefix \n Vés amb compte amb els espai en blanc", strBotones, 1);
                System.out.println("Prefix sense resultat: " + isel + " " + strBotones[isel]);
            } else {
                for (int i = 0; i < res.size(); ++i) {
                    Resultat.append(res.get(i));
                    Resultat.append("\n");
                }
            }
        }
    }

    /**
     * Funció que captura l'acció del desplegable Cerques i actualitza el desplegable Sorting
     *
     * @param event acció que es captura al seleccionar una opció del desplegable Cerques
     */
    public void actionPerformed_ComboBoxCerques(ActionEvent event) {
        if (String.valueOf(Cerques.getSelectedItem()) == "Llista de titols d'un autor") {
            Sorting.removeAllItems();
            Sorting.addItem("TIT_DESC");
            Sorting.addItem("TIT_ASC");
        } else if (String.valueOf(Cerques.getSelectedItem()) == "Llista d'autors que comencen per un prefix") {
            Sorting.removeAllItems();
            Sorting.addItem("AUT_DESC");
            Sorting.addItem("AUT_ASC");
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
        panel.setLayout(new GridLayoutManager(11, 3, new Insets(0, 0, 0, 0), -1, -1));
        Info = new JTextField();
        panel.add(Info, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelResultat = new JLabel();
        Font labelResultatFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelResultat.getFont());
        if (labelResultatFont != null) labelResultat.setFont(labelResultatFont);
        labelResultat.setText("Resultat de la cerca:");
        panel.add(labelResultat, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelInfo = new JLabel();
        Font labelInfoFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelInfo.getFont());
        if (labelInfoFont != null) labelInfo.setFont(labelInfoFont);
        labelInfo.setText("Info");
        panel.add(labelInfo, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Cerques = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Llista de titols d'un autor");
        defaultComboBoxModel1.addElement("Llista d'autors que comencen per un prefix");
        Cerques.setModel(defaultComboBoxModel1);
        panel.add(Cerques, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCerques = new JLabel();
        Font labelCerquesFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelCerques.getFont());
        if (labelCerquesFont != null) labelCerques.setFont(labelCerquesFont);
        labelCerques.setText("Tria la cerca que vol realitzar");
        panel.add(labelCerques, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Buscar = new JButton();
        Buscar.setText("Buscar");
        panelOpcions.add(Buscar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enrere = new JButton();
        Enrere.setText("Enrere");
        panelOpcions.add(Enrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        Sorting = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("TIT_DESC");
        defaultComboBoxModel2.addElement("TIT_ASC");
        Sorting.setModel(defaultComboBoxModel2);
        panel.add(Sorting, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSorting = new JLabel();
        Font labelSortingFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelSorting.getFont());
        if (labelSortingFont != null) labelSorting.setFont(labelSortingFont);
        labelSorting.setText("Com vol obtenir ordenat el resultat?");
        panel.add(labelSorting, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel.add(scrollPane, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Resultat = new JTextArea();
        Resultat.setEditable(false);
        Resultat.setText("");
        scrollPane.setViewportView(Resultat);
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
