package com.ephoenix.adb.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileListFilter implements FilenameFilter {
	private String extension;

	public FileListFilter(String extension) {
		this.extension = extension;
	}

	/*
	 * filter file (non-Javadoc)
	 * 
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File directory, String filename) {
		boolean fileOK = true;
		if (extension != null) {
			fileOK &= filename.endsWith('.' + extension);
		}
		return fileOK;
	}
}
