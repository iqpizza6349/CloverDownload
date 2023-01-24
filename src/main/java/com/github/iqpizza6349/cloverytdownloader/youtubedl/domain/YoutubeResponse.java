package com.github.iqpizza6349.cloverytdownloader.youtubedl.domain;

public class YoutubeResponse {

    private final int exitCode;
    private final String out;
    private final String err;
    private final String directory;
    private final int elapsedTime;

    public YoutubeResponse(int exitCode, String out, String err, String directory, int elapsedTime) {
        this.exitCode = exitCode;
        this.out = out;
        this.err = err;
        this.directory = directory;
        this.elapsedTime = elapsedTime;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getOut() {
        return out;
    }

    public String getErr() {
        return err;
    }

    public String getDirectory() {
        return directory;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}
