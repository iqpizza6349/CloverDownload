package com.github.iqpizza6349.cloverytdownloader.youtubedl.progress;

@FunctionalInterface
public interface YoutubeDownloadCallback {

    void onProgressUpdate(String title, float progress, long etaInSeconds, int currentIndex, int totalIndex);

}
