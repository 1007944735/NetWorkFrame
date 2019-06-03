package com.sgevf.network.listener;

public interface OnLoadingListener<T> {
    void onLoadFinish(T t);
    void show();
    void dismiss();
}
