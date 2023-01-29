package com.github.iqpizza6349.cloverytdownloader.frame.component.bar;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;

import javax.swing.*;
import java.awt.*;

public class DownloadProgressBar extends JProgressBar implements CustomComponent {

    private String title;

    public DownloadProgressBar() {
        setStringPainted(true);
        setPreferredSize(new Dimension(450, 24));

        percentUpdate(0);
        etaUpdate(0);

        setVisible(false);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void percentUpdate(int value) {
        setValue(value);
    }

    public void etaUpdate(long value) {
        setString(String.format("%s (ETA: %d)", title, value));
    }

    public void playlistUpdate(long value, int currentIndex, int total) {
        setString(String.format("%s (ETA: %d) [%d of %d]", title, value, currentIndex,
                total));
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DownloadProgressBar{" +
                "title='" + title + '\'' +
                '}';
    }
}
