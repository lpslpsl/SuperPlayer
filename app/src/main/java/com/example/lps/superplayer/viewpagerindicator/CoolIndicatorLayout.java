package com.example.lps.superplayer.viewpagerindicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by lps on 2017/8/21.
 *
 * @version 1
 * @see
 * @since 2017/8/21 15:40
 * 供外部布局引用
 */


public class CoolIndicatorLayout extends FrameLayout {
    private IPagerIndicatorLayout mIPagerIndicatorLayout;

    public CoolIndicatorLayout(@NonNull Context context) {
        super(context);
    }

    public CoolIndicatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIPagerIndicatorLayout(IPagerIndicatorLayout ipagerIndicatorLayout) {
        if (ipagerIndicatorLayout == mIPagerIndicatorLayout) {
            return;
        }

        if (mIPagerIndicatorLayout != null) {
            mIPagerIndicatorLayout.onDetachCoolIndicatorLayout();
        }
        mIPagerIndicatorLayout = ipagerIndicatorLayout;
        removeAllViews();
        if (mIPagerIndicatorLayout != null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView((View) mIPagerIndicatorLayout, lp);
            mIPagerIndicatorLayout.onAttachCoolIndicatorLayout();
        }
    }


    public void onPagerSelected(int position) {
        if (mIPagerIndicatorLayout != null) {
            mIPagerIndicatorLayout.onPagerSelected(position);
        }
    }

    public void onPagerScrolled(int position, float positionOffsetPersent, int positionOffsetPixel) {
        if (mIPagerIndicatorLayout != null) {
            mIPagerIndicatorLayout.onPagerScrolled(position, positionOffsetPersent, positionOffsetPixel);
        }
    }

    public void onPagerScrollStateChanaged(int state) {
        if (mIPagerIndicatorLayout != null) {
            mIPagerIndicatorLayout.onPagerScrollStateChanaged(state);
        }
    }
}
