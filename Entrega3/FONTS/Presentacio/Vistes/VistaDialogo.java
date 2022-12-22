package Presentacio.Vistes;

import javax.swing.*;

/**
 * Representa una vista de dialeg modal
 * @author pol.mane.roiger
 * @author isaac.roma.granado
 */
public class VistaDialogo {

  public VistaDialogo() {
  }

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

    // Crea y viisualiza el dialogo
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

