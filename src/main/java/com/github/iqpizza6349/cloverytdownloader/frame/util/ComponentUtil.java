package com.github.iqpizza6349.cloverytdownloader.frame.util;

import com.github.iqpizza6349.cloverytdownloader.constant.ConfigMenuItem;
import com.github.iqpizza6349.cloverytdownloader.core.exceptions.NoInitializedProgressBarException;
import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;
import com.github.iqpizza6349.cloverytdownloader.frame.component.bar.DownloadProgressBar;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public final class ComponentUtil {

    private static final Map<DownloadProgressBar, Boolean> AVAILABLE_PROGRESS_BARS
            = new LinkedHashMap<>();
    private static final Map<String, Boolean> AVAILABLE_MENU_ITEMS = new HashMap<>();
    private static MainFrame MAIN_FRAME;

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

    public static void addJProgressBar(final Component[] data) {
        Arrays.stream(data)
                .filter(component -> component.getClass() == DownloadProgressBar.class)
                .map(component -> (DownloadProgressBar) component)
                .forEach(progressBar -> AVAILABLE_PROGRESS_BARS.put(progressBar, true));
    }

    public static synchronized DownloadProgressBar getAvailableProgressBar() throws
            NoInitializedProgressBarException {
        for (Map.Entry<DownloadProgressBar, Boolean> entry : AVAILABLE_PROGRESS_BARS.entrySet()) {
            final DownloadProgressBar progressBar = entry.getKey();
            final boolean available = entry.getValue();

            if (!available || progressBar.getValue() != 0 || progressBar.getTitle() != null) {
                continue;
            }

            return progressBar;
        }

        throw new NoInitializedProgressBarException();
    }

    public static synchronized boolean checkDuplicateRequest(String title) {
        if (AVAILABLE_PROGRESS_BARS.keySet().stream()
                .allMatch(progressBar -> progressBar.getTitle() == null)) {
            // if progress bar is all null
            return false;
        }

        for (DownloadProgressBar progressBar : AVAILABLE_PROGRESS_BARS.keySet()) {
            if (progressBar.getTitle() == null) {
                return true;
            }

            if (progressBar.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }

        return false;
    }

    public static synchronized void useDownloadProgressBar(DownloadProgressBar progressBar) {
        AVAILABLE_PROGRESS_BARS.put(progressBar, false);
    }

    public static synchronized void returnProgressBar(DownloadProgressBar progressBar) {
        AVAILABLE_PROGRESS_BARS.put(progressBar, true);
    }

    public static void initializesMenuItems(Collection<ConfigMenuItem> components) {
        components.forEach(s -> AVAILABLE_MENU_ITEMS.put(s.getName(), true));
    }

    public static boolean isAvailableMenuItem(ConfigMenuItem menuItem) {
        final String menuName = menuItem.getName();
        if (!AVAILABLE_MENU_ITEMS.containsKey(menuName)) {
            // doesn't exists
            return false;
        }

        for (Map.Entry<String, Boolean> menu : AVAILABLE_MENU_ITEMS.entrySet()) {
            if (menu.getKey().equals(menuName) && menu.getValue()) {
                return true;
            }
        }
        return false;
    }

    public static void useAvailableMenuItem(ConfigMenuItem menuItem) {
        if (!AVAILABLE_MENU_ITEMS.containsKey(menuItem.getName())) {
            throw new NoSuchElementException();
        }

        AVAILABLE_MENU_ITEMS.put(menuItem.getName(), false);
    }

    public static void returnMenuItem(ConfigMenuItem menuItem) {
        AVAILABLE_MENU_ITEMS.put(menuItem.getName(), true);
    }

    public static void initializeMainFrame(MainFrame mainFrame) {
        if (MAIN_FRAME == null) {
            MAIN_FRAME = mainFrame;
        }
    }

    public static void showDiagram(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(MAIN_FRAME, message, title, messageType);
    }
}
