package com.sgevf.network.dialog;

import android.app.Dialog;
import android.content.Context;

public class DialogManager {
    public static Dialog initDefaultLoading(Context context){
        return new DialogDefaultLoading(context);
    }
}
