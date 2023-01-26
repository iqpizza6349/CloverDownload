package com.github.iqpizza6349.cloverytdownloader.core;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;

public class DownloadRequest {

    private final YoutubeLink link;
    private final YoutubeDownloadCallback callback;
    private final String hexCode;       // reference in DownloadProgressBar.class

    public DownloadRequest(YoutubeLink link, YoutubeDownloadCallback callback,
                           CustomComponent customComponent) {
        this(link, callback, (customComponent == null) ? "0" : customComponent.getHexHashCode());
    }

    public DownloadRequest(YoutubeLink link, YoutubeDownloadCallback callback,
                           String hexCode) {
        this.link = link;
        this.callback = callback;
        this.hexCode = hexCode;
    }

    public YoutubeLink getLink() {
        return link;
    }

    public YoutubeDownloadCallback getCallback() {
        return callback;
    }

    public String getHexCode() {
        return hexCode;
    }

    @Override
    public String toString() {
        return "DownloadRequest{" +
                "link=" + link +
                ", callback=" + callback +
                '}';
    }
}
