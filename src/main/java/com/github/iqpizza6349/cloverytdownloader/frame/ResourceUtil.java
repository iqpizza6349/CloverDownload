package com.github.iqpizza6349.cloverytdownloader.frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class ResourceUtil {

    private ResourceUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static String getYoutubeDLBinPath() {
        URL url = ResourceUtil.class.getProtectionDomain()
                .getCodeSource().getLocation();
        String fullPath = url.toString();
        String[] args = fullPath.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < args.length - 1; i++) {
            stringBuilder.append(args[i])
                    .append("/");
        }
        System.out.println(stringBuilder);
        stringBuilder.append("ffmpeg-win64-gpl");

        return stringBuilder.append("/bin").toString();
    }

    public static Image getImage() {
        ClassLoader classLoader = ResourceUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("icon/folder_icon.png");
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found!");
        }

        try {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
