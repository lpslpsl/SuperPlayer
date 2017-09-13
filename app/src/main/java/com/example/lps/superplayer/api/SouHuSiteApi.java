package com.example.lps.superplayer.api;

import android.text.TextUtils;
import android.util.Log;

import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;
import com.example.lps.superplayer.model.souhu.SoHuAlbum;
import com.example.lps.superplayer.model.souhu.SoHuAlbumDetail;
import com.example.lps.superplayer.model.souhu.Video;
import com.example.lps.superplayer.model.souhu.VideoData;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:15
 */


public class SouHuSiteApi extends BaseSiteApi {
    private static final String TAG = "SouHuSiteApi";
    private static final int SOHU_CHANNELID_MOVIE = 1; //搜狐电影频道ID
    private static final int SOHU_CHANNELID_SERIES = 2; //搜狐电视剧频道ID
    private static final int SOHU_CHANNELID_VARIETY = 7; //搜狐综艺频道ID
    private static final int SOHU_CHANNELID_DOCUMENTRY = 8; //搜狐纪录片频道ID
    private static final int SOHU_CHANNELID_COMIC = 16; //搜狐动漫频道ID
    private static final int SOHU_CHANNELID_MUSIC = 24; //搜狐音乐频道ID

    //某一专辑详情
    //http://api.tv.sohu.com/v4/album/info/9112373.json?plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47
    private final static String API_KEY = "plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";
    private final static String API_ALBUM_INFO = "http://api.tv.sohu.com/v4/album/info/";
    //http://api.tv.sohu.com/v4/search/channel.json?cid=2&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47&page=1&page_size=1
    private final static String API_CHANNEL_ALBUM_FORMAT = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=%s&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&" +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";
    //http://api.tv.sohu.com/v4/album/videos/9112373.json?page=1&page_size=50&order=0&site=1&with_trailer=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47
    private final static String API_ALBUM_VIDOES_FORMAT = "http://api.tv.sohu.com/v4/album/videos/%s.json?page=%s&page_size=%s&order=0&site=1&with_trailer=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";
    // 播放url
    //http://api.tv.sohu.com/v4/video/info/3669315.json?site=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=4.5.1&sysver=4.4.2&partner=47&aid=9112373
    private final static String API_VIDEO_PLAY_URL_FORMAT = "http://api.tv.sohu.com/v4/video/info/%s.json?site=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=4.5.1&sysver=4.4.2&partner=47&aid=%s";

    //真实url格式 m3u8
    //http://hot.vrs.sohu.com/ipad3669271_4603585256668_6870592.m3u8?plat=6uid=f5dbc7b40dad477c8516885f6c681c01&pt=5&prod=app&pg=1
    @Override
    void onGetChannelAlbums(Channel channel, int page, int pagecount, ApiCallBack mCallBack) {
        String url = String.format(API_CHANNEL_ALBUM_FORMAT, toConvertChannelId(channel), page, pagecount);
        doGetChannelAlbumsByUrl(url, mCallBack);
    }

    @Override
    public void onGetAlbumDetail(final Album mAlbum, final ApiCallBack mCallBack) {
        String url = API_ALBUM_INFO + mAlbum.getAlbumId() + ".json?" + API_KEY;
        OkHttpUtils.get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mCallBack.onFail(e);
            }

