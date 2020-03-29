package com.ephoenix.adb;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class DeviceDetails extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = "";

	private int id = -1;

	private String device = "";

	private static JTextArea textArea = new JTextArea();

	public DeviceDetails(String name, String device, int id) {
		super(name + " - " + device, true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		this.name = name;

		this.id = id;

		this.device = device;

		setSize(300, 300);

		setLayout(new BorderLayout(1, 1));

		textArea.setEditable(false);

		textArea.setRows(10);

		JScrollPane scrollPane = new JScrollPane(textArea);

		getContentPane().add(scrollPane, BorderLayout.SOUTH);
		
		setJMenuBar(createMenuBar());
	
	}

	
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Set up the lone menu.
		JMenu document = new JMenu("Hệ thống");
		document.setMnemonic(KeyEvent.VK_D);

		// Set up the first menu item.
		JMenuItem menuItem = new JMenuItem("Thiết bị");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("devices");
		menuItem.addActionListener(new Action());
		document.add(menuItem);

		// Set up the second menu item.
		menuItem = new JMenuItem("Quit");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(new Action());
		document.add(menuItem);

		// -----------------------------------------------------------

		JMenu setup = new JMenu("Cài đặt");
		setup.setMnemonic(KeyEvent.VK_S);

		JMenuItem clearLog = new JMenuItem("Xóa logs");
		clearLog.setMnemonic(KeyEvent.VK_C);
		clearLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		clearLog.setActionCommand("clear");
		clearLog.addActionListener(new Action());

		setup.add(clearLog);

		// -----------------------------------------------------------

		menuBar.add(document);
		menuBar.add(setup);

		return menuBar;
	}

	class Action implements ActionListener {

		// React to menu selections.
		public void actionPerformed(ActionEvent e) {

			
		}

	}
	
	@Override
	public void dispose() {

		Application.removeDevice(device);

		super.dispose();
	}

}
