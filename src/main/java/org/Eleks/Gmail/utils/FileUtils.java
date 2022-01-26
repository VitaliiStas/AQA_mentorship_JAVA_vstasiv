package org.Eleks.Gmail.utils;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class FileUtils {
    public String getAbsolutePath(String relativePath) {
        return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }

    public String getPathToFile(String pathToFile) {
        return String.valueOf(Paths.get(pathToFile));
    }

    public void filesComparing(String pathToFile1, String pathToFile2) {
        File expectedFile = new File(new FileUtils().getPathToFile(pathToFile1));
        File actualFile = new File(new FileUtils().getPathToFile(pathToFile2));
        try {
            Assert.assertEquals(expectedFile.createNewFile(), actualFile.createNewFile(), "The files are different");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
