package com.sgevf.network.listener;

import java.util.Map;

import io.reactivex.Observable;

public interface ObservableListener {
    Observable setObservable(Map<String, okhttp3.RequestBody> params);
}
