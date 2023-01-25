package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownloadCore extends Thread {

    private static final DownloadQueue QUEUE = DownloadQueue.getInstance();
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);
    private static final YoutubeDL DOWNLOAD_PROCESSOR = new YoutubeDL();

    public DownloadCore() {
        super.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //noinspection BusyWait
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
                // TODO: 2023-01-24 handle this exception
            }

            if (QUEUE.getRequests().isEmpty()) {
                return;
            }

            final DownloadRequest request;
            synchronized (QUEUE) {
                request = QUEUE.getElement();
            }
            assert (request != null);

            EXECUTOR.execute(() -> {
                try {
                    DOWNLOAD_PROCESSOR.execute(request.getLink(), request.getCallback());
                } catch (YoutubeException ignored) {
                    /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
                    // TODO: 2023-01-24 handle this exception
                }
            });
        }
    }
}
