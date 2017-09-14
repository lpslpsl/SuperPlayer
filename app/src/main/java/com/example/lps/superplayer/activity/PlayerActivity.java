package com.example.lps.superplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.souhu.Video;

public class PlayerActivity extends Activity {
public static final String URL="url";
public static final String Video="video";
public static final String TYPE="type";
    public static final int TYPE_NOR=0;
    public static final int TYPE_HIGH=1;
    public static final int TYPE_SURPER=2;
    Video  mVideo;
    String url;// 播放url
    int type;//清晰度类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initData();
    }

    private void initData() {
        Intent mIntent = getIntent();
        url = mIntent.getStringExtra(URL);

        type = mIntent.getIntExtra(TYPE, -1);
        mVideo = mIntent.getParcelableExtra(Video);
    }
}
