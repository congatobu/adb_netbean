package com.ephoenix.adb.util;

public class CommonUtils {

	public static void sleep(final long time) {
		
		try {
			Thread.sleep(time);
			
		} catch (InterruptedException ex) {
		}
	}

}
