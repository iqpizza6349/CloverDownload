package com.github.iqpizza6349.cloverytdownloader.youtubedl.domain;

public class YoutubeResponse {

    private final int exitCode;
    private final String out;
    private final String err;
    private final String directory;
    private final String options;
    private final int elapsedTime;

    public YoutubeResponse(int exitCode, String out, String err, String directory,
                           String options, int elapsedTime) {
        this.exitCode = exitCode;
        this.out = out;
        this.err = err;
        this.directory = directory;
        this.options = options;
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

    @Override
    public String toString() {
        return "YoutubeResponse{" +
                "exitCode=" + exitCode +
                ", out='" + out + '\'' +
                ", err='" + err + '\'' +
                ", directory='" + directory + '\'' +
                ", options='" + options + '\'' +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
