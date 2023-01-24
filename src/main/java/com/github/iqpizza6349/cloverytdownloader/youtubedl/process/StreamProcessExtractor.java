package com.github.iqpizza6349.cloverytdownloader.youtubedl.process;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessExtractor extends Thread {

    private static final String GROUP_PERCENT = "percent";
    private static final String GROUP_MINUTES = "minutes";
    private static final String GROUP_SECONDS = "seconds";
    private static final Pattern REGEX = Pattern.compile(
            "\\[download]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)\\s*"
    );

    private final InputStream stream;
    private final StringBuffer buffer;
    private final YoutubeDownloadCallback callback;

    public StreamProcessExtractor(InputStream stream, StringBuffer buffer, YoutubeDownloadCallback callback) {
        this.stream = stream;
        this.buffer = buffer;
        this.callback = callback;
        start();
    }

    @Override
    public void run() {
        try {
            StringBuffer currentLine = new StringBuffer();
            int nextChar;
            while((nextChar = stream.read()) != -1) {
                buffer.append((char) nextChar);
                if (nextChar == '\r' && callback != null) {
                    processOutputLine(currentLine.toString());
                    currentLine.setLength(0);
                    continue;
                }

                currentLine.append((char) nextChar);
            }
        } catch (IOException ignored) {
            /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
            // TODO: 2023-01-24 handle this exception
        }
    }

    private void processOutputLine(String line) {
        Matcher m = REGEX.matcher(line);
        if (m.matches()) {
            float progress = Float.parseFloat(m.group(GROUP_PERCENT));
            long eta = convertToSeconds(m.group(GROUP_MINUTES), m.group(GROUP_SECONDS));
            callback.onProgressUpdate(progress, eta);
        }
    }

    private int convertToSeconds(String minutes, String seconds) {
        return Integer.parseInt(minutes) * 60
                + Integer.parseInt(seconds);
    }
}
