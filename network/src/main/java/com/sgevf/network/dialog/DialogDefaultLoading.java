package com.sgevf.network.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class DialogDefaultLoading extends Dialog {
    private View view;
    public DialogDefaultLoading(Context context) {
        super(context);
        this.view=new ProgressBar(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
    }
}
