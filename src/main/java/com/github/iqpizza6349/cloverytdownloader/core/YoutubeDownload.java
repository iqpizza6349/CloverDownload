package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.core.execute.YoutubeDownloadExecutor;

import java.util.concurrent.CompletableFuture;

public class YoutubeDownload {

    private final YoutubeDownloadExecutor downloadExecutor;

    public YoutubeDownload() {
        this.downloadExecutor = new YoutubeDownloadExecutor(10);
    }

    public CompletableFuture<Boolean> downloadYoutube(
            YoutubeRequest youtubeRequest) {
        return CompletableFuture.supplyAsync(
                youtubeRequest::downloadYT, downloadExecutor.getExecutor()
        );
    }
}
