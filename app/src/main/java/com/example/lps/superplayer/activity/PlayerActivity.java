package com.example.lps.superplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.souhu.Video;
import com.example.lps.superplayer.widget.media.IjkVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayerActivity extends Activity {
    public static final String URL = "url";
    public static final String Video = "video";
    public static final String TYPE = "type";
    public static final int TYPE_NOR = 0;
    public static final int TYPE_HIGH = 1;
    public static final int TYPE_SURPER = 2;
    Video mVideo;
    String url;// 播放url
    int type;//清晰度类型


    @BindView(R.id.ijkvideoView)
    IjkVideoView mIjkvideoView;
    @BindView(R.id.rela_load)
    RelativeLayout mRelaLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
//        mIjkvideoView.setHudView(new TableLayout(this));
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        Uri mUri = Uri.parse(url);
        mIjkvideoView.setVideoURI(mUri);
        mIjkvideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                mIjkvideoView.start();
                mRelaLoad.setVisibility(View.GONE);
            }
        });
        mIjkvideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                switch (what) {
                    case IjkMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mRelaLoad.setVisibility(View.VISIBLE);
                        break;
                    case IjkMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mRelaLoad.setVisibility(View.GONE);

                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
        Intent mIntent = getIntent();
        url = mIntent.getStringExtra(URL);

        type = mIntent.getIntExtra(TYPE, -1);
        mVideo = mIntent.getParcelableExtra(Video);
    }

    @OnClick({R.id.ijkvideoView, R.id.rela_load})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ijkvideoView:
                break;
            case R.id.rela_load:
                break;
        }
    }
}
