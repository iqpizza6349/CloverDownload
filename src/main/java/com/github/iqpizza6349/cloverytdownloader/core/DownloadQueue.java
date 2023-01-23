package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;

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

    private final Queue<YoutubeLink> youtubeLinkQueue = new ConcurrentLinkedDeque<>();

    public void add(YoutubeLink link) {
        youtubeLinkQueue.offer(link);
    }

    public YoutubeLink getElement() {
        return youtubeLinkQueue.poll();
    }

    public YoutubeLink peek() {
        return youtubeLinkQueue.peek();
    }

}
