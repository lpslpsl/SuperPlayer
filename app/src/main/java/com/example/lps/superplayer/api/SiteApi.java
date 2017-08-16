package com.example.lps.superplayer.api;

import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:16
 */


public class SiteApi {
    public static final int PAGE_COUNT=20;
    public void onGetChannelAlbums(int mMsiteId, Channel mMchannel, int page, ApiCallBack mCallBack){
        switch (mMsiteId){
            case Site.LETV:
new LeTvSiteApi().onGetChannelAlbums(mMchannel,page,PAGE_COUNT,mCallBack);
                break;
            case Site.SOHU:
                new SouHuSiteApi().onGetChannelAlbums(mMchannel,page,PAGE_COUNT,mCallBack);

                break;
        }

    }
}
