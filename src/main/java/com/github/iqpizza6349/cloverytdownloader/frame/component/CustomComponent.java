package com.github.iqpizza6349.cloverytdownloader.frame.component;

public interface CustomComponent {

    default String getHexHashCode() {
        return String.format("%s@%x", this.getClass().getSimpleName(), System.identityHashCode(this));
    }

}
