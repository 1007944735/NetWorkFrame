package com.sgevf.networkframe;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sgevf.network.Config;
import com.sgevf.network.basic.activity.BLoadActivity;

public class MainActivity extends BLoadActivity<String> {
    public TextView text;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        Config.url="http://192.168.22.217:8080/";
        new TestTask(this,this).setLoading(progressBar).request();
    }

    @Override
    public void onLoadFinish(String s) {
        text.setText(s);
    }
}
