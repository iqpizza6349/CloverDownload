package com.github.iqpizza6349.cloverytdownloader.youtubedl.process;

import java.io.IOException;
import java.io.InputStream;

public class StreamGobbler extends Thread {

    private final InputStream stream;
    private final StringBuffer buffer;

    public StreamGobbler(InputStream stream, StringBuffer buffer) {
        this.stream = stream;
        this.buffer = buffer;
        start();
    }

    @Override
    public void run() {
        try {
            int nextChar;
            while ((nextChar = stream.read()) != -1) {
                buffer.append((char) nextChar);
            }
        } catch (IOException e) {
            /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
            // TODO: 2023-01-24 handle this exception
            e.printStackTrace();
        }
    }
}
