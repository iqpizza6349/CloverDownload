package com.github.iqpizza6349.cloverytdownloader.youtubedl.domain;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;

public class YoutubeRequest {

    private static final UrlValidator VALIDATOR = new UrlValidator();

    private final String youtubeUrl;
    private final String downloadDirectory;

    public YoutubeRequest(String youtubeUrl, String downloadDirectory)
            throws IllegalArgumentException {
        this.youtubeUrl = checkUrl(youtubeUrl);
        checkDirectory(downloadDirectory);
        this.downloadDirectory = downloadDirectory;
    }

    private String checkUrl(String url) throws IllegalArgumentException {
        if (!isUrl(url)) {
            throw new IllegalArgumentException("youtube url is invalid");
        }

        if (url.matches("https://youtube.com/shorts/(.*?)\\?feature=share")) {
            url = url.split("/shorts/")[1]
                    .split("\\?")[0];
        }
        if (url.matches("(.*)&ab_channel=(.*)")) {
            url = url.split("&")[0];
        }

        return url;
    }

    private void checkDirectory(String path) {
        if (!isDirectory(path)) {
            throw new IllegalArgumentException("directory is invalid");
        }
    }

    protected boolean isUrl(String url) {
        return VALIDATOR.isValid(url);
    }

    protected boolean isDirectory(String path) {
        File file = new File(path);
        return (file.exists() && file.isDirectory());
    }

    public YoutubeLink toLink(final String format) {
        final YoutubeOption option = YoutubeOption.builder()
                .audioFormat(format)
                .build();

        // return default youtube option
        return new YoutubeLink(youtubeUrl, downloadDirectory, option);
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }
}
