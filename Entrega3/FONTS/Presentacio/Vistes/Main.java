package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    CtrlPresentacio _ctrlPresentacio = new CtrlPresentacio();
                    _ctrlPresentacio.iniPresentacio();
                    //JFrame frame = new VistaCrearDirectori();
                }
        });
    }
}
