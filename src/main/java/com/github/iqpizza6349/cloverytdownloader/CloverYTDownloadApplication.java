package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.frame.MainFrame;

public class CloverYTDownloadApplication {

    public static void main(String[] args) {
        Thread thread = new Thread(new MainFrame());
        thread.setDaemon(true);
        thread.start();
    }

    /*
     * TODO: 2023-01-26
     * (1) 현재 다운로드 받고 있는 중에 처리가 아직 시작되지 않아, value 가 0인 경우
     * 두 번 이상 빠르게 누를 경우, 같은 progress 를 사용해버리는 경우가 발생함
     * 즉, 동일한 progress-bar 에 하나는 정상적인 요청이 실행되고, 다른 하나는 null 의 상태가 작동해버림
     * sleep 을 통해 제어를 해주니 잘 작동은 하나, 이름이 동일하기에 RENAME 처리가 발생.
     * 한 번에 하나의 RENAME 이 발생하는 것이 정상이기에, WINERROR 가 발생한다. 32, 2
     * 아마 각각의 파일이 아닌 2개의 파일이 동시에 생겨날 수 없다는 점에서 예외가 발생한 듯
     * 즉 해당 오류는 한 번에 동일한 파일을 2 번 이상 요청할 경우에만 발생한다.
     *
     * 이를 예외 처리해주기 위해서는 pop-up 을 통해 그대로 진행할 것인지, 아니면
     * 따로 설정할 수 있도록 할 것인지를 판단하도록 해야할 것으로 보인다.
     * 완전히 동일한 파일만이 아니라면 문제는 발생하지 않는다.
     */

    // TODO: 2023-01-27 playlist 를 어떻게 처리할 것 인가?
    // 1번째 인덱스에 포함된 것만 사용할 것인가?
    // 아니라면 병렬로 계속 사용할 것인가?
    // 만일 전자를 택한다면 매우 간단하게 처리가 가능하다.
    // 하지만 후자라면 playlist 를 한 번 더 쪼개서 처리해야 한다.
    // playlist 에 있는 모든 음악을 QUEUE 에 넣거나 playlist-queue 를 따로 만들어서 관리

}
