package com.github.iqpizza6349.cloverytdownloader.core;

public final class NativeResourceUtil {

    private NativeResourceUtil() {}

    public static String defaultNativeLibrary() {
        return NativeResourceUtil.class.getClassLoader()
                .getResource("./nativelibs/ffmpeg-win64-gpl/bin/youtube-dl")
                .getPath();
    }

}
