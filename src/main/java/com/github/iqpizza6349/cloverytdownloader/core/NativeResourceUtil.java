package com.github.iqpizza6349.cloverytdownloader.core;

import java.util.Objects;

public final class NativeResourceUtil {

    private NativeResourceUtil() {}

    public static String defaultNativeLibrary() {
        return Objects.requireNonNull(NativeResourceUtil.class.getClassLoader()
                        .getResource("./nativelibs/ffmpeg-win64-gpl/bin/youtube-dl.exe")).getPath();
    }

}
