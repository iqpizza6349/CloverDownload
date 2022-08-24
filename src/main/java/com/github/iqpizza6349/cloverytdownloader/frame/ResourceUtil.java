package com.github.iqpizza6349.cloverytdownloader.frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class ResourceUtil {

    public static final String BLANK = "                     ";
    private static boolean trigger = false;
    private static boolean downloadTrigger = false;
    private static int currentProgress = 0;
    private static String youtubeURL = "";
    private static String downloadPath = "";

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

    public static String getYoutubeURL() {
        return youtubeURL;
    }

    public static void setYoutubeURL(String youtubeURL) {
        ResourceUtil.youtubeURL = youtubeURL;
    }

    public static String getDownloadPath() {
        return downloadPath;
    }

    public static void setDownloadPath(String downloadPath) {
        ResourceUtil.downloadPath = downloadPath;
    }

    public static int getCurrentProgress() {
        return currentProgress;
    }

    public static void updateCurrentProgress(int currentProgress) {
        ResourceUtil.currentProgress = currentProgress;
    }

    public static void initializationProgress() {
        ResourceUtil.currentProgress = 0;
    }

    public static void switchTrigger(boolean trigger) {
        ResourceUtil.trigger = trigger;
    }

    public static void switchDownloadTrigger(boolean downloadTrigger) {
        ResourceUtil.downloadTrigger = downloadTrigger;
    }

    public static boolean isTrigger() {
        return trigger;
    }

    public static boolean isDownloadTrigger() {
        return downloadTrigger;
    }
}
