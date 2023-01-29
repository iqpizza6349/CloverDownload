package com.github.iqpizza6349.cloverytdownloader.youtubedl.process;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.progress.YoutubeDownloadCallback;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessExtractor extends Thread {

    private static final String GROUP_PERCENT = "percent";
    private static final String GROUP_MINUTES = "minutes";
    private static final String GROUP_SECONDS = "seconds";
    private static final Pattern PROGRESS_REGEX = Pattern.compile(
            "\\[download]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)\\s*"
    );

    private static final String GROUP_START_INDEX = "start";
    private static final String GROUP_END_INDEX = "end";
    private static final Pattern PLAYLIST_REGEX = Pattern.compile(
            "\\[download] Downloading video (?<start>\\d+) of (?<end>\\d+)"
    );

    private static final String TITLE_DESCRIBE_REGEX = "[download] Destination: ";

    private final BufferedReader stream;
    private final StringBuffer buffer;
    private final YoutubeDownloadCallback callback;

    public StreamProcessExtractor(InputStream stream, StringBuffer buffer, YoutubeDownloadCallback callback) {
        try {
            // TODO: 2023-01-29 option.ini to set charset-name
            this.stream = new BufferedReader(new InputStreamReader(stream, "x-windows-949"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.buffer = buffer;
        this.callback = callback;
        start();
    }

    private int end = -1;
    private int current = -1;
    private String title;

    @Override
    public void run() {
        try {
            //noinspection StringBufferMayBeStringBuilder
            StringBuffer currentLine = new StringBuffer();
            int nextChar;
            while((nextChar = stream.read()) != -1) {
                buffer.append((char) nextChar);
                if ((nextChar == '\n' || nextChar == '\r') && callback != null) {
                    processOutputLine(currentLine.toString());
                    currentLine.setLength(0);
                    continue;
                }

                currentLine.append((char) nextChar);
            }
        } catch (IOException e) {
            /* if exception occurred, program need to handle and show message or whatever shows that exception has occurred */
            // TODO: 2023-01-24 handle this exception
            e.printStackTrace();
        }
    }

    private void processOutputLine(String line) {
        Matcher playlistMatcher = PLAYLIST_REGEX.matcher(line);
        if (playlistMatcher.matches()) {
            current = Integer.parseInt(playlistMatcher.group(GROUP_START_INDEX));
            end = Integer.parseInt(playlistMatcher.group(GROUP_END_INDEX));
        }

        if (line.startsWith(TITLE_DESCRIBE_REGEX)) {
            String describeTitle = line.replace(TITLE_DESCRIBE_REGEX, "");
            title = describeTitle.substring(0, describeTitle.lastIndexOf("."));
        }

        Matcher progressMatcher = PROGRESS_REGEX.matcher(line);
        if (progressMatcher.matches()) {
            float progress = Float.parseFloat(progressMatcher.group(GROUP_PERCENT));
            long eta = convertToSeconds(progressMatcher.group(GROUP_MINUTES), progressMatcher.group(GROUP_SECONDS));
            callback.onProgressUpdate(title, progress, eta, current, end);
        }
    }

    private int convertToSeconds(String minutes, String seconds) {
        return Integer.parseInt(minutes) * 60
                + Integer.parseInt(seconds);
    }
}
