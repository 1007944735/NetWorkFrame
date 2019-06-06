package com.sgevf.networkframe;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.sgevf.network.basic.http.BService;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class TestTask extends BService<Test,String> {
    private ProgressBar view;
    public TestTask(Activity mActivity, Object mTarget) {
        super(mActivity, mTarget);
        setLoadingVisibility(false);
    }

    public TestTask setLoading(ProgressBar view){
        this.view=view;
        return this;
    }

    @Override
    public Observable setObservable(Map<String, RequestBody> params) {
        return getService().test();
    }

    @Override
    public void beforeRequest(Disposable d) {
        view.setVisibility(View.VISIBLE);
        Log.d("TAG", "beforeRequest");
    }

    @Override
    public void onSuccess(String s) {
        Log.d("TAG", "onSuccess");
        if(getTarget() instanceof MainActivity){
            ((MainActivity) getTarget()).onLoadFinish(s);
        }
        view.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable e) {
        Log.d("TAG", "onError");
        view.setVisibility(View.GONE);
    }

    @Override
    public void onFail(int reCode, String reInfo) {
        Log.d("TAG", "onFail");
        view.setVisibility(View.GONE);
    }

    @Override
    public void finishRequest() {
        Log.d("TAG", "finishRequest");
    }
}
