package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;

public class DownloadRequest {

    private final YoutubeLink link;
    private final YoutubeDownloadCallback callback;

    public DownloadRequest(YoutubeLink link, YoutubeDownloadCallback callback) {
        this.link = link;
        this.callback = callback;
    }

    public YoutubeLink getLink() {
        return link;
    }

    public YoutubeDownloadCallback getCallback() {
        return callback;
    }
}
