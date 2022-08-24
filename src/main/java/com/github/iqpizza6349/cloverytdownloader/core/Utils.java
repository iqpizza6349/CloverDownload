package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.frame.ResourceUtil;
import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;

public class Utils {

    private static final UrlValidator URL_VALIDATOR = new UrlValidator();

    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static boolean isUrl(String url) {
        return URL_VALIDATOR.isValid(url);
    }

    public static boolean isDirectory(String path) {
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    public static boolean sendRequest(final String videoUrl,
                                      final String directory) {
        YoutubeDLRequest request = new YoutubeDLRequest(
                videoUrl, directory
        );
        request.setOption("extract-audio");
        request.setOption("audio-format", "mp3");
        request.setOption("output", "\"%(title)s.%(ext)s\"");
        request.setOption("ignore-errors");
        request.setOption("retries", 10);

        try {
            YoutubeDL.setExecutablePath(ResourceUtil.getYoutubeDLBinPath() + "/youtube-dl");
            YoutubeDL.execute(request, (progress, etaInSeconds) -> {
                ResourceUtil.updateCurrentProgress((int) progress);
            });
            return true;
        } catch (YoutubeDLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
