package com.github.iqpizza6349.cloverytdownloader.frame.component.bar;

import javax.swing.*;

public class DownloadProgressBar extends JProgressBar {

    public DownloadProgressBar() {
        setStringPainted(true);
        setValue(0);
        setVisible(false);
    }

}
