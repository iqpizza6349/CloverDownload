package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.core.execute.YoutubeDownloadExecutor;
import com.github.iqpizza6349.cloverytdownloader.frame.ResourceUtil;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

public class YoutubeDownload {

    private final JProgressBar progressBar;
    private final YoutubeDownloadExecutor downloadExecutor;

    public YoutubeDownload(JProgressBar progressBar) {
        this.progressBar = progressBar;
        this.downloadExecutor = new YoutubeDownloadExecutor(10);
    }

    public CompletableFuture<Boolean> downloadYoutube() throws IllegalArgumentException {
        return CompletableFuture.supplyAsync(
                this::downloadFile, downloadExecutor.getExecutor()
        );
    }

    private boolean downloadFile() throws IllegalArgumentException {
        return new YoutubeRequest(
                ResourceUtil.getYoutubeURL(),
                ResourceUtil.getDownloadPath(),
                progressBar
        ).downloadYT();
    }
}
