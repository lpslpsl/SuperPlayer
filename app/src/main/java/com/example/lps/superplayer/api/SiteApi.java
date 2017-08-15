package com.example.lps.superplayer.api;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:16
 */


public class SiteApi {
    public static final int PAGE_COUNT=20;
    public void onGetChannelAlbums( String mMchannel,int page,ApiCallBack mCallBack){
        switch (mMchannel){
            case "letv":
new LeTvSiteApi().onGetChannelAlbums(mMchannel,page,PAGE_COUNT,mCallBack);
                break;
            case "souhu":
                new SouHuSiteApi().onGetChannelAlbums(mMchannel,page,PAGE_COUNT,mCallBack);

                break;
        }

    }
}
