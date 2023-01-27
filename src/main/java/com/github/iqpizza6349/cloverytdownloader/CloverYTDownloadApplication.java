package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;

public class CloverYTDownloadApplication {

    public static void main(String[] args) {
        Thread thread = new Thread(new MainFrame());
        thread.setDaemon(true);
        thread.start();
    }

    // TODO: 2023-01-27 playlist 를 어떻게 처리할 것 인가?
    // 1번째 인덱스에 포함된 것만 사용할 것인가?
    // 아니라면 병렬로 계속 사용할 것인가?
    // 만일 전자를 택한다면 매우 간단하게 처리가 가능하다.
    // 하지만 후자라면 playlist 를 한 번 더 쪼개서 처리해야 한다.
    // playlist 에 있는 모든 음악을 QUEUE 에 넣거나 playlist-queue 를 따로 만들어서 관리

}
