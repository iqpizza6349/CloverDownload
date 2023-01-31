package com.github.iqpizza6349.cloverytdownloader.core;

import java.util.Objects;

public final class NativeResourceUtil {

    private NativeResourceUtil() {}

    public static String defaultNativeLibrary() {
        final String fullPath = Objects.requireNonNull(NativeResourceUtil.class
                        .getResource("/nativelibs/ffmpeg-win64-gpl/bin/youtube-dl.exe"))
                .toExternalForm();
        return fullPath.substring(6);
    }
}
