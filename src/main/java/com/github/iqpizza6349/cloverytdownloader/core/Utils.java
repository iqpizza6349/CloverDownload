package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.frame.ResourceUtil;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDLException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDLRequest;
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
        request.setOption("format", "\"bestaudio/best[height<=?720]\"");
        request.setOption("no-cache-dir");
        request.setOption("no-mtime");
        request.setOption("extract-audio");
        request.setOption("audio-format", "mp3");
        request.setOption("output", "\"%(title)s.%(ext)s\"");
        request.setOption("hls-prefer-native");
        request.setOption("audio-quality", 0);
        request.setOption("add-metadata");
        request.setOption("embed-thumbnail");
        request.setOption("ignore-errors");
        request.setOption("continue");
        request.setOption("retries", 10);

        try {
            YoutubeDL.setExecutablePath(ResourceUtil.getYoutubeDLBinPath() + "/youtube-dl");
            YoutubeDL.execute(
                    request,
                    (progress, etaInSeconds) -> ResourceUtil.updateCurrentProgress((int) progress)
            );
            return true;
        } catch (YoutubeDLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
