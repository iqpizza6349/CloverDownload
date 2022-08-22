package com.github.iqpizza6349.cloverytdownloader.frame;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public final class ResourceUtil {

    private ResourceUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static String getResource() {
        URL resource = ResourceUtil.class.getClassLoader()
                .getResource("icon/folder_icon.png");
        return new File(Objects.requireNonNull(resource).getPath()).toPath().toString();
    }
}
