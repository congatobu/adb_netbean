package com.ephoenix.adb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Application {

    private static JDesktopPane desktop;

    private JFrame frame;

    private static JTextArea textArea = new JTextArea();

    private DevicesFrame devices = new DevicesFrame();

    private AddDevicesFrame addDevicesFrame = new AddDevicesFrame();

    private static HashMap<String, String> devicesFrames = new HashMap<String, String>();

    private static int ids = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application window = new Application();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Application() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();

        // ---------------------------------------------
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -----------------------------------------------------
        desktop = new JDesktopPane();

        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        frame.getContentPane().setLayout(new BorderLayout(1, 1));

        // -----------------------------------------------
        frame.getContentPane().add(desktop, BorderLayout.CENTER);
        // ---------------------------------------------------

        textArea.setEditable(false);

        textArea.setRows(10);

        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

        // --------------------------------------------
        frame.setJMenuBar(createMenuBar());

        frame.pack();
        frame.setVisible(true);

        createDeviceFrame();

    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Set up the lone menu.
        JMenu document = new JMenu("Hệ thống");
        document.setMnemonic(KeyEvent.VK_D);

        // Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("Thiết bị");
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("devices");
        menuItem.addActionListener(new MyAction());
        document.add(menuItem);

        // Set up the first menu item.
        menuItem = new JMenuItem("Thêm mới");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(new MyAction());
        document.add(menuItem);

        // Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(new MyAction());
        document.add(menuItem);

        // -----------------------------------------------------------
        JMenu setup = new JMenu("Cài đặt");
        setup.setMnemonic(KeyEvent.VK_S);

        JMenuItem clearLog = new JMenuItem("Xóa logs");
        clearLog.setMnemonic(KeyEvent.VK_C);
        clearLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        clearLog.setActionCommand("clear");
        clearLog.addActionListener(new MyAction());

        setup.add(clearLog);

        // -----------------------------------------------------------
        menuBar.add(document);
        menuBar.add(setup);

        return menuBar;
    }

    class MyAction implements ActionListener {

        // React to menu selections.
        public void actionPerformed(ActionEvent e) {
            
            if ("new".equals(e.getActionCommand())) { // new
                addNewDeviceFrame();
            }
            
            if ("devices".equals(e.getActionCommand())) { // new
                createDeviceFrame();
            }
            if ("clear".equals(e.getActionCommand())) { // new
                clearLog();
            }
        }

    }

    protected void addNewDeviceFrame() {

        if (!addDevicesFrame.isVisible()) {

            addDevicesFrame.setVisible(true);

            desktop.add(addDevicesFrame);

            try {

                addDevicesFrame.setSelected(true);

            } catch (java.beans.PropertyVetoException e) {

            }
        }
    }

    protected void createDeviceFrame() {

        if (!devices.isVisible()) {

            devices.setVisible(true);

            desktop.add(devices);

            try {

                devices.setSelected(true);

            } catch (java.beans.PropertyVetoException e) {

            }
        }
    }

    public static void createFrame(String name, String device) {

        if (devicesFrames.containsKey(device)) {
            return;
        }

        addDevice(device, name);

        ids = ids + 1;

        int id = ids;

        DeviceDetailsFrame mframe = new DeviceDetailsFrame(name, device, id);

        mframe.setVisible(true); // necessary as of 1.3

        desktop.add(mframe);

        try {

            mframe.setSelected(true);

        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public static void clearLog() {

        try {
            System.err.println("Xóa log hệ thống");
            Application.textArea.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {

        try {

            Application.textArea.append(message + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDevice(String device, String name) {
        devicesFrames.put(device, name);
    }

    public static void removeDevice(String device) {

        if (devicesFrames.containsKey(device)) {
            devicesFrames.remove(device);
        }

    }

}
