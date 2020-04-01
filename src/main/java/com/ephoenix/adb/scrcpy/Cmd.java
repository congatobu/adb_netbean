package com.ephoenix.adb.scrcpy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cmd {

    public static String run(String command){
        String response ="";
        Process p;
        try {
            List<String> cmdList = new ArrayList<String>();
            cmdList.add("cmd");
            cmdList.add("/c");
            cmdList.add(command);
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmdList);
            p = pb.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line+"\n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
}
