package com.github.iqpizza6349.cloverytdownloader.core;

public final class NativeResourceUtil {

    private NativeResourceUtil() {}

    public static String defaultNativeLibrary() {
        return System.getProperty("user.dir") + "/nativelibs/ffmpeg-win64-gpl/bin/yt-dlp.exe";
    }
}
