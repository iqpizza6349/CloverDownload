package com.github.iqpizza6349.cloverytdownloader.frame.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

final class ImageUtil {

    private ImageUtil() {}

    static Image getImage(String resourceName) {
        ClassLoader loader = ImageUtil.class.getClassLoader();
        try (final InputStream imageResource = loader.getResourceAsStream(resourceName)) {
            if (imageResource == null) {
                throw new IllegalArgumentException("file not found!");
            }

            return ImageIO.read(imageResource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
