package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.CloverComponent;
import com.github.iqpizza6349.cloverytdownloader.constant.ExitCode;
import com.github.iqpizza6349.cloverytdownloader.core.exceptions.NoInitializedProgressBarException;
import com.github.iqpizza6349.cloverytdownloader.core.task.DownloadFutureTask;
import com.github.iqpizza6349.cloverytdownloader.frame.component.bar.DownloadProgressBar;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.progress.YoutubeDownloadCallback;

import javax.swing.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownloadCore extends Thread implements CloverComponent {

    private static final DownloadQueue QUEUE = DownloadQueue.getInstance();
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);
    private static final YoutubeDL DOWNLOAD_PROCESSOR = new YoutubeDL();

    public DownloadCore() {
        super.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            EXIT_MANAGER.occurredExit(ExitCode.INTERRUPTED);
        }

        final DownloadRequest request;
        final DownloadProgressBar progressBar;
        try {
            progressBar = findInitializedProgressBar();
        } catch (NoInitializedProgressBarException e) {
            return;
        }

        synchronized (this) {
            if (QUEUE.getRequests().isEmpty() || QUEUE.peek() == null) {
                return;
            }

            request = QUEUE.getElement();
        }
        if (request == null) {
            return;
        }

        final YoutubeLink link = request.getLink();
        final YoutubeDownloadCallback callback = callback(progressBar);

        if (ComponentUtil.checkDuplicateRequest(request.getTitle())) {
            EXIT_MANAGER.occurredExit(ExitCode.DUPLICATE_REQUEST);
            return;
        }

        initializeProgressBar(progressBar, request.getTitle());
        EXECUTOR.execute(task(link, callback, progressBar));
    }

    private synchronized DownloadProgressBar findInitializedProgressBar() throws
            NoInitializedProgressBarException {
        try {
            return ComponentUtil.getAvailableProgressBar();
        } catch (ClassCastException e) {
            throw new NoInitializedProgressBarException();
        }
    }

    private synchronized YoutubeDownloadCallback callback(final DownloadProgressBar progressBar) {
        return (title, progress, etaInSeconds, currentIndex, totalIndex) -> {
            if (!progressBar.isVisible()) {
                progressBar.setVisible(true);
            }

            progressBar.setTitle(shortString(title));
            progressBar.percentUpdate((int) progress);
            if (currentIndex == -1 || totalIndex == -1) {
                progressBar.etaUpdate(etaInSeconds);
            }
            else {
                progressBar.playlistUpdate(etaInSeconds, currentIndex, totalIndex);
            }
        };
    }

    private synchronized void initializeProgressBar(final DownloadProgressBar progressBar,
                                                    final String title) {
        progressBar.setValue(0);
        progressBar.setTitle(shortString(title));
        ComponentUtil.useDownloadProgressBar(progressBar);
    }

    protected String shortString(final String s) {
        return (s.trim().length() >= 40) ? s.substring(0, 30) + "..." : s;
    }

    private DownloadFutureTask task(final YoutubeLink link, final YoutubeDownloadCallback callback,
                                    final DownloadProgressBar progressBar) {
        return new DownloadFutureTask(() -> {
            return DOWNLOAD_PROCESSOR.execute(link, callback);
        }, youtubeResponse -> {
            // if download is success, check-mark must be shown.
            ComponentUtil.showDiagram("SUCCESS", "download success", JOptionPane.INFORMATION_MESSAGE);
        }, throwable -> {
            // if download is failed, fail-mark must be shown.
            EXIT_MANAGER.occurredExit(ExitCode.DOWNLOAD_FAILED);
        }, () -> {
            progressBar.setTitle(null);
            progressBar.etaUpdate(0);
            progressBar.setValue(0);
            progressBar.setVisible(false);
            ComponentUtil.returnProgressBar(progressBar);
        });
    }
}
