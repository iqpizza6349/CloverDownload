package com.github.iqpizza6349.cloverytdownloader.frame.util;

import java.awt.*;

public final class ResourceUtil {

    private ResourceUtil() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Image getFolderImage() {
        return ImageUtil.getImage("icon/folder_icon.png");
    }

    public static Image getLogoIcon() {
        return ImageUtil.getImage("icon/logo.png");
    }

    public static String getValueFromIni(IniData section, String key) {
        return IniUtil.foundValue(section, key);
    }



}
