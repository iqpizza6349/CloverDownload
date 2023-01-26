package com.github.iqpizza6349.cloverytdownloader.frame.util;

import com.github.iqpizza6349.cloverytdownloader.core.exceptions.NoInitializedProgressBarException;
import com.github.iqpizza6349.cloverytdownloader.frame.component.bar.DownloadProgressBar;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public final class ComponentUtil {

    private static final List<DownloadProgressBar> PROGRESS_BARS = new ArrayList<>();


    @SuppressWarnings("unchecked")
    public static <T extends Component> T findComponent(final Component[] data, Class<T> clazz) {
        return (T) Arrays.stream(data).filter(component -> component.getClass() == clazz)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("cannot found such find of " + clazz + "type in elements!"));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Container> T findComponent(final Component[] data, Class<T> clazz,
                                                        final Class<? extends LayoutManager> layout) {
        return (T) Arrays.stream(data).filter(component -> component.getClass() == clazz)
                .filter(component -> ((Container) component).getLayout().getClass() == layout)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("cannot found such find of " + clazz + "type in elements!"));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Container> T findComponent(final Component[] data, Class<T> clazz,
                                                        final Class<? extends LayoutManager> layout,
                                                        final String name) {
        return (T) Arrays.stream(data).filter(component -> component.getClass() == clazz)
                .filter(component -> ((Container) component).getLayout().getClass() == layout)
                .filter(component -> component.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("cannot found such find of " + clazz + "type in elements!"));
    }

    public static JProgressBar findInitializedProgressBar(final Component[] data) throws NoInitializedProgressBarException {
        return (JProgressBar) Arrays.stream(data).filter(component -> component instanceof JProgressBar)
                .filter(component -> ((JProgressBar) component).getValue() == 0)
                .filter(component -> component.getName() == null)
                .findFirst()
                .orElseThrow(NoInitializedProgressBarException::new);
    }

    public static void useDownloadProgressBar(DownloadProgressBar progressBar) {
        PROGRESS_BARS.add(progressBar);
    }

    public static DownloadProgressBar findProgressBarByHexCode(String hexCode) {
        return PROGRESS_BARS.stream().filter(progressBar -> progressBar.getHexHashCode()
                .equalsIgnoreCase(hexCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("cannot found progress-bar with hex-code such as " + hexCode));
    }

}
