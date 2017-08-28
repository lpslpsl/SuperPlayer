package com.example.lps.superplayer.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.DetailPagerAdapter;
import com.example.lps.superplayer.fragment.DetailListFragment;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;
import com.example.lps.superplayer.viewpagerindicator.CoolIndicatorLayout;
import com.example.lps.superplayer.viewpagerindicator.ViewPagerIndicatorAdapter;
import com.example.lps.superplayer.viewpagerindicator.ViewPagerIndicatorLayout;
import com.example.lps.superplayer.viewpagerindicator.ViewpagerWrapper;
import com.example.lps.superplayer.viewpagerindicator.indicator.IPagerIndicatorView;
import com.example.lps.superplayer.viewpagerindicator.indicator.ViewPagerIndicatorView;
import com.example.lps.superplayer.viewpagerindicator.title.IPagerTitle;
import com.example.lps.superplayer.viewpagerindicator.title.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailListActivity extends BaseActivity {

    public static final String CHANNEL = "channel";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.cool_indicator_layout)
    CoolIndicatorLayout mCoolIndicatorLayout;
    Channel channel;
    List<Fragment> mFragmentList;

    String[] mSite = {"乐视视频", "搜狐视频"};

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        channel = getIntent().getParcelableExtra(CHANNEL);
        mToolbar.setTitle(channel.getChanlName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentList = new ArrayList<>();
        DetailListFragment mDetailListFragment = new DetailListFragment();
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(mDetailListFragment.CHANNEL, channel);
        mBundle.putInt(mDetailListFragment.SITE_ID, Site.LETV);

        mDetailListFragment.setArguments(mBundle);

        mFragmentList.add(mDetailListFragment);
        DetailListFragment mDetailListFragment1 = new DetailListFragment();
        Bundle mBundle1 = new Bundle();
        mBundle1.putParcelable(mDetailListFragment.CHANNEL, channel);
        mBundle1.putInt(mDetailListFragment.SITE_ID, Site.SOHU);

        mDetailListFragment1.setArguments(mBundle1);

        mFragmentList.add(mDetailListFragment1);
        mViewpager.setAdapter(new DetailPagerAdapter(mFragmentList, getSupportFragmentManager()));

        initViewpagerIndicatorLayout();

    }

    private void initViewpagerIndicatorLayout() {
        ViewPagerIndicatorLayout mViewPagerIndicatorLayout = new ViewPagerIndicatorLayout(this);
        mViewPagerIndicatorLayout.setAdapter(new ViewPagerIndicatorAdapter() {
            @Override
            public int getCount() {
                return mSite.length;
            }

            @Override
            public IPagerTitle getTitle(Context mContext, final int index) {
                ViewPagerTitle mViewPagerTitle = new ViewPagerTitle(mContext);
                mViewPagerTitle.setText(mSite[index]);
                mViewPagerTitle.setNormolColor(Color.parseColor("#333333"));
                mViewPagerTitle.setSelectedColor(Color.parseColor("#e94220"));
                mViewPagerTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewpager.setCurrentItem(index);
                    }
                });
                return mViewPagerTitle;
            }

            @Override
            public IPagerIndicatorView getIndicator(Context mContext) {
                ViewPagerIndicatorView mViewPagerIndicatorView = new ViewPagerIndicatorView(mContext);
                mViewPagerIndicatorView.setFiterColor(Color.parseColor("#ebe4e3"));
                return mViewPagerIndicatorView;
            }
        });
        mCoolIndicatorLayout.setIPagerIndicatorLayout(mViewPagerIndicatorLayout);
        ViewpagerWrapper.with(mCoolIndicatorLayout, mViewpager).compose();
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
    protected int getLayoutId() {
        return R.layout.activity_detail_list;
    }
}
