package com.github.iqpizza6349.cloverytdownloader.exitmanager;

import com.github.iqpizza6349.cloverytdownloader.constant.ExitCode;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;

import javax.swing.*;

public class ExitManagerImpl implements ExitManager {

    @Override
    public void occurredExit(ExitCode exitCode) {
        checkExit(exitCode);
    }

    protected void checkExit(ExitCode exitCode) {
        switch (exitCode) {
            case SUCCESS_EXIT:
                System.exit(0);
                break;
            case NO_TITLE:
            case DOWNLOAD_FAILED:
                ComponentUtil.showDiagram("Download Failed",
                        "Cannot download successful.%n Please try again.",
                        JOptionPane.WARNING_MESSAGE
                );
                break;
            case DUPLICATE_REQUEST:
                ComponentUtil.showDiagram("Duplicate Request", "Please Request again when requests all done.",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case INVALID_REQUEST:
                ComponentUtil.showDiagram("Invalid Request Detected", "Please check your download path and url is valid",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case NETWORK_ERROR:
                ComponentUtil.showDiagram("Network Error", "Please try again or check your network is connected",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(ExitCode.NETWORK_ERROR.getCode());
                break;
            case NO_NATIVE_LIBRARY:
                ComponentUtil.showDiagram("Cannot Find Native Library",
                        "Please check if the native library exists in 'nativelibs' directory",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(ExitCode.NO_NATIVE_LIBRARY.getCode());
                break;
        }
    }
}
