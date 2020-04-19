package com.ephoenix.adb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.Vector;

/**
 * Utilitities to ease the handling of java.io.File.
 */
public class FilesHelper {

    public static String[] getAllFile(String path, String ext) {

        File f = new File(path);

        FileListFilter filter = new FileListFilter(ext);
        
        String[] paths = f.list(filter);
        
        
        return paths;
        
    }

    /**
     * Creates a new file (and missing parent folders) if given file doesn't
     * exist. If something goes wrong, a thrown IOException is wrapped within a
     * RuntimeException.
     */
    public static void createMissing(File file) {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (java.io.IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    /**
     * Creates a temporary directory. If something goes wrong, a thrown
     * IOException is wrapped within a RuntimeException.
     */
    public static File createTempDirectory() {
        try {
            File directory = File.createTempFile("eportal-util-", "");
            directory.delete();
            directory.mkdirs();
            return directory;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Renames given source to target. If File#renameTo(File) fails, the file
     * content is copied from source to target.
     */
    public static void rename(File source, File target) {
        target.delete();
        if (!source.renameTo(target)) {
            copy(source, target);
            source.delete();
        }
    }

    /**
     * Copies source to target via NIO API. If source is null or doesn't exist,
     * nothing happens.
     */
    public static void copy(File source, File target) {
        if (source != null && source.exists()) {
            try {
                target.createNewFile();
                FileChannel sourceChannel = null;
                FileChannel targetChannel = null;
                try {

                    sourceChannel = new FileInputStream(source).getChannel();
                    targetChannel = new FileOutputStream(target).getChannel();
                    targetChannel.transferFrom(sourceChannel, 0,
                            sourceChannel.size());

                } finally {
                    if (sourceChannel != null) {
                        sourceChannel.close();
                    }
                    if (targetChannel != null) {
                        targetChannel.close();
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(String.format(
                        "Copy file from '%s' to '%s' failed (%s)",
                        source.getAbsolutePath(), target.getAbsolutePath(),
                        e.getMessage()), e);
            }
        }
    }

    /**
     * Returns UTF-8 encoded Reader for given file.
     */
    public static Reader newReader(File file) throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * Returns UTF-8 encoded Writer for given file.
     */
    public static Writer newWriter(File file) throws IOException {
        try {
            return new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     *
     * @param file
     * @return append file write.
     * @throws IOException
     */
    public static Writer appendWrite(File file) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            return new BufferedWriter(fileWriter);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     *
     * @param file
     */
    public static void deleteFileOrDir(File file) {
        if (file.isDirectory()) {
            File[] childs = file.listFiles();
            for (File child : childs) {
                deleteFileOrDir(child);
            }
        }
        file.delete();
    }
}
