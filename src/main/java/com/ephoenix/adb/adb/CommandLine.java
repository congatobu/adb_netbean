
package com.ephoenix.adb.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {

	public String executeCommand(String command) {
		System.out.println(command);
		Process process;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			process = Runtime.getRuntime().exec(command);
			
			process.waitFor();
			
			InputStreamReader in = new InputStreamReader(process.getInputStream());
			BufferedReader reader = new BufferedReader(in);
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	public String execute() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

		System.err.println("is windows " + isWindows);
		
		Process p;
		try {
			p = Runtime.getRuntime().exec("D:/workspace_adb/platform-tools/adb.exe devices");

			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
			}
			return line;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
