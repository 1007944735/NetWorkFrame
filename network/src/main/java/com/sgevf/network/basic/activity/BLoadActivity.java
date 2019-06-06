package com.sgevf.network.basic.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sgevf.network.dialog.DialogManager;
import com.sgevf.network.listener.OnLoadingListener;

public abstract class BLoadActivity<T> extends AppCompatActivity implements OnLoadingListener<T> {
    protected Dialog loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading=initDialog(this);
    }

    public Dialog initDialog(Context context){
        return DialogManager.initDefaultLoading(context);
    }

    public View initLoading(){
        return null;
    }

    @Override
    public void show() {
        if(loading!=null){
            loading.show();
        }
    }

    @Override
    public void dismiss() {
        if(loading!=null){
            loading.dismiss();
        }
    }
}
