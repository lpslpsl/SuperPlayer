package com.example.lps.superplayer.api;

import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.souhu.Video;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:13
 */


public abstract class BaseSiteApi {
  abstract   void onGetChannelAlbums(Channel channel, int page, int pagecount, ApiCallBack mCallBack);


  public abstract void onGetAlbumDetail(Album mAlbum, ApiCallBack mCallBack);
  public abstract void ongetVideo(int mPagenum, Album mAlbum, ApiCallBack mCallBack);


    public abstract void onGetPlayVideoUrl(Video mVideo, OnGetVideoPlayUrlListener mApiCallBack);
}
