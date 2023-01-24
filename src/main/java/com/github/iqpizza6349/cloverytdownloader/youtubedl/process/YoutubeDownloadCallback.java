package com.github.iqpizza6349.cloverytdownloader.youtubedl.process;

@FunctionalInterface
public interface YoutubeDownloadCallback {

    void onProgressUpdate(float progress, long etaInSeconds);

}
