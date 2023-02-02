package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;

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

    public synchronized void add(YoutubeLink link, String title) {
        if (!requests.isEmpty()) {
            if (findSameTitle(link.getUrl(), title)) {
                return;
            }
        }

        requests.offer(new DownloadRequest(link, title, (title != null)));
    }

    public synchronized DownloadRequest getElement() {
        return requests.poll();
    }

    public synchronized DownloadRequest peek() {
        return requests.peek();
    }

    protected synchronized boolean findSameTitle(final String link, final String title) {
        return requests.stream()
                .anyMatch(
                        downloadRequest -> downloadRequest.getTitle().equalsIgnoreCase(title)
                        && downloadRequest.getLink().getUrl().equalsIgnoreCase(link)
                );
    }

}
