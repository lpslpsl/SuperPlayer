package com.example.lps.superplayer.api;

import android.content.Context;

import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;
import com.example.lps.superplayer.model.souhu.Video;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:16
 */


public class SiteApi {
    public static final int PAGE_COUNT = 20;

    public void onGetChannelAlbums(int mMsiteId, Channel mMchannel, int page, ApiCallBack mCallBack) {
        switch (mMsiteId) {
            case Site.LETV:
                new LeTvSiteApi().onGetChannelAlbums(mMchannel, page, PAGE_COUNT, mCallBack);
                break;
            case Site.SOHU:
                new SouHuSiteApi().onGetChannelAlbums(mMchannel, page, PAGE_COUNT, mCallBack);

                break;
        }

    }
    public static void onGetAlbumDetail( Album mAlbum,ApiCallBack mCallBack){
        switch (mAlbum.getSite().getSiteId()){
            case Site.LETV:
                new LeTvSiteApi().onGetAlbumDetail(mAlbum,mCallBack);
                break;
            case Site.SOHU:
                new SouHuSiteApi().onGetAlbumDetail(mAlbum,mCallBack);
                break;
        }
    }

    public static void ongetVideo(int mPagenum, Album mAlbum, ApiCallBack mApiCallBack) {
        switch (mAlbum.getSite().getSiteId()){
            case Site.LETV:
                new LeTvSiteApi().ongetVideo(mPagenum,mAlbum,mApiCallBack);
                break;
            case Site.SOHU:
                new SouHuSiteApi().ongetVideo(mPagenum,mAlbum,mApiCallBack);
                break;
        }
    }

    public static void onGetPlayVideoUrl(int siteid,Video mVideo, OnGetVideoPlayUrlListener mApiCallBack) {
        switch (siteid){
            case Site.LETV:
                new LeTvSiteApi().onGetPlayVideoUrl(mVideo,mApiCallBack);
                break;
            case Site.SOHU:
                new SouHuSiteApi().onGetPlayVideoUrl(mVideo,mApiCallBack);
                break;
        }
    }
}
