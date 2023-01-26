package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;

public class CloverYTDownloadApplication {

    public static void main(String[] args) {
        Thread thread = new Thread(new MainFrame());
        thread.setDaemon(true);
        thread.start();
    }

    /*
    TODO: 2023-01-26
    * 5 개 이상 큐에 있는 경우, Executor 에 의해 대기 해야함
    * 큐가 작동되고 있는 지, 어쩌는 지를 알아볼 필요가 있음
     */

}
