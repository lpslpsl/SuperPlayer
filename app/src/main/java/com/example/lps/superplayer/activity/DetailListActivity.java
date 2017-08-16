package com.example.lps.superplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.DetailPagerAdapter;
import com.example.lps.superplayer.fragment.DetailListFragment;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailListActivity extends BaseActivity {

    public static final String CHANNEL="channel";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
Channel channel;
List<Fragment> mFragmentList;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        channel =getIntent().getParcelableExtra(CHANNEL);
        mToolbar.setTitle(channel.getChanlName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentList=new ArrayList<>();
        DetailListFragment mDetailListFragment=new DetailListFragment();
        Bundle mBundle=new Bundle();
        mBundle.putParcelable(mDetailListFragment.CHANNEL,channel);
        mBundle.putInt(mDetailListFragment.SITE_ID, Site.LETV);

        mDetailListFragment.setArguments(mBundle);

        mFragmentList.add(mDetailListFragment);
        DetailListFragment mDetailListFragment1=new DetailListFragment();
        Bundle mBundle1=new Bundle();
        mBundle1.putParcelable(mDetailListFragment.CHANNEL,channel);
        mBundle1.putInt(mDetailListFragment.SITE_ID, Site.SOHU);

        mDetailListFragment1.setArguments(mBundle1);

        mFragmentList.add(mDetailListFragment1);
        mViewpager.setAdapter(new DetailPagerAdapter(mFragmentList,getSupportFragmentManager()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
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
