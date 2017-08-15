package com.example.lps.superplayer.api;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:13
 */


public abstract class BaseSiteApi {
  abstract   void onGetChannelAlbums(String channel,int page,int pagecount,ApiCallBack mCallBack);
}
