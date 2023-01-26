package com.github.iqpizza6349.cloverytdownloader.core.task;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DownloadFutureTask extends FutureTask<YoutubeResponse> {

    private final SuccessCallback<YoutubeResponse> successCallback;
    private final FailureCallback<Throwable> failureCallback;
    private final FinallyCallback finallyCallback;

    public DownloadFutureTask(Callable<YoutubeResponse> callable,
                              SuccessCallback<YoutubeResponse> successCallback,
                              FailureCallback<Throwable> failureCallback,
                              FinallyCallback finallyCallback) {
        super(callable);
        this.successCallback = successCallback;
        this.failureCallback = failureCallback;
        this.finallyCallback = finallyCallback;
    }

    @Override
    protected void done() {
        try {
            successCallback.onSuccess(get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            failureCallback.onFailure(e.getCause());
        } finally {
            finallyCallback.onFinally();
        }
    }
}
