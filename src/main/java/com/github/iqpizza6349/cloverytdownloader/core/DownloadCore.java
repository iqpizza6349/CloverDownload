package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.core.task.DownloadFutureTask;
import com.github.iqpizza6349.cloverytdownloader.frame.component.bar.DownloadProgressBar;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
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
            final String hexCode = request.getHexCode();

            EXECUTOR.execute(task(link, callback, hexCode));
        }
    }

    private DownloadFutureTask task(final YoutubeLink link, final YoutubeDownloadCallback callback,
                                    final String hexCode) {
        // download progress bar 들이 사용 중이라고 알려주는 하나의, 일종의 저장소가 있다면
        // 쉽게 접근할 수 있다.
        DownloadProgressBar progressBar = ComponentUtil.findProgressBarByHexCode(hexCode);

        return new DownloadFutureTask(() -> {
            return DOWNLOAD_PROCESSOR.execute(link, callback);
        }, youtubeResponse -> {
            // if download is success, check-mark must be shown.
            // TODO: 2023-01-26 find progress bar
            System.out.println("success");
        }, throwable -> {
            // if download is failed, fail-mark must be shown.
            System.out.println("failure");
        }, () -> {
            // FINALLY: progress-bar should be initialized.
            System.out.println("finally");
            progressBar.setTitle(null);
            progressBar.etaUpdate(0);
            progressBar.setValue(0);
        });
    }

}
