package com.github.iqpizza6349.cloverytdownloader.core;

public class YoutubeRequest {
    private final String youtubeUrl;
    private final String downloadDirectory;

    public YoutubeRequest(String youtubeUrl, String downloadDirectory)
            throws IllegalArgumentException {
        checkUrl(youtubeUrl);
        this.youtubeUrl = youtubeUrl;
        checkDirectory(downloadDirectory);
        this.downloadDirectory = downloadDirectory;
    }

    public boolean downloadYT() {
        return Utils.sendRequest(youtubeUrl, downloadDirectory);
    }

    private void checkUrl(String url) throws IllegalArgumentException {
        if (!Utils.isUrl(url)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkDirectory(String path) {
        if (!Utils.isDirectory(path)) {
            throw new IllegalArgumentException();
        }
    }
}
