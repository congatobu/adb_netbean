package com.ephoenix.adb.scrcpy;

import com.ephoenix.adb.Application;

public class ScrcpyWorker implements Runnable {

    private boolean isRunning = false;
    
    private String id = "";
    
    private long delay = 0;

    public ScrcpyWorker(String id) {
        this.id = id;
    }

    
    
    public void run() {

        Application.log("Start srcrpy device " + id);
        
        Cmd.run("D:\\workspace_adb\\scrcpy\\scrcpy.exe");
        
        Application.log("Stop srcrpy device " + id);
    }

    public void start() {

        final Thread thread = new Thread(this);

        thread.start();
    }

    public void stop() {
        this.isRunning = false;
    }

    public void setRunning(boolean isRunning, long delay) {

        this.delay = delay;

        this.isRunning = isRunning;

    }

}
