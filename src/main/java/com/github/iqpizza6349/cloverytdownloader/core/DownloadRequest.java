package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;

public class DownloadRequest {

    private final YoutubeLink link;
    private final String title;
    private final boolean requiredProgress;

    public DownloadRequest(YoutubeLink link, String title, boolean requiredProgress) {
        this.link = link;
        this.title = title;
        this.requiredProgress = requiredProgress;
    }

    public YoutubeLink getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public boolean isRequiredProgress() {
        return requiredProgress;
    }

    @Override
    public String toString() {
        return "DownloadRequest{" +
                "link=" + link +
                ", title='" + title + '\'' +
                ", requiredProgress=" + requiredProgress +
                '}';
    }
}
