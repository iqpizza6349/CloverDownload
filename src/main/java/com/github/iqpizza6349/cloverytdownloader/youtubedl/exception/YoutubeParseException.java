package com.github.iqpizza6349.cloverytdownloader.youtubedl.exception;

public class YoutubeParseException extends YoutubeException {

    public YoutubeParseException(String message) {
        super(message);
    }

    public YoutubeParseException(Exception e) {
        super(e);
    }
}
