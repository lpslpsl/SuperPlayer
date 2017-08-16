package com.example.lps.superplayer.model;

import android.os.Parcel;
import android.os.Parcelable;
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


public class Channel implements Parcelable {
    public static final int SHOW = 1;//电视剧
    public static final int MOVIE = 2;//电影
    public static final int COMIC = 3;//动漫
    public static final int DOCUMENTRY = 4;//纪录片
    public static final int MUSIC = 5;//音乐
    public static final int VARIETY = 6;//综艺
    public static final int LIVE = 7;//直播
    public static final int FAVORITE = 8;//收藏
    public static final int HISTORY = 9;//历史记录
    public static final int MAX_COUNT = 9;//频道数
    @LayoutRes
    int imgRes;
    String chanlName;
    private int mChannelId;

    private int channelId;


    public Channel(int mImgRes, String mChanlName,int mChannelId) {
        imgRes = mImgRes;
        chanlName = mChanlName;
        this.mChannelId = mChannelId;
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

    public Channel setChanlName(String mChanlName) {
        chanlName = mChanlName;
        return this;
    }

    public static List<Channel> getChannelList() {
        List<Channel> mChannels = new ArrayList<>();
        mChannels.add(new Channel(R.drawable.ic_movie, "电影",MOVIE));
        mChannels.add(new Channel(R.drawable.ic_documentary, "纪录片",DOCUMENTRY));
        mChannels.add(new Channel(R.drawable.ic_comic, "动漫",COMIC));
        mChannels.add(new Channel(R.drawable.ic_music, "音乐",MUSIC));
        mChannels.add(new Channel(R.drawable.ic_show, "电视剧",SHOW));
        mChannels.add(new Channel(R.drawable.ic_variety, "综艺",VARIETY));
        mChannels.add(new Channel(R.drawable.ic_live, "直播",LIVE));
        mChannels.add(new Channel(R.drawable.ic_bookmark, "收藏",FAVORITE));
        mChannels.add(new Channel(R.drawable.ic_history, "历史记录",HISTORY));
        return mChannels;
    }

    public int getChannelId() {
        return channelId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imgRes);
        dest.writeString(this.chanlName);
        dest.writeInt(this.channelId);
    }

    protected Channel(Parcel in) {
        this.imgRes = in.readInt();
        this.chanlName = in.readString();
        this.channelId = in.readInt();
    }

    public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
