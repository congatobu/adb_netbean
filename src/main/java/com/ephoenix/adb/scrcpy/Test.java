package com.ephoenix.adb.scrcpy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args){
        Process p;
        try {
            List<String> cmdList = new ArrayList<String>();
            cmdList.add("cmd");
            cmdList.add("/c");
            cmdList.add("C:\\scrcpy\\adb connect 37.159.108.5");
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmdList);
            p = pb.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            List<String> cmdList = new ArrayList<String>();
            cmdList.add("cmd");
            cmdList.add("/c");
            cmdList.add("C:\\scrcpy\\scrcpy.exe");
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(cmdList);
            p = pb.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
