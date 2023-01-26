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
     *
     * 또다른 문제는 5개가 이미 진행 중에 다음 요청 (즉, 6번째 요청)에서
     * while 를 통해 대기하도록 하였었는 데, 그때 component event thread 에서 대기 상태가 되어
     * progress-bar 및 youtube download 하는 영역(이 둘은 다른 쓰레드를 사용함)
     * 외의 컴포넌트 영역들이 모두 정지되어버리는 상태가 발생함
     */

    // (1) 하나는 null 이 들어간 이상한 요청이므로, failure 이 발생해버리고
    // 다른 하나는 정상적인 요청이기에, 정상적으로 success 이 발생한다.

}
