package com.example.lps.superplayer.viewpagerindicator;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 14:32
 */


public interface IPagerChangeListener {
    void onPagerSelected(int position);
    void onPagerScrolled(int position,float positionOffsetPersent,int positionOffsetPixel);
    void onPagerScrollStateChanaged(int state);
}
