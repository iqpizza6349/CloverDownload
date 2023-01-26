package com.github.iqpizza6349.cloverytdownloader.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.iqpizza6349.cloverytdownloader.core.NativeResourceUtil;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeOption;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeResponse;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeParseException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoInfo;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.StreamGobbler;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.StreamProcessExtractor;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.process.YoutubeDownloadCallback;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.utils.YoutubeOptionUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YoutubeDL {

    private final String executablePath;

    public YoutubeDL() {
        this.executablePath = NativeResourceUtil.defaultNativeLibrary();
    }

    public YoutubeDL(String executablePath) {
        this.executablePath = executablePath;
    }

    protected String buildCommand(String command) {
        return String.format("%s %s", executablePath, command);
    }

    public YoutubeResponse execute(YoutubeLink request) throws YoutubeException {
        return execute(request, null);
    }

    public YoutubeResponse execute(YoutubeLink request,
                                   YoutubeDownloadCallback callback) throws YoutubeException {
        return execute(request.getUrl(), request.getDownloadDirectory(),
                request.getOption().getOptions(), callback);
    }

    protected YoutubeResponse execute(final String options,
                                      final YoutubeDownloadCallback callback) throws YoutubeParseException {
        return execute(null, null, options, callback);
    }

    protected YoutubeResponse execute(final String url, final String directory, final String options,
                                      final YoutubeDownloadCallback callback) throws YoutubeParseException {
        final String command = buildCommand(String.format("%s %s", url, options));

        final int exitCode;
        long startTime = System.nanoTime();
        String[] split = command.split(" ");
        ProcessBuilder processBuilder = new ProcessBuilder(split);

        if (directory != null) {
            processBuilder.directory(new File(directory));
        }

        final Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new YoutubeParseException(e);
        }

        final InputStream outStream = process.getInputStream();     // console text stream
        final InputStream errStream = process.getErrorStream();     // console error stream
        final StringBuffer outBuffer = new StringBuffer();
        final StringBuffer errBuffer = new StringBuffer();
        final StreamProcessExtractor parseProcessor
                = new StreamProcessExtractor(outStream, outBuffer, callback);
        final StreamGobbler errorProcessor = new StreamGobbler(errStream, errBuffer);

        try {
            parseProcessor.join();
            errorProcessor.join();
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new YoutubeParseException(e);
        }

        String out = outBuffer.toString();
        String err = errBuffer.toString();

        if (exitCode > 0) {
            throw new YoutubeParseException(err);
        }

        int elapsedTime = (int) ((System.nanoTime() - startTime) / 1_000_000);
        return new YoutubeResponse(exitCode, out, err, directory, options, elapsedTime);
    }

    public String getVersion() throws YoutubeParseException {
        YoutubeOptionUtil util = new YoutubeOptionUtil();
        util.addOption("version");
        YoutubeOption request = new YoutubeOption(util.buildOptions());
        return execute(request.getOptions(), null).getOut();
    }

    public VideoInfo getVideoInfo(String url) throws YoutubeException {
        YoutubeOptionUtil option = new YoutubeOptionUtil();
        option.addOption("dump-json");
        option.addOption("no-playlist");
        YoutubeLink request = new YoutubeLink(url, null,
                new YoutubeOption(option.buildOptions()));
        YoutubeResponse response = execute(request);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.getOut(), VideoInfo.class);
        } catch (IOException e) {
            throw new YoutubeParseException("Unable to parse video information: " + e.getMessage());
        }
    }
}
