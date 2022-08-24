package com.github.iqpizza6349.cloverytdownloader.frame.progress;

import com.github.iqpizza6349.cloverytdownloader.frame.ResourceUtil;

import javax.swing.*;

public class YoutubeProgress implements Runnable {

    private final JProgressBar progressBar;

    public YoutubeProgress(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        // noinspection InfiniteLoopStatement
        while (true) {
            if (!ResourceUtil.isTrigger() && !ResourceUtil.isDownloadTrigger()) {
                Thread.yield();
            }
            else {
                if (!progressBar.isVisible()) {
                    progressBar.setVisible(true);
                }

                progressBar.setValue(ResourceUtil.getCurrentProgress());
                try {
                    // noinspection BusyWait
                    Thread.sleep(37);
                } catch (InterruptedException ignored) {}

                if (ResourceUtil.getCurrentProgress() >= 100) {
                    progressBar.setValue(100);
                    ResourceUtil.switchTrigger(false);
                }
            }
        }
    }
}
