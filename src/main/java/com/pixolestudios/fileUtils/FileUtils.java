package main.java.com.pixolestudios.fileUtils;

import java.io.File;

public class FileUtils {

    private FileUtils() {
    }

    /**
     * Makes all the required directories up to the path provided
     *
     * @param path to which directories to be created
     * @return true if successfully creates directories
     */
    public static boolean mkdirs(String path) {
        File file = new File(path);
        return file.getParentFile().mkdirs();
    }
}
