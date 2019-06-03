package com.sgevf.network.listener;

import io.reactivex.disposables.Disposable;

public interface ObserverListener<T> {
    void beforeRequest(Disposable d);
    void onSuccess(T t);
    void onError(Throwable e);
    void onFail(int reCode,String reInfo);
    void finishRequest();
}
