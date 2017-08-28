package com.example.lps.superplayer.viewpagerindicator.title;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 14:28
 */


public interface IPagerTitle {
    void  onSelected(int index,int totalCount);
    void onDisSelected(int index,int totalCount);
    void onLeave(int index,int totalCount,float leavePercent,boolean isLeftToRight);
    void onEnter(int index,int totalCount,float enterPercent,boolean isLeftToRight);
}
