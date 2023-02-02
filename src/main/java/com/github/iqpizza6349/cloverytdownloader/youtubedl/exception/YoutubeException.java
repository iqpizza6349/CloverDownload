package com.github.iqpizza6349.cloverytdownloader.youtubedl.exception;

public class YoutubeException extends Exception {

    private final String message;

    public YoutubeException(String message) {
        this.message = message;
    }

    public YoutubeException(Exception e) {
        this.message = e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
