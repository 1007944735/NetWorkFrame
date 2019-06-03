package com.sgevf.network.basic.http;

import android.app.Activity;
import android.widget.Toast;

import com.sgevf.network.basic.entitiy.Response;
import com.sgevf.network.listener.ObserverListener;
import com.sgevf.network.listener.OnLoadingListener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BObserver<T> implements Observer<Response<T>> {
    private ObserverListener<T> listener;
    private Activity mActivity;
    private Object mTarget;

    public BObserver(Activity mActivity){
        this(mActivity, mActivity);
    }

    public BObserver(Activity mActivity, Object mTarget){
        this.mActivity = mActivity;
        this.mTarget = mTarget;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(mTarget instanceof OnLoadingListener){
            ((OnLoadingListener) mTarget).show();
        }
        if(listener!=null) {
            listener.beforeRequest(d);
        }
    }

    @Override
    public void onNext(Response<T> tResponse) {
        if(listener!=null) {
            if (tResponse.reCode == 200) {
                listener.onSuccess(tResponse.params);
            } else {
                listener.onFail(tResponse.reCode, tResponse.reInfo);
                Toast.makeText(mActivity, tResponse.reInfo, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mTarget instanceof OnLoadingListener){
            ((OnLoadingListener) mTarget).dismiss();
        }
        if(listener!=null){
            listener.onError(e);
        }
    }

    @Override
    public void onComplete() {
        if(mTarget instanceof OnLoadingListener){
            ((OnLoadingListener) mTarget).dismiss();
        }
        if(listener!=null){
            listener.finishRequest();
        }
    }

    public ObserverListener<T> getListener() {
        return listener;
    }

    public void setListener(ObserverListener<T> listener) {
        this.listener = listener;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public Object getmTarget() {
        return mTarget;
    }
}
