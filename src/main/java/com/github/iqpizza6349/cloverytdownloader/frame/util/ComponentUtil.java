package com.github.iqpizza6349.cloverytdownloader.frame.util;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ComponentUtil {

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

    public static JProgressBar findInitializedProgressBar(final Component[] data) {
        return (JProgressBar) Arrays.stream(data).filter(component -> component instanceof JProgressBar)
                .filter(component -> ((JProgressBar) component).getValue() == 0)
                .findFirst()
                .orElseThrow();
    }

}
