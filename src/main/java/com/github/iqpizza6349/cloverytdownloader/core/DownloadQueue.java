package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DownloadQueue {

    private static DownloadQueue DOWNLOAD_QUEUE;

    public static DownloadQueue getInstance() {
        if (DOWNLOAD_QUEUE == null) {
            DOWNLOAD_QUEUE = new DownloadQueue();
        }
        return DOWNLOAD_QUEUE;
    }

    private final Queue<DownloadRequest> requests = new ConcurrentLinkedDeque<>();

    public Queue<DownloadRequest> getRequests() {
        return requests;
    }

    public void add(YoutubeLink link) {
        add(link, null);
    }

    public void add(YoutubeLink link, YoutubeDownloadCallback callback) {
        requests.offer(new DownloadRequest(link, callback));
    }

    public DownloadRequest getElement() {
        return requests.poll();
    }

    public DownloadRequest peek() {
        return requests.peek();
    }

}
