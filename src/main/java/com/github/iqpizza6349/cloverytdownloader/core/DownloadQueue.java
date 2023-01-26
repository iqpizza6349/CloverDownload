package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DownloadQueue {

    private static DownloadQueue DOWNLOAD_QUEUE;

    public static DownloadQueue getInstance() {
        if (DOWNLOAD_QUEUE == null) {
            DOWNLOAD_QUEUE = new DownloadQueue();
        }
        return DOWNLOAD_QUEUE;
    }

    private final Queue<DownloadRequest> requests = new ConcurrentLinkedQueue<>();

    public synchronized Queue<DownloadRequest> getRequests() {
        return requests;
    }

    public synchronized void add(YoutubeLink link) {
        add(link, null);
    }

    public synchronized void add(YoutubeLink link, YoutubeDownloadCallback callback) {
        add(link, callback, null);
    }

    public synchronized void add(YoutubeLink link, YoutubeDownloadCallback callback,
                                 CustomComponent customComponent) {
        if (customComponent != null) {
            if (!(customComponent instanceof JProgressBar)) {
                return;
            }
        }

        requests.offer(new DownloadRequest(link, callback, customComponent));
    }

    public synchronized DownloadRequest getElement() {
        return requests.poll();
    }

    public synchronized DownloadRequest peek() {
        return requests.peek();
    }

}
