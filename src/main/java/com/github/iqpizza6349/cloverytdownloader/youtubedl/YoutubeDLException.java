package com.github.iqpizza6349.cloverytdownloader.youtubedl;

public class YoutubeDLException extends Exception {

    /**
     * Exception message
     */
    private final String message;

    /**
     * Construct YoutubeDLException with a message
     */
    public YoutubeDLException(String message) {
        this.message = message;
    }

    /**
     * Construct YoutubeDLException from another exception
     * @param e Any exception
     */
    public YoutubeDLException(Exception e) {
        message = e.getMessage();
    }

    /**
     * Get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return message;
    }
}