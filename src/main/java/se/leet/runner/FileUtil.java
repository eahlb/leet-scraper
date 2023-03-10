package se.leet.runner;

import se.leet.output.OutputHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtil {

    public static void createFile(String url, byte[] data) {
        // Build the file name, making some assumptions on url structure.
        String fileName = System.getProperty("user.home")
                + File.separatorChar
                + url.substring(7).replace('/', File.separatorChar);
        OutputHandler.log("SAVING", fileName);

        // Create file, and if necessary directory.
        var file = new File(fileName);
        try {
            Files.createDirectories(Paths.get(file.getParentFile().getAbsolutePath()));
            Files.write(Paths.get(file.getAbsolutePath()), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
