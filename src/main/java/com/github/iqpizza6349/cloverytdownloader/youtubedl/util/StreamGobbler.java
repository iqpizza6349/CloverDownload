package com.github.iqpizza6349.cloverytdownloader.youtubedl.util;

import java.io.IOException;
import java.io.InputStream;

public class StreamGobbler extends Thread {

    private final InputStream stream;
    private final StringBuffer buffer;

    public StreamGobbler(StringBuffer buffer, InputStream stream) {
        this.stream = stream;
        this.buffer = buffer;
        start();
    }

    public void run() {
        try {
            int nextChar;
            while((nextChar = this.stream.read()) != -1) {
                this.buffer.append((char) nextChar);
            }
        } catch (IOException ignored) {}
    }
}