package com.github.iqpizza6349.cloverytdownloader.constant;

import com.github.iqpizza6349.cloverytdownloader.exitmanager.ExitManagerImpl;

/**
 * if code variable is positive, is will be catch in ExitManager
 * if variable is negative, is will be throw exception to shut down the
 * main process
 * @see ExitManagerImpl
 */
public enum ExitCode {
    SUCCESS_EXIT(0),
    DOWNLOAD_FAILED(2),
    DUPLICATE_REQUEST(3),
    NO_TITLE(4),
    INVALID_REQUEST(5),

    NO_NATIVE_LIBRARY(-1),
    NETWORK_ERROR(-2),
    ;
    private final int code;

    ExitCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
