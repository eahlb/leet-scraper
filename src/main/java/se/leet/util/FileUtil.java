package se.leet.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static void createFile(String url, byte[] data) {
        String fileName = System.getProperty("user.home")
                + File.separatorChar
                + url.substring(7).replace('/', File.separatorChar);
        System.out.println("SAVING PAGE: " + fileName);

        var file = new File(fileName);
        try {
            Files.createDirectories(Paths.get(file.getParentFile().getAbsolutePath()));
            Files.write(Paths.get(file.getAbsolutePath()), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
