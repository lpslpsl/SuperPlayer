package com.example.lps.superplayer.model;

import android.support.annotation.LayoutRes;

import com.example.lps.superplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lps on 2017/8/11.
 *
 * @version 1
 * @see
 * @since 2017/8/11 14:40
 * 首页频道
 */


public class Channel {
    @LayoutRes
    int imgRes;
    String chanlName;

    public Channel(int mImgRes, String mChanlName) {
        imgRes = mImgRes;
        chanlName = mChanlName;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int mImgRes) {
        imgRes = mImgRes;
    }

    public String getChanlName() {
        return chanlName;
    }

    public void setChanlName(String mChanlName) {
        chanlName = mChanlName;
    }

    public static List<Channel> getChannelList() {
        List<Channel> mChannels = new ArrayList<>();
        mChannels.add(new Channel(R.drawable.ic_movie, "电影"));
        mChannels.add(new Channel(R.drawable.ic_documentary, "纪录片"));
        mChannels.add(new Channel(R.drawable.ic_comic, "动漫"));
        mChannels.add(new Channel(R.drawable.ic_music, "音乐"));
        mChannels.add(new Channel(R.drawable.ic_show, "电视剧"));
        mChannels.add(new Channel(R.drawable.ic_variety, "综艺"));
        mChannels.add(new Channel(R.drawable.ic_live, "直播"));
        mChannels.add(new Channel(R.drawable.ic_bookmark, "收藏"));
        mChannels.add(new Channel(R.drawable.ic_history, "历史记录"));
        return mChannels;
    }
}
