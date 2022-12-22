package Presentacio.Vistes;

import javax.swing.*;

/**
 * Representa una vista de dialeg modal
 * @author pol.mane.roiger
 * @author isaac.roma.granado
 */
public class VistaDialogo {
  /**
   * Creadora de la VistaDialogo
   */
  public VistaDialogo() {
  }

  /**
   * Funció que inicialitza un dialeg a partir de paràmetres
   * @param frame Frame pare (parentFrame) del diàleg
   * @param strTitulo Títol del diàleg
   * @param strTexto  Text del contingut del diàleg
   * @param strBotones Botons disponibles en el diàleg
   * @param iTipo Tipus de diàleg JOptionPane
   * @return Retorna l'índex del botó seleccionat
   */
  public int setDialogo
    (JFrame frame,String strTitulo, String strTexto, String[] strBotones, int iTipo) {

    int oTipo = 1;
    switch (iTipo) {
      case 0: oTipo = JOptionPane.ERROR_MESSAGE; break;
      case 1: oTipo = JOptionPane.INFORMATION_MESSAGE; break;
      case 2: oTipo = JOptionPane.WARNING_MESSAGE; break;
      case 3: oTipo = JOptionPane.QUESTION_MESSAGE; break;
      case 4: oTipo = JOptionPane.PLAIN_MESSAGE; break;
    }

    JOptionPane optionPane = new JOptionPane(strTexto,oTipo);
    optionPane.setOptions(strBotones);

    JDialog dialogOptionPane = optionPane.createDialog(frame, strTitulo);
    dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialogOptionPane.pack();
    dialogOptionPane.setVisible(true);

    String vsel = (String) optionPane.getValue();
    int isel;
    for (isel = 0;
         isel < strBotones.length && !strBotones[isel].equals(vsel); isel++);
    dialogOptionPane.dispose();
    return isel;
  }

}

