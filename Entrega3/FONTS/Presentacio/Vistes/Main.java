package Presentacio.Vistes;

import Presentacio.Controladors.CtrlPresentacio;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run() {
                    CtrlPresentacio _ctrlPresentacio = new CtrlPresentacio();
                    _ctrlPresentacio.iniPresentacio();
                }
        });
    }
}
