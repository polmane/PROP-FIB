package Presentacio.Vistes;

import Domini.Classes.Pair;
import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isWhitespace;

/**
 * Representa la vista on es fa la gestió d'una expressió
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
     * Finestra que apareix quan hi ha un error
     */
    private JFrame frame = new JFrame("JFrame");

    /**
     * Creadora de la vistaGestióExpressio
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
     * @param event acció que es captura al clicar el botó Crear
     */
    public void actionPerformed_buttonCrear(ActionEvent event) {
        _ctrlPresentacio.ObrirVistaCrearExpressio();
    }

    /**
     * Funció que captura l'acció del botó Enrere i crida a la funció activarPagPrincipal del controlador de Presentació
     * @param event acció que es captura al clicar el botó Enrere
     */
    public void actionPerformed_buttonEnrere(ActionEvent event) {
        _ctrlPresentacio.activarPagPrincipal();
        frame.dispose();
        dispose();
    }

    /**
     * Funció que captura l'acció del botó Eliminar i crida a la funció eliminarExpressio del controlador de Presentació
     * @param event acció que es captura al clicar el botó Eliminar
     */
    public void actionPerformed_buttonEliminar(ActionEvent event) {
        Resultat.setText("");
        //TODO
        //if (Expres)
        String info = String.valueOf(Expressions.getSelectedItem());
        int i = 0;
        while(!isWhitespace(info.charAt(i))) ++i;
        int id = Integer.parseInt(info.substring(0, i));
        System.out.println(id);

        int codi = _ctrlPresentacio.eliminarExpressio(id);
        System.out.println(codi);

        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Ok"};
        if (codi == -11 ||codi == -10) {
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

    /**
     * Funció que captura l'acció del desplegable Expressions i crida a la funció seleccionarExpressio del controlador de Presentació
     * @param event acció que es captura al seleccionar una expressió del desplegable
     */
    public void actionPerformed_ComboBoxExpressions(ActionEvent event) {
        if (Expressions.getSelectedItem() != null) {
            Buscar.setEnabled(true);
            Modificar.setEnabled(true);
            String info = String.valueOf(Expressions.getSelectedItem());

            int i = 0;
            while(!isWhitespace(info.charAt(i))) ++i;
            int id = Integer.parseInt(info.substring(0, i));

            int codi = _ctrlPresentacio.seleccionarExpressio(id);

            if (codi == -20) {
                VistaDialogo vistaDialogo = new VistaDialogo();
                String[] strBotones = {"Ok"};
                int isel = vistaDialogo.setDialogo(frame, "Error al seleccionar expressió", "Expressió no reconeguda", strBotones, 0);
                System.out.println("Error seleccio exp no reconegut: " + isel + " " + strBotones[isel]);
            }
            System.out.println("Seleccionant expressio " + id + "; " +codi);
        }
        else {
            Buscar.setEnabled(false);
            Modificar.setEnabled(false);
        }
    }

    /**
     * Funció que captura l'acció del botó Buscar i crida a la funció selectPerExpressio del controlador de Presentació
     * @param event acció que es captura al clicar el botó Buscar
     */
    public void actionPerformed_buttonBuscar(ActionEvent event) {
        Resultat.setText("");

        String info = String.valueOf(Expressions.getSelectedItem());
        int i = 0;
        while(!isWhitespace(info.charAt(i))) ++i;
        int id = Integer.parseInt(info.substring(0, i));

        List<Pair<String, String>> res = _ctrlPresentacio.selectPerExpressio(id);
        for (int j = 0; j < res.size(); ++j) {
            Resultat.append(res.get(j).first());
            Resultat.append(" | ");
            Resultat.append(res.get(j).second());
            Resultat.append("\n");
        }
    }

    /**
     * Funció que captura l'acció del botó Modificar i crida a la funció ObrirVistaModificarExpressio del controlador de Presentació
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
        }
        else {
            for (int i = 0; i < resultat.size(); i += 2) {
                Expressions.addItem(resultat.get(i) + " : " + resultat.get(i+1));
            }
            Buscar.setEnabled(true);
            Modificar.setEnabled(true);
        };
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

}
