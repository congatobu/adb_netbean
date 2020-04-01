package com.ephoenix.adb.scrcpy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {

    private JButton button1;
    private JTextField textField1;
    private JButton adb;
    private JScrollPane connDevices;
    private JTextArea textArea1;

    public GUI() throws IOException {
        button1.addActionListener(new StartListener());
        adb.addActionListener(new ConnectEventListener());

    }

    public static void main(String[] args) {
        // Scrcpy.runScrcpy();
        JPanel panel = new JPanel();
        
        JFrame frame = new JFrame("Scrcpy GUI");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    
        Scrcpy.runScrcpy();
    }

    private class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Scrcpy.runScrcpy();
        }
    }

    private class ConnectEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Scrcpy.connect(textField1.getText());
        }
    }
}
