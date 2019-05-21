package main.java.com.pixolestudios.fileUtils;

import java.io.File;

public class FileUtils {

    public static boolean mkdirs(String path) {
        File file = new File(path);
        return file.getParentFile().mkdirs();
    }
}
