package com.github.iqpizza6349.cloverytdownloader.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoFormat;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoInfo;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoThumbnail;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.util.StreamGobbler;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.util.StreamProcessExtractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YoutubeDL {

    /**
     * Youtube-dl executable name
     */
    protected static String executablePath = "youtube-dl";

    /**
     * Append executable name to command
     * @param command Command string
     * @return Command string
     */
    protected static String buildCommand(String command) {
        return String.format("%s %s", executablePath, command);
    }

    /**
     * Execute youtube-dl request
     * @param request request object
     * @return response object
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
        return execute(request, null);
    }

    /**
     * Execute youtube-dl request
     * @param request request object
     * @param callback callback
     * @return response object
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request, DownloadProgressCallback callback) throws YoutubeDLException {

        String command = buildCommand(request.buildOptions());
        String directory = request.getDirectory();
        Map<String, String> options = request.getOption();

        YoutubeDLResponse youtubeDLResponse;
        Process process;
        int exitCode;
        StringBuffer outBuffer = new StringBuffer(); //stdout
        StringBuffer errBuffer = new StringBuffer(); //stderr
        long startTime = System.nanoTime();

        String[] split = command.split(" ");

        ProcessBuilder processBuilder = new ProcessBuilder(split);

        // Define directory if one is passed
        if(directory != null)
            processBuilder.directory(new File(directory));

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new YoutubeDLException(e);
        }

        InputStream outStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();

        StreamProcessExtractor stdOutProcessor = new StreamProcessExtractor(outBuffer, outStream, callback);
        StreamGobbler stdErrProcessor = new StreamGobbler(errBuffer, errStream);

        try {
            stdOutProcessor.join();
            stdErrProcessor.join();
            exitCode = process.waitFor();
        } catch (InterruptedException e) {

            // process exited for some reason
            throw new YoutubeDLException(e);
        }

        String out = outBuffer.toString();
        String err = errBuffer.toString();

        if(exitCode > 0) {
            throw new YoutubeDLException(err);
        }

        int elapsedTime = (int) ((System.nanoTime() - startTime) / 1000000);

        youtubeDLResponse = new YoutubeDLResponse(command, options, directory, exitCode , elapsedTime, out, err);

        return youtubeDLResponse;
    }


    /**
     * Get youtube-dl executable version
     * @return version string
     */
    public static String getVersion() throws YoutubeDLException {
        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("version");
        return YoutubeDL.execute(request).getOut();
    }

    /**
     * Retrieve all information available on a video
     * @param url Video url
     * @return Video info
     */
    public static VideoInfo getVideoInfo(String url) throws YoutubeDLException  {

        // Build request
        YoutubeDLRequest request = new YoutubeDLRequest(url);
        request.setOption("dump-json");
        request.setOption("no-playlist");
        YoutubeDLResponse response = YoutubeDL.execute(request);

        // Parse result
        ObjectMapper objectMapper = new ObjectMapper();
        VideoInfo videoInfo;

        try {
            videoInfo = objectMapper.readValue(response.getOut(), VideoInfo.class);
        } catch (IOException e) {
            throw new YoutubeDLException("Unable to parse video information: " + e.getMessage());
        }

        return videoInfo;
    }

    /**
     * List formats
     * @param url Video url
     * @return list of formats
     */
    public static List<VideoFormat> getFormats(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.formats;
    }

    /**
     * List thumbnails
     * @param url Video url
     * @return list of thumbnail
     */
    public static List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.thumbnails;
    }

    /**
     * List categories
     * @param url Video url
     * @return list of category
     */
    public static List<String> getCategories(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.categories;
    }

    /**
     * List tags
     * @param url Video url
     * @return list of tag
     */
    public static List<String> getTags(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.tags;
    }

    /**
     * Get command executable or path to the executable
     * @return path string
     */
    public static String getExecutablePath(){
        return executablePath;
    }

    /**
     * Set path to use for the command
     * @param path String path to the executable
     */
    public static void setExecutablePath(String path){
        executablePath = path;
    }
}