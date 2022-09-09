package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;

public class CloverYTDownloadApplication {

    public static void main(String[] args) {
        new Thread(new MainFrame()).start();
    }
}
