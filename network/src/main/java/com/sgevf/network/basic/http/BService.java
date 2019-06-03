package com.sgevf.network.basic.http;

import android.app.Activity;

import com.sgevf.network.Config;
import com.sgevf.network.basic.entitiy.RequestBody;
import com.sgevf.network.listener.ObservableListener;
import com.sgevf.network.listener.ObserverListener;
import com.sgevf.network.listener.UploadProgressListener;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BService<T,S> implements ObserverListener<S>,ObservableListener {
    private T service;
    private Activity mActivity;
    private Object mTarget;
    private RequestBody params;

    public BService(Activity mActivity, Object mTarget){
        this(mActivity,mTarget,null);
    }

    public BService(Activity mActivity, Object mTarget, UploadProgressListener listener){
        this.mActivity=mActivity;
        this.mTarget=mTarget;
        params=new RequestBody();
        params.setUploadListener(listener);
        Retrofit retrofit=new Retrofit.Builder()
                .client(OkHttpManager.getClient(mActivity))
                .baseUrl(Config.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        if(getCls().isInterface()) {
            service = retrofit.create(getCls());
        }
    }

    public void request(){
        Observable o=setObservable(params.getParam());
        if(o!=null){
            subscribe(o,setObserver());
        }
    }

    private void subscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private Class<T> getCls() {
        Class c = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return c;
    }

    private Observer setObserver(){
        BObserver<S> observer=new BObserver<>(mActivity,mTarget);
        observer.setListener(this);
        return observer;
    }

    public T getService() {
        return service;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public Object getTarget() {
        return mTarget;
    }

    public RequestBody getParams() {
        return params;
    }
}
