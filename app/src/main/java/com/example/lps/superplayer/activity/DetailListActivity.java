package com.example.lps.superplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.DetailPagerAdapter;
import com.example.lps.superplayer.fragment.DetailListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailListActivity extends BaseActivity {

    public static final String TITLE="title";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
String title;
List<Fragment> mFragmentList;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title=getIntent().getStringExtra(TITLE);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentList=new ArrayList<>();
        DetailListFragment mDetailListFragment=new DetailListFragment();
        Bundle mBundle=new Bundle();
        mBundle.putString(mDetailListFragment.CHANNEL,"letv");
        mBundle.putString(mDetailListFragment.CATEGARY,title);
        mDetailListFragment.setArguments(mBundle);

        mFragmentList.add(mDetailListFragment);
        DetailListFragment mDetailListFragment1=new DetailListFragment();
        Bundle mBundle1=new Bundle();
        mBundle1.putString(mDetailListFragment.CHANNEL,"souhu");
        mBundle1.putString(mDetailListFragment.CATEGARY,title);
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
