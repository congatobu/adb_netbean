

package com.ephoenix.adb.window;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ConnectDisconnectRenderer extends JPanel implements TableCellRenderer {
  private final ConnectDisconnectPanel connectDisconnectPane = new ConnectDisconnectPanel();

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus,
      int row, int column) {
    if (isSelected) {
      connectDisconnectPane.setBackground(table.getSelectionBackground());
    } else {
      connectDisconnectPane.setBackground(table.getBackground());
    }
    return connectDisconnectPane;
  }
}