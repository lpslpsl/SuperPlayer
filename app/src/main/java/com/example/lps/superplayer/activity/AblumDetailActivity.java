package com.example.lps.superplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lps.superplayer.R;
import com.example.lps.superplayer.api.ApiCallBack;
import com.example.lps.superplayer.api.OnGetVideoPlayUrlListener;
import com.example.lps.superplayer.api.SiteApi;
import com.example.lps.superplayer.fragment.AlbumPlayGridFragment;
import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.souhu.Video;

//剧集的详情页
public class AblumDetailActivity extends BaseActivity implements View.OnClickListener, AlbumPlayGridFragment.OnPlayVideoSelectedListener {
    private static final String TAG = "AblumDetailActivity";
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
    boolean isFavor;

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
        if (!TextUtils.isEmpty(mAlbum.getAlbumDesc()) && isShowDesc) {
            mTvAlbumDesc.setText(mAlbum.getAlbumDesc());
            mTvAlbumDesc.setVisibility(View.VISIBLE);
            mAlbumDescContainer.setVisibility(View.VISIBLE);
            mFragmentContainer.setVisibility(View.GONE);
        } else {
            mAlbumDescContainer.setVisibility(View.GONE);
            mTvAlbumDesc.setVisibility(View.GONE);
            mFragmentContainer.setVisibility(View.VISIBLE);
        }
        Glide.with(this).load(mAlbum.getHorImgUrl()).into(mImgConver);

        SiteApi.onGetAlbumDetail(mAlbum, new ApiCallBack<Album>() {
            @Override
            public void onsuccess(Album data) {
                Log.e(TAG, "onsuccess: " + Thread.currentThread().getName());
                AlbumPlayGridFragment mFragment = AlbumPlayGridFragment.newInstance(data, isShowDesc, 0);
                mFragment.setOnPlayVideoSelectedListener(AblumDetailActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment)
                        .commit();
            }

            @Override
            public void onFail(Exception mE) {

            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ablum_menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favirate = menu.findItem(R.id.action_favor_item);
        MenuItem unfavirate = menu.findItem(R.id.action_unfavor_item);
        favirate.setVisible(isFavor);
        unfavirate.setVisible(!isFavor);
        return super.onPrepareOptionsMenu(menu);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_favor_item:
                if (isFavor) {
                    isFavor = false;
//                    todo 收藏状态存储
                    invalidateOptionsMenu();
                    Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_unfavor_item:
                if (!isFavor) {
                    isFavor = true;
                    invalidateOptionsMenu();
                    Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Video video = null;
        String url = "";
        int type = 0;
        switch (v.getId()) {
            case R.id.bt_super:
                video = (Video) v.getTag(R.id.key_video);
                url = (String) v.getTag(R.id.key_video_url);
                type = 2;
                break;
            case R.id.bt_normal:
                type = 0;
                video = (Video) v.getTag(R.id.key_video);
                url = (String) v.getTag(R.id.key_video_url);
                break;
            case R.id.bt_high:
                type = 1;
                video = (Video) v.getTag(R.id.key_video);
                url = (String) v.getTag(R.id.key_video_url);
                break;

        }
        Intent mIntent = new Intent(AblumDetailActivity.this, PlayerActivity.class);
        mIntent.putExtra(PlayerActivity.URL, url);
        mIntent.putExtra(PlayerActivity.TYPE, type);
        mIntent.putExtra(PlayerActivity.Video, video);
        startActivity(mIntent);
    }

    @Override
    public void OnPlayVideoSelected(Video video, int position) {
        SiteApi.onGetPlayVideoUrl(mAlbum.getSite().getSiteId(), video, new OnGetVideoPlayUrlListener() {
            @Override
            public void onGetSuperUrl(Video video, String url) {
                mBtSuper.setTag(R.id.key_video, video);
                mBtSuper.setTag(R.id.key_video_url, url);
                mBtSuper.setVisibility(View.VISIBLE);
            }

            @Override
            public void onGetNoramlUrl(Video video, String url) {
                mBtNormal.setTag(R.id.key_video, video);
                mBtNormal.setTag(R.id.key_video_url, url);
                mBtNormal.setVisibility(View.VISIBLE);
            }

            @Override
            public void onGetHighUrl(Video video, String url) {
                mBtHigh.setTag(R.id.key_video, video);
                mBtHigh.setTag(R.id.key_video_url, url);
                mBtHigh.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {
                mBtHigh.setVisibility(View.GONE);
                mBtNormal.setVisibility(View.GONE);
                mBtSuper.setVisibility(View.GONE);
            }
        });
    }
}
