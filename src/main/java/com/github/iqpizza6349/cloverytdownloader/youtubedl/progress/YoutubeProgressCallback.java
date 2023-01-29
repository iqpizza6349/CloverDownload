package com.github.iqpizza6349.cloverytdownloader.youtubedl.progress;

@FunctionalInterface
public interface YoutubeProgressCallback extends YoutubeDownloadCallback {

    void onProgressUpdate(float progress, long etaInSeconds);

    @Override
    default void onProgressUpdate(String title, float progress, long etaInSeconds, int currentIndex, int totalIndex) {
        onProgressUpdate(progress, etaInSeconds);
    }
}
