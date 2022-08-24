package com.github.iqpizza6349.cloverytdownloader.youtubedl;

public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}