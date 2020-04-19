package com.ephoenix.adb.adb;

import com.ephoenix.adb.Application;
import com.ephoenix.adb.Device;
import com.ephoenix.adb.DevicesFrame;
import com.ephoenix.adb.util.CommonUtils;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;

public class ADBWorker implements Runnable {

    private boolean isRunning = false;


    public void run() {

        while (true) {


            try {
                if (this.isRunning) {

                    Application.log("ADB WORKER RUNING");
                    CommandLine commandLine = new CommandLine();

                    ADBParser adbParser = new ADBParser();

                    ADB adb = new ADB(commandLine, adbParser);

                    Collection<Device> devices = adb.getDevicesConnected();

                    DefaultTableModel tm = (DefaultTableModel) DevicesFrame.table.getModel();

                    tm.setRowCount(0);

                    for (Device device : devices) {

                        String data[] = new String[4];

                        data[0] = device.getName();
                        data[1] = device.getId();
                        data[2] = device.getType();

                        if (device.isConnected()) {

                            data[3] = "Kết Nối";

                        } else {

                            data[3] = "Mất Kết Nối";

                        }

                        tm.addRow(data);

                    }
                    
                    DevicesFrame.table.setModel(tm);
                   
                    tm.fireTableDataChanged();

                }
            } catch (Exception e) {
                Application.log("ADB WORKER STOPED");
            }

            CommonUtils.sleep(30000);

        }

    }

    public void start() {
        this.isRunning = true;
        final Thread thread = new Thread(this);

        thread.start();
    }

    public void stop() {
        this.isRunning = false;
    }

}
