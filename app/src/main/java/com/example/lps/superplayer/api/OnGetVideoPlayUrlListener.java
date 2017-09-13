package com.example.lps.superplayer.api;

import com.example.lps.superplayer.model.souhu.Video;

/**
 * Created by lps on 2017/9/13.
 *
 * @version 1
 * @see
 * @since 2017/9/13 15:00
 */


public interface OnGetVideoPlayUrlListener {

    void onGetSuperUrl(Video video, String url);//超清url

    void onGetNoramlUrl(Video video, String url);//标清url

    void onGetHighUrl(Video video, String url);//高清url

    void onError(Exception e);
}
