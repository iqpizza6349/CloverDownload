package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;

public class CloverYTDownloadApplication {

    public static void main(String[] args) {
        Thread thread = new Thread(new MainFrame());
        thread.setDaemon(true);
        thread.start();
    }

    // TODO: 2023-01-29 playlist 를 진행 중에 configuration 에 손을 대서 최대나 최소값을 바꾼다면 어떻게 될까?
    // TODO: 2023-01-29 playlist 특정 인덱스로 시작하는 url로 1번째 부터 접근하도록 유도한다면 어떻게 될까?
    // TODO: 2023-01-29 많고 많은 예외처리하기
}
