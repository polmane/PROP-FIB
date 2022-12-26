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
import java.util.Locale;

/**
 * Representa la vista de la pàgina on es crea un nou document des de l'aplicació
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaCrearDocument extends JFrame {
    /**
     * Controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els components de la vista
     */
    private JPanel panel;
    /**
     * Camp de text per inserir l'autor
     */
    private JTextField Autor;
    /**
     * Camp de text per inserir el títol
     */
    private JTextField Titol;
    /**
     * Àrea de text per introduir el contingut
     */
    private JTextArea Contingut;
    /**
     * Botó per crear el document
     */
    private JButton Crear;
    /**
     * Botó per tornar a la vista principal
     */
    private JButton Enrere;
    /**
     * Etiqueta per indicar el camp Contingut
     */
    private JLabel labelContingut;
    /**
     * Etiqueta per indicar el camp Títol
     */
    private JLabel labelTitol;
    /**
     * Etiqueta per indicar el camp Autor
     */
    private JLabel labelAutor;
    /**
     * Panell de "scroll"
     */
    private JScrollPane scrollPane;
    /**
     * Panell dels botons Crear i Enrere
     */
    private JPanel panelOpcions;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");


    public vistaCrearDocument(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Crear un document");

        Contingut.setLineWrap(true);
        Contingut.setWrapStyleWord(true);

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
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonCrear(e);
            }
        });
        Enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEnrere(e);
            }
        });
    }

    /**
     * Funció que implementa l'acció quan es prem el botó Crear, que crida a la funció crearDocument del controlador de presentació
     *
     * @param event acció que es captura al clicar el botó Crear
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        int codi = _ctrlPresentacio.crearDocument(Autor.getText(), Titol.getText(), Contingut.getText());

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -50) {
            int isel = vistaDialogo.setDialogo(frame, "Error crear document", "No s'ha pogut afegir el document a disc", strBotones, 0);
            System.out.println("Error crear doc disc: " + isel + " " + strBotones[isel]);

        } else if (codi == -30) {
            int isel = vistaDialogo.setDialogo(frame, "No s'ha afegit el document", "Titol o autor nuls", strBotones, 1);
            System.out.println("Error CrearDoc nuls: " + isel + " " + strBotones[isel]);

        } else if (codi == -20) {
            int isel = vistaDialogo.setDialogo(frame, "No s'ha afegit el document", "Ja existeix un document en el directori \n amb aquest títol i autor", strBotones, 1);
            System.out.println("Error Crear Doc ja existeix doc amb titol i autor: " + isel + " " + strBotones[isel]);

        } else {
            _ctrlPresentacio.activarPagPrincipal();
            frame.dispose();
            dispose();
        }
    }

    /**
     * Funció que implementa l'acció quan es prem el botó Enrere, i activa de nou la pagina principal
     * (activarPagPrincipal del controlador de Presentació)
     *
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
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
        panel.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        Autor = new JTextField();
        panel.add(Autor, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Titol = new JTextField();
        panel.add(Titol, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Crear = new JButton();
        Crear.setText("Crear");
        panelOpcions.add(Crear, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enrere = new JButton();
        Enrere.setText("Enrere");
        panelOpcions.add(Enrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel.add(scrollPane, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(250, 150), null, 1, false));
        Contingut = new JTextArea();
        Contingut.setColumns(0);
        scrollPane.setViewportView(Contingut);
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