            @Override
            public void onResponse(String response, int id) {
                Gson mGson = new Gson();
                SoHuAlbumDetail mSoHuAlbumDetail = mGson.fromJson(response, SoHuAlbumDetail.class);
                if (mSoHuAlbumDetail.getData() != null) {
                    if (mSoHuAlbumDetail.getData().getLatest_video_count() > 0) {
                        mAlbum.setVideoTotal(mSoHuAlbumDetail.getData().getLatest_video_count());
                    } else {
                        mAlbum.setVideoTotal(mSoHuAlbumDetail.getData().getTotal_video_count());
                    }

                }
                mCallBack.onsuccess(mAlbum);
            }
        });
    }

    @Override
    public void ongetVideo(int mPagenum, Album mAlbum, final ApiCallBack mCallBack) {
        int pagesize = 50;
        String url = String.format(API_ALBUM_VIDOES_FORMAT, mAlbum.getAlbumId(), mPagenum, pagesize);
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mCallBack.onFail(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson mGson = new Gson();
                        VideoData mVideoData = mGson.fromJson(response, VideoData.class);
                        if (mVideoData != null) {
                            ArrayList<Video> mVideos = new ArrayList<Video>();
                            for (Video mVideo : mVideoData.getData().getVideos()) {
                                Video video = new Video();
                                video.setHorHighPic(mVideo.getHorHighPic());
                                video.setVerHighPic(mVideo.getVerHighPic());
                                video.setVid(mVideo.getVid());
                                video.setAid(mVideo.getAid());
                                video.setVideoName(mVideo.getVideoName());
                                mVideos.add(video);
                            }
                            mCallBack.onsuccess(mVideos);
                        }
                    }
                });
    }

    @Override
    public void onGetPlayVideoUrl(final Video mVideo, final OnGetVideoPlayUrlListener mApiCallBack) {
        String url = String.format(API_VIDEO_PLAY_URL_FORMAT, mVideo.getVid(), mVideo.getAid());
        OkHttpUtils.get()
                .url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mApiCallBack.onError(e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject result = new JSONObject(response);
                    JSONObject mData = result.optJSONObject("data");
                    String nor_url = mData.optString("url_nor");
                    if (!TextUtils.isEmpty(nor_url)) {
                        nor_url += "uid" + getUUid() + "&pt=5&prod=app&pg=1";
                        mVideo.setNormalUrl(nor_url);
                        mApiCallBack.onGetNoramlUrl(mVideo, nor_url);
                    }
                    String superurl = mData.optString("url_super");
                    if (!TextUtils.isEmpty(superurl)) {
                        superurl += "uid" + getUUid() + "&pt=5&prod=app&pg=1";
                        mVideo.setSuperUrl(superurl);
                        mApiCallBack.onGetSuperUrl(mVideo, superurl);
                    }
                    String highurl = mData.optString("url_high");
                    if (!TextUtils.isEmpty(highurl)) {
                        highurl += "uid" + getUUid() + "&pt=5&prod=app&pg=1";
                        mVideo.setHighUrl(highurl);
                        mApiCallBack.onGetHighUrl(mVideo, highurl);
                    }
                } catch (JSONException mE) {
                    mE.printStackTrace();
                }
            }
        });
    }

    private void doGetChannelAlbumsByUrl(String mUrl, final ApiCallBack mCallBack) {
        OkHttpUtils.get()
                .url(mUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mCallBack.onFail(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson mGson = new Gson();
                        SoHuAlbum mSoHuAlbum = mGson.fromJson(response, SoHuAlbum.class);
                        List<Album> mAlbumList = new ArrayList<Album>();
                        for (SoHuAlbum.DataBean.VideosBean data : mSoHuAlbum.getData().getVideos()) {
                            Album mAlbum = new Album(Site.SOHU);
                            mAlbum.setAlbumDesc(data.getAlbum_desc());
                            mAlbum.setAlbumId(data.getAid() + "");
                            mAlbum.setMainActor(data.getMain_actor());
                            mAlbum.setTip(data.getTip());
                            mAlbum.setTitle(data.getAlbum_name());
                            mAlbum.setDirector(data.getDirector());
                            mAlbum.setHorImgUrl(data.getHor_big_pic());
                            mAlbumList.add(mAlbum);
                        }
                        mCallBack.onsuccess(mAlbumList);
                    }
                });
    }

    private int toConvertChannelId(Channel mChannel) {
        int channelId = -1;
        switch (mChannel.getChannelId()) {
            case Channel.SHOW:
                channelId = SOHU_CHANNELID_SERIES;
                break;
            case Channel.MOVIE:
                channelId = SOHU_CHANNELID_MOVIE;
                break;
            case Channel.COMIC:
                channelId = SOHU_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = SOHU_CHANNELID_MUSIC;
                break;
            case Channel.DOCUMENTRY:
                channelId = SOHU_CHANNELID_DOCUMENTRY;
                break;
            case Channel.VARIETY:
                channelId = SOHU_CHANNELID_VARIETY;
                break;
        }
        return channelId;
    }

    public String getUUid() {
        UUID mUUID = UUID.randomUUID();
        return mUUID.toString().replace("-", "");
    }
}
