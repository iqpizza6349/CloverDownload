package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.frame.ResourceUtil;

import javax.swing.*;

public class YoutubeDownload implements Runnable {

    private final JLabel label;

    public YoutubeDownload(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        // noinspection InfiniteLoopStatement
        while (true) {
            if (!ResourceUtil.isTrigger() && !ResourceUtil.isDownloadTrigger()) {
                Thread.yield();
                continue;
            }
            if (ResourceUtil.isDownloadTrigger()) {
                ResourceUtil.switchDownloadTrigger(false);
                boolean success;
                try {
                    success = downloadFile();
                } catch (IllegalArgumentException e) {
                    label.setText("유튜브 경로 혹은 다운로드 경로에 이상이 있습니다.");
                    ResourceUtil.switchTrigger(false);
                    continue;
                }
                if (success) {
                    label.setText("다운로드 완료");
                }
                else {
                    label.setText("문제가 발생했습니다. 다시 시도해주세요");
                    ResourceUtil.switchTrigger(false);
                }
            }
        }
    }

    private boolean downloadFile() throws IllegalArgumentException {
        return new YoutubeRequest(
                ResourceUtil.getYoutubeURL(),
                ResourceUtil.getDownloadPath()
        ).downloadYT();
    }
}
