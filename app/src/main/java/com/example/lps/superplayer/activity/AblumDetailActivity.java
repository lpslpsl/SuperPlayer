package com.example.lps.superplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.Album;

//剧集的详情页
public class AblumDetailActivity extends BaseActivity {
    Album mAlbum;
    boolean isShowDesc;//是否显示描述信息
    int mVideoNum;
    private Toolbar mToolbar;
    private ImageView mImgConver;
    private TextView mTitle;
    private TextView mDirector;
    private TextView mActor;

    @Override
    protected void initData() {
        mAlbum = (Album) getIntent().getSerializableExtra("album");
        mVideoNum = getIntent().getIntExtra("videonum", 0);
        isShowDesc = getIntent().getBooleanExtra("isshowdesc", false);
        mToolbar.setTitle(mAlbum.getTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImgConver = (ImageView) findViewById(R.id.img_conver);
        mTitle = (TextView) findViewById(R.id.title);
        mDirector = (TextView) findViewById(R.id.director);
        mActor = (TextView) findViewById(R.id.actor);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ablum_detail;
    }

    public static void launch(Activity mActivity, Album mAlbum) {
        Intent mIntent = new Intent(mActivity, AblumDetailActivity.class);
        mIntent.putExtra("album", mAlbum);
        mActivity.startActivity(mIntent);
    }

    public static void launch(Activity mActivity, Album mAlbum, int videonum, boolean showdesc) {
        Intent mIntent = new Intent(mActivity, AblumDetailActivity.class);
        mIntent.putExtra("album", mAlbum);
        mIntent.putExtra("videonum", videonum);
        mIntent.putExtra("isshowdesc", showdesc);
        mActivity.startActivity(mIntent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
