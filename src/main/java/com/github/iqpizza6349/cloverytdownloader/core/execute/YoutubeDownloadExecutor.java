package com.github.iqpizza6349.cloverytdownloader.core.execute;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class YoutubeDownloadExecutor {
    private final Executor executor;

    public YoutubeDownloadExecutor(final int requestCount) {
        this.executor = initializeExecutor(requestCount);
    }

    private Executor initializeExecutor(int count) {
        return Executors.newFixedThreadPool(
                Math.min(count, 25), (runnable) -> {
                    Thread thread = new Thread(runnable);
                    thread.setDaemon(true);
                    return thread;
                }
        );
    }

    public Executor getExecutor() {
        return executor;
    }
}
