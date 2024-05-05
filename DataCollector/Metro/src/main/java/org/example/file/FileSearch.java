package org.example.file;

import java.io.File;

public class FileSearch {
    public static void main(String[] args) {
        String path = "data";
        File directory = new File(path);
        searchFiles(directory);
    }

    public static void searchFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFiles(file);
                    } else {
                        String fileName = file.getName();
                        if (fileName.endsWith(".json") || fileName.endsWith(".csv")) {
                            System.out.println(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
