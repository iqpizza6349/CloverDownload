package com.github.iqpizza6349.cloverytdownloader.constant;

public enum YoutubeFormat {
    MP3("mp3"),
    OGG("vorbis");

    private final String audioFormat;

    YoutubeFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public String getAudioFormat() {
        return audioFormat;
    }
}
