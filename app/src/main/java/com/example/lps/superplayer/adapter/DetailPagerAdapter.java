package com.example.lps.superplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 9:47
 */


public class DetailPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public DetailPagerAdapter(List<Fragment> mFragmentList, FragmentManager fm) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }




    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
