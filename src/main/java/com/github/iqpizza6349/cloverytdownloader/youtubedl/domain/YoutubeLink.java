package com.github.iqpizza6349.cloverytdownloader.youtubedl.domain;

import com.github.iqpizza6349.cloverytdownloader.core.NativeResourceUtil;

public class YoutubeLink {

    private final String url;
    private final String downloadDirectory;
    private final YoutubeOption option;

    public YoutubeLink(String url, YoutubeOption option) {
        this(url, NativeResourceUtil.defaultNativeLibrary(), option);
    }

    public YoutubeLink(String url, String downloadDirectory, YoutubeOption option) {
        this.url = url;
        this.downloadDirectory = downloadDirectory;
        this.option = option;
    }

    public String getUrl() {
        return url;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public YoutubeOption getOption() {
        return option;
    }
}
