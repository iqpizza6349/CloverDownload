package com.github.iqpizza6349.cloverytdownloader.core;

public class YTRequest {
    private final String youtubeUrl;
    private final String downloadDirectory;

    public YTRequest(String youtubeUrl, String downloadDirectory)
            throws IllegalArgumentException {
        checkUrl(youtubeUrl);
        this.youtubeUrl = youtubeUrl;
        checkDirectory(downloadDirectory);
        this.downloadDirectory = downloadDirectory;
    }

    public void downloadYT() {
        Utils.sendRequest(youtubeUrl, downloadDirectory);
    }

    private void checkUrl(String url) throws IllegalArgumentException {
        if (!Utils.isUrl(url)) {
            System.out.println("유튜브 주소가 이상합니다.");
            throw new IllegalArgumentException();
        }
    }

    private void checkDirectory(String path) {
        if (!Utils.isDirectory(path)) {
            System.out.println("파일 경로가 이상합니다.");
            throw new IllegalArgumentException();
        }
    }
}
