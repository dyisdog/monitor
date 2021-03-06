package com.support.monitor.commons.binder.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    private FileUtils() {
    }

    public static File[] listFiles(final File path, final List<String> fileExtensionList) {
        if (path == null) {
            throw new NullPointerException("path must not be null");
        }
        if (fileExtensionList == null) {
            throw new NullPointerException("fileExtensionList must not be null");
        }
        return path.listFiles(pathname -> {
            String path1 = pathname.getName();
            for (String extension : fileExtensionList) {
                return StringUtils.endsWithIgnoreCase(path1, extension);

            }
            return false;
        });
    }


    public static boolean isEmpty(File[] files) {
        return files == null || files.length == 0;
    }


    public static String toCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }


}
