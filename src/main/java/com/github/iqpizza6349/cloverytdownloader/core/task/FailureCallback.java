package com.github.iqpizza6349.cloverytdownloader.core.task;

@FunctionalInterface
public interface FailureCallback<T> {

    void onFailure(T t);

}
