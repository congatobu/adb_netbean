package com.ephoenix.adb.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Utils {

    public static LinkedList<String> readFile(String content) {
        String line = "";
        LinkedList<String> result = new LinkedList<String>();
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;
        BufferedReader bufferedReader = null;
        File file = new File(content);
        try {
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);
            try {

                bufferedReader = new BufferedReader(new InputStreamReader(
                        dataInputStream, "UTF-8"));

                boolean flag = true;

                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();

                    if (line.matches("#START.*")) {
                        break;
                    }

                    if (flag) {
                        if (!(line.startsWith("#")) && (line.length() > 0)) {
                            result.addFirst(line);
                            flag = false;
                        } else {
                            result.add(line);
                        }

                    } else {
                        result.add(line);
                    }

                }
            } finally {
                bufferedReader.close();
            }

            dataInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int writeResultFile(String errorFile, String line, String result) {

        File file = new File(errorFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Writer writer = FilesHelper.newWriter(file);

                if (line.equals("") || line.length() == 0) {
                    writer.write(line + result
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(line + ":" + result
                            + System.getProperty("line.separator"));

                }

                writer.flush();
                writer.close();
            } catch (java.io.IOException e) {

            }
        } else {
            try {

                Writer writer = FilesHelper.appendWrite(file);

                if (line.equals("") || line.length() == 0) {
                    writer.write(line + result
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(line + ":" + result
                            + System.getProperty("line.separator"));

                }

                writer.flush();
                writer.close();
            } catch (IOException e) {

            }
        }
        return 0;
    }

    public static String[] validText(String text) {
        String[] temp;
        String delimiter = ";";
        temp = text.split(delimiter);

        return temp;
    }

    public static String createUrl(String template,
            HashMap<String, String> params) {

        if (params == null) {
            return template;
        }
        if (params.isEmpty()) {
            return template;
        }

        for (Iterator<String> names = params.keySet().iterator(); names
                .hasNext();) {
            String name = (String) names.next();
            template = template.replaceAll("\\%" + name,
                    params.get(name));
        }

        return template.trim();
    }
}
