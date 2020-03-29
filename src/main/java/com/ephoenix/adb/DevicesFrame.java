package com.ephoenix.adb;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.ephoenix.adb.adb.ADB;
import com.ephoenix.adb.adb.ADBParser;
import com.ephoenix.adb.adb.CommandLine;

public class DevicesFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	

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
		CommandLine commandLine = new CommandLine();

		ADBParser adbParser = new ADBParser();

		ADB adb = new ADB(commandLine, adbParser);

		Collection<Device> devices = adb.getDevicesConnected();

		Object data[][] = new Object[devices.size()][4];

		String column[] = { "TÊN MÁY", "ID", "LOẠI MÁY", "STATUS" };

		Application.log("adb run");

		int i = 0;
		for (Device device : devices) {

			data[i][0] = device.getName();
			data[i][1] = device.getId();
			data[i][2] = device.getType();

			if (device.isConnected()) {

				data[i][3] = "Kết Nối";

			} else {

				data[i][3] = "Mất Kết Nối";

			}

			i++;
		}

		table = new JTable();

//		table.setCellSelectionEnabled(true);

		DefaultTableModel tm = new DefaultTableModel(data, column);

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

				}
			}

		});

		JScrollPane pane = new JScrollPane(table);
		return pane;
	}

	@Override
	public void dispose() {

		super.dispose();
		
		
	}

}
