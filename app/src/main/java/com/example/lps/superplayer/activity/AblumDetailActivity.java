package com.example.lps.superplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.Album;

//剧集的详情页
public class AblumDetailActivity extends BaseActivity implements View.OnClickListener {
    Album mAlbum;
    boolean isShowDesc;//是否显示描述信息
    int mVideoNum;
    private Toolbar mToolbar;
    private ImageView mImgConver;
    private TextView mTitle;
    private TextView mDirector;
    private TextView mActor;
    private TextView mTvAlbumDesc;
    private RelativeLayout mAlbumDescContainer;
    private FrameLayout mFragmentContainer;
    /**
     * 超清
     */
    private Button mBtSuper;
    /**
     * 标清
     */
    private Button mBtNormal;
    /**
     * 高清
     */
    private Button mBtHigh;

    @Override
    protected void initData() {
        mAlbum = (Album) getIntent().getSerializableExtra("album");
        mVideoNum = getIntent().getIntExtra("videonum", 0);
        isShowDesc = getIntent().getBooleanExtra("isshowdesc", false);
        mToolbar.setTitle(mAlbum.getTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle.setText(mAlbum.getTitle());
        if (!TextUtils.isEmpty(mAlbum.getDirector())) {
            mDirector.setText("导演：" + mAlbum.getDirector());
            mDirector.setVisibility(View.VISIBLE);
        } else {
            mDirector.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mAlbum.getMainActor())) {
            mActor.setText("主演：" + mAlbum.getMainActor());
            mActor.setVisibility(View.VISIBLE);
        } else {
            mActor.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mAlbum.getAlbumDesc())&&isShowDesc){
            mTvAlbumDesc.setText(mAlbum.getAlbumDesc());
            mTvAlbumDesc.setVisibility(View.VISIBLE);
            mAlbumDescContainer.setVisibility(View.VISIBLE);
        }else {
            mAlbumDescContainer.setVisibility(View.GONE);
            mTvAlbumDesc.setVisibility(View.GONE);
        }
        Glide.with(this).load(mAlbum.getHorImgUrl()).into(mImgConver);
    }

    @Override
    protected void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImgConver = (ImageView) findViewById(R.id.img_conver);
        mTitle = (TextView) findViewById(R.id.title);
        mDirector = (TextView) findViewById(R.id.director);
        mActor = (TextView) findViewById(R.id.actor);


        mTvAlbumDesc = (TextView) findViewById(R.id.tv_album_desc);
        mAlbumDescContainer = (RelativeLayout) findViewById(R.id.album_desc_container);
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        mBtSuper = (Button) findViewById(R.id.bt_super);
        mBtSuper.setOnClickListener(this);
        mBtNormal = (Button) findViewById(R.id.bt_normal);
        mBtNormal.setOnClickListener(this);
        mBtHigh = (Button) findViewById(R.id.bt_high);
        mBtHigh.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_super:
                break;
            case R.id.bt_normal:
                break;
            case R.id.bt_high:
                break;
        }
    }
}
