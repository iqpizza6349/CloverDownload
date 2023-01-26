package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
                // TODO: 2023-01-24 handle this exception
                e.printStackTrace();
            }

            final DownloadRequest request;

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
            final YoutubeDownloadCallback callback = request.getCallback();

            EXECUTOR.execute(() -> {
                try {
                     DOWNLOAD_PROCESSOR.execute(link, callback);
                } catch (YoutubeException e) {
                    /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
                    // TODO: 2023-01-24 handle this exception
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
