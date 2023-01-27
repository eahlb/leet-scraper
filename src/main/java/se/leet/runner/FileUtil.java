package se.leet.runner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtil {

    static void createFile(String url, String data) {
        String fileName = System.getProperty("user.home")
                + File.separatorChar
                + url.substring(7).replace('/', File.separatorChar);
        System.out.println("SAVING PAGE: " + fileName);

        var file = new File(fileName);
        try {
            Files.createDirectories(Paths.get(file.getParentFile().getAbsolutePath()));
            Files.writeString(Paths.get(file.getAbsolutePath()), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
