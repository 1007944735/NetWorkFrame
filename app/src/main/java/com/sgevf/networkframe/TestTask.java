package com.sgevf.networkframe;

import android.app.Activity;
import android.util.Log;

import com.sgevf.network.basic.http.BService;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class TestTask extends BService<Test,String> {
    public TestTask(Activity mActivity, Object mTarget) {
        super(mActivity, mTarget);
    }

    @Override
    public Observable setObservable(Map<String, RequestBody> params) {
        return getService().test();
    }

    @Override
    public void beforeRequest(Disposable d) {

    }

    @Override
    public void onSuccess(String s) {
        if(getTarget() instanceof MainActivity){
            ((MainActivity) getTarget()).onLoadFinish(s);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d("TAG", "onError: "+e.getMessage());
    }

    @Override
    public void onFail(int reCode, String reInfo) {

    }

    @Override
    public void finishRequest() {

    }
}
