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
 * Representa la vista on es fa la gestió d'una expressió
 *
 * @author isaac.roma.granado
 * @author pol.mane.roiger
 */
public class vistaGestioExpressio extends JFrame {

    /**
     * Representa un controlador de Presentació
     */
    private CtrlPresentacio _ctrlPresentacio;
    /**
     * Panell que conté tots els elements de la finestra
     */
    private JPanel panel;
    /**
     * Botó que obra la vistaCrearExpressio
     */
    private JButton Crear;
    /**
     * Botó que elimina la expressió seleccionada
     */
    private JButton Eliminar;
    /**
     * Botó que obra la vistaModificarExpressio
     */
    private JButton Modificar;
    /**
     * Desplegable que mostra les expressions del directori
     */
    private JComboBox Expressions;
    /**
     * Botó per fer la cerca dels documents que compleixen la expressió seleccionada
     */
    private JButton Buscar;
    /**
     * Botó que obra la vistaPaginaPrincipal
     */
    private JButton Enrere;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelInfo;
    /**
     * Panell que conté el desplegable amb les epxressions del directori
     */
    private JPanel panelSelect;
    /**
     * Panell que conté els botons de buscar i enrere
     */
    private JPanel panelBuscar;
    /**
     * Panell que conté els botons de crear, eliminar i modificar una expressió
     */
    private JPanel panelOpcions;
    /**
     * Etiqueta indicativa per l'usuari
     */
    private JLabel labelResultat;
    /**
     * Àrea de text que mostra el resultat de la cerca
     */
    private JTextArea Resultat;
    /**
     * Panell per fer "scroll" de Resultat
     */
    private JScrollPane scrollPane;
    /**
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaGestióExpressio
     *
     * @param pCtrlPresentacio Controlador de Presentació
     */
    public vistaGestioExpressio(CtrlPresentacio pCtrlPresentacio) {
        _ctrlPresentacio = pCtrlPresentacio;
        setContentPane(panel);
        setBounds(450, 200, 700, 400);
        setResizable(true);
        setTitle("Gestió d'expressions");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        RefreshExpressionsGestio();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                _ctrlPresentacio.activarPagPrincipal();
                System.out.println("Tancant vistaGestioExpressio");
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
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonEliminar(e);
            }
        });

        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonBuscar(e);
            }
        });

        Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonModificar(e);
            }
        });
        Expressions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_ComboBoxExpressions(e);
            }
        });
    }

    /**
     * Funció que captura l'acció del botó Crear i crida a la funció ObrirVistaCrearExpressio del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Crear
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaCrearExpressio();
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
     * Funció que captura l'acció del botó Eliminar i crida a la funció eliminarExpressio del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Eliminar
     */
    public void actionPerformed_buttonEliminar(ActionEvent event) {
        Resultat.setText("");

        if (Expressions.getSelectedItem() != null) {
            String info = String.valueOf(Expressions.getSelectedItem());
            int i = 0;
            while (!isWhitespace(info.charAt(i))) ++i;
            int id = Integer.parseInt(info.substring(0, i));
            System.out.println(id);

            int codi = _ctrlPresentacio.eliminarExpressio(id);
            System.out.println(codi);

            VistaDialogo vistaDialogo = new VistaDialogo();
            String[] strBotones = {"Ok"};
            if (codi == -11 || codi == -10) {
                int isel = vistaDialogo.setDialogo(frame, "Eliminar expressió", "Expressió eliminada", strBotones, 1);
                System.out.println("Error eliminar seleccionat: " + isel + " " + strBotones[isel]);
                Expressions.removeItem(Expressions.getSelectedItem());

            } else if (codi == -20) {
                int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar expressió", "Expressió no reconeguda", strBotones, 0);
                System.out.println("Error eliminar exp no reconegut: " + isel + " " + strBotones[isel]);

            } else {
                int isel = vistaDialogo.setDialogo(frame, "Error a l'eliminar expressió", "Expressió no s'ha pogut eliminar de disc", strBotones, 0);
                System.out.println("Error eliminar exp no reconegut: " + isel + " " + strBotones[isel]);
            }
        }
    }

    /**
     * Funció que captura l'acció del desplegable Expressions i crida a la funció seleccionarExpressio del controlador de Presentació
     *
     * @param event acció que es captura al seleccionar una expressió del desplegable
     */
    public void actionPerformed_ComboBoxExpressions(ActionEvent event) {
        if (Expressions.getSelectedItem() != null) {
            Buscar.setEnabled(true);
            Modificar.setEnabled(true);
            String info = String.valueOf(Expressions.getSelectedItem());

            int i = 0;
            while (!isWhitespace(info.charAt(i))) ++i;
            int id = Integer.parseInt(info.substring(0, i));

            int codi = _ctrlPresentacio.seleccionarExpressio(id);

            if (codi == -20) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Error al seleccionar expressió", "Expressió no reconeguda", strBotones, 0);
                System.out.println("Error seleccio exp no reconegut: " + isel + " " + strBotones[isel]);
            }
            System.out.println("Seleccionant expressio " + id + "; " + codi);
        } else {
            Buscar.setEnabled(false);
            Modificar.setEnabled(false);
        }
    }

    /**
     * Funció que captura l'acció del botó Buscar i crida a la funció selectPerExpressio del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");
        System.out.println("AQUI SI");
        if (Expressions.getSelectedItem() != null) {
            String info = String.valueOf(Expressions.getSelectedItem());
            int i = 0;
            while (!isWhitespace(info.charAt(i))) ++i;
            int id = Integer.parseInt(info.substring(0, i));

            List<Pair<String, String>> res = _ctrlPresentacio.selectPerExpressio(id);
            System.out.println(res);
            if (res.size() != 0) {
                for (int j = 0; j < res.size(); ++j) {
                    Resultat.append("Autor: " + res.get(j).first());
                    Resultat.append(" | ");
                    Resultat.append("Titol: " + res.get(j).second());
                    Resultat.append("\n");
                }
            } else {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Cerca booleana", "No s'ha trobat cap document que compleixi l'expressió", strBotones, 1);
                System.out.println("cap doc compleix expressio: " + isel + " " + strBotones[isel]);
            }
        }
    }

    /**
     * Funció que captura l'acció del botó Modificar i crida a la funció ObrirVistaModificarExpressio del controlador de Presentació
     *
     * @param event acció que es captura al clicar el botó Modificar
     */
    public void actionPerformed_buttonModificar(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaModificarExpressio();
    }

    /**
     * Funció que refresca la finestra per actualitzar el desplegable amb les expressions del directori
     */
    public void RefreshExpressionsGestio() {
        Expressions.removeAllItems();

        ArrayList<String> resultat = _ctrlPresentacio.llistarExpressions();
        if (resultat == null) {
            Buscar.setEnabled(false);
            Modificar.setEnabled(false);
        } else {
            _ctrlPresentacio.seleccionarExpressio(Integer.parseInt(resultat.get(0)));
            for (int i = 0; i < resultat.size(); i += 2) {
                Expressions.addItem(resultat.get(i) + " : " + resultat.get(i + 1));
            }
            Buscar.setEnabled(true);
            Modificar.setEnabled(true);
        }
        ;
    }

    /**
     * Funció que activa la finestra
     */
    public void activar() {
        this.setEnabled(true);
        this.toFront();
        RefreshExpressionsGestio();
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
        panel.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelBuscar = new JPanel();
        panelBuscar.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelBuscar, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Buscar = new JButton();
        Buscar.setText("Buscar");
        panelBuscar.add(Buscar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enrere = new JButton();
        Enrere.setText("Enrere");
        panelBuscar.add(Enrere, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelSelect = new JPanel();
        panelSelect.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelSelect, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Expressions = new JComboBox();
        panelSelect.add(Expressions, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelInfo = new JLabel();
        Font labelInfoFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelInfo.getFont());
        if (labelInfoFont != null) labelInfo.setFont(labelInfoFont);
        labelInfo.setText("Selecciona una expressió:");
        panelSelect.add(labelInfo, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelResultat = new JLabel();
        Font labelResultatFont = this.$$$getFont$$$(null, Font.BOLD, -1, labelResultat.getFont());
        if (labelResultatFont != null) labelResultat.setFont(labelResultatFont);
        labelResultat.setText("Resultat de la cerca:");
        panel.add(labelResultat, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelOpcions = new JPanel();
        panelOpcions.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelOpcions, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Crear = new JButton();
        Crear.setText("Crear");
        panelOpcions.add(Crear, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Eliminar = new JButton();
        Eliminar.setText("Eliminar");
        panelOpcions.add(Eliminar, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Modificar = new JButton();
        Modificar.setText("Modificar");
        panelOpcions.add(Modificar, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel.add(scrollPane, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Resultat = new JTextArea();
        Resultat.setEditable(false);
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
