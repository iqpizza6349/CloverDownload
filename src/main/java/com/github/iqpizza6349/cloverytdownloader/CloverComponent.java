package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.exitmanager.ExitManager;
import com.github.iqpizza6349.cloverytdownloader.exitmanager.ExitManagerImpl;

public interface CloverComponent {

    ExitManager EXIT_MANAGER = new ExitManagerImpl();

}
