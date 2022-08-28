package com.github.iqpizza6349.cloverytdownloader.frame;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

public class ProgressThread extends Thread {

    private final JLabel resultLabel;
    private CompletableFuture<Boolean> future;

    public ProgressThread(JLabel label) {
        this.resultLabel = label;
    }

    public void setFuture(CompletableFuture<Boolean> future) {
        this.future = future;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            if (future == null) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(37);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                boolean success = future.join();
                if (success) {
                    resultLabel.setText("다운로드 완료");
                }
                else {
                    resultLabel.setText("다운로드 실패");
                }
                future = null;
                resultLabel.setName("INIT");
            }
        }
    }
}
