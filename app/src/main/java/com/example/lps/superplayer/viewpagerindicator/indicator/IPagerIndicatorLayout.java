package com.example.lps.superplayer.viewpagerindicator.indicator;

import com.example.lps.superplayer.viewpagerindicator.IPagerChangeListener;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 14:39
 */


public interface IPagerIndicatorLayout extends IPagerChangeListener {
    void onAttachCoolIndicatorLayout();
    void onDetachCoolIndicatorLayout();

}
