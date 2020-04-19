package com.ephoenix.adb;

import com.ephoenix.adb.scrcpy.Scrcpy;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DevicesFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JTable table;
	

	public DevicesFrame() {
		super("Danh sách thiết bị", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		// ...Create the GUI and put it in the window...

		// ...Then set the window size or call pack...
		setSize(800, 800);
		
		
		
		// -----------------------------------------
		
		add(getTable());
		
		try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			
			e.printStackTrace();
		}

	}

	public JScrollPane getTable() {
		

		String column[] = { "TÊN MÁY", "ID", "LOẠI MÁY", "STATUS" };

		Application.log("adb run");

		

		table = new JTable();

		DefaultTableModel tm = (DefaultTableModel) table.getModel();
                
                tm.setColumnIdentifiers(column);
		
                table.setModel(tm);

		table.setFillsViewportHeight(true);

		ListSelectionModel select = table.getSelectionModel();

		select.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				int row = table.getSelectedRow();

				if (row >= 0) {

					Application.log("Table element selected is: " + row);

					String name = (String) table.getValueAt(row, 0);

					String id = (String) table.getValueAt(row, 1);

					Application.log(name + " - " + id);

					Application.createFrame(name, id);

					table.clearSelection();
                                        
                                        runScrcpy(id);
                                        
				}
			}

		});

		JScrollPane pane = new JScrollPane(table);
		return pane;
	}
        
        public void runScrcpy(String id){
            Scrcpy.runScrcpy(id);
        }

	@Override
	public void dispose() {

		super.dispose();
		
		
	}

}
