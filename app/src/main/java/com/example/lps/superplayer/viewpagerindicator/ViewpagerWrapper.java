package com.example.lps.superplayer.viewpagerindicator;

import android.support.v4.view.ViewPager;

/**
 * Created by lps on 2017/8/28.
 *
 * @version 1
 * @see
 * @since 2017/8/28 10:13
 */


public class ViewpagerWrapper {
    private CoolIndicatorLayout mCoolIdinatorLayout;
    private ViewPager mViewPager;

    public ViewpagerWrapper(CoolIndicatorLayout mCoordinatorLayout, ViewPager mViewPager) {
        this.mCoolIdinatorLayout = mCoordinatorLayout;
        this.mViewPager = mViewPager;
    }

    public static ViewpagerWrapper with(CoolIndicatorLayout mCoolIndicatorLayout, ViewPager mViewPager) {
        return new ViewpagerWrapper(mCoolIndicatorLayout, mViewPager);
    }

    public void compose() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCoolIdinatorLayout.onPagerScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mCoolIdinatorLayout.onPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mCoolIdinatorLayout.onPagerScrollStateChanaged(state);
            }
        });
    }
}
