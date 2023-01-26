package com.github.iqpizza6349.cloverytdownloader.core.task;

@FunctionalInterface
public interface SuccessCallback<T> {

    void onSuccess(T t);

}
