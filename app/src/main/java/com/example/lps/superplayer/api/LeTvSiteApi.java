package com.example.lps.superplayer.api;

import android.util.Log;

import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Channel;
import com.example.lps.superplayer.model.Site;
import com.example.lps.superplayer.model.letv.LeTvAlbum;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:15
 */


public class LeTvSiteApi extends BaseSiteApi {
    private static final String TAG = "LeTvSiteApi";
    private static final int LETV_CHANNELID_MOVIE = 1; //乐视电影频道ID
    private static final int LETV_CHANNELID_SERIES = 2; //乐视电视剧频道ID
    private static final int LETV_CHANNELID_VARIETY = 11; //乐视综艺频道ID
    private static final int LETV_CHANNELID_DOCUMENTRY = 16; //乐视纪录片频道ID
    private static final int LETV_CHANNELID_COMIC = 5; //乐视动漫频道ID
    private static final int LETV_CHANNELID_MUSIC = 9; //乐视音乐频道ID
    private static final int BITSTREAM_SUPER = 100;
    private static final int BITSTREAM_NORMAL = 101;
    private static final int BITSTREAM_HIGH = 102;
    private final static String ALBUM_LIST_URL_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/ph/420003,420004/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    private final static String ALBUM_LIST_URL_DOCUMENTARY_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/3/ph/420003,420004/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    private final static String ALBUM_LIST_URL_SHOW_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    //http://static.meizi.app.m.letv.com/android/mod/mob/ctl/album/act/detail/id/10026309/pcode/010410000/version/2.1.mindex.html
    private final static String ALBUM_DESC_URL_FORMAT = "http://static.meizi.app.m.letv.com/" +
            "android/mod/mob/ctl/album/act/detail/id/%s/pcode/010410000/version/2.1.mindex.html";
    //key : bh65OzqYYYmHRQ
    private final static String SEVER_TIME_URL = "http://dynamic.meizi.app.m.letv.com/android/dynamic.php?mod=mob&ctl=timestamp&act=timestamp&pcode=010410000&version=5.4";

    //http://static.app.m.letv.com/android/mod/mob/ctl/videolist/act/detail/id/10026309/vid/0/b/1/s/30/o/-1/m/1/pcode/010410000/version/2.1.mindex.html
    private final static String ALBUM_VIDEOS_URL_FORMAT = "http://static.app.m.letv.com/" +
            "android/mod/mob/ctl/videolist/act/detail/id/%s/vid/0/b/%s/s/%s/o/%s/m/%s/pcode/010410000/version/2.1.mindex.html";

    //arg: mmsid currentServerTime key vid
    private final static String VIDEO_FILE_URL_FORMAT = "http://dynamic.meizi.app.m.letv.com/android/dynamic.php?mmsid=" +
            "%s&playid=0&tss=ios&pcode=010410000&version=2.1&tm=%s&key=%s&vid=" +
            "%s&ctl=videofile&mod=minfo&act=index";

    private final static String VIDEO_REAL_LINK_APPENDIX = "&format=1&expect=1&termid=2&pay=0&ostype=android&hwtype=iphone";



    @Override
    void onGetChannelAlbums(Channel channel, int page, int pagecount, ApiCallBack mCallBack) {
        String url =  getChannelAlbumUrl(channel,page, pagecount);
        doGetChannelAlbumsByUrl(url, mCallBack);
    }

    private void doGetChannelAlbumsByUrl(String mUrl, final ApiCallBack mCallBack) {

        OkHttpUtils.get()
                .url(mUrl)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "onError() called with: call = [" + call + "], e = [" + e + "], id = [" + id + "]");
            }

            @Override
            public void onResponse(String response, int id) {
                        Gson mGson=new Gson();
                LeTvAlbum mLeTvAlbum = mGson.fromJson(response, LeTvAlbum.class);
                List<Album> mAlbumList=new ArrayList<Album>();
                for (LeTvAlbum.BodyBean.AlbumListBean data:mLeTvAlbum.getBody().getAlbum_list()){
                    Album mAlbum=new Album(Site.LETV);
                    mAlbum.setAlbumDesc(data.getSubname());
                    mAlbum.setAlbumId(data.getAid());
                    mAlbum.setTip(data.getSubname());
                    mAlbum.setTitle(data.getName());
                    mAlbum.setHorImgUrl(data.getImages().getBigimg());
                    mAlbumList.add(mAlbum);
                }

               mCallBack.onsuccess(mAlbumList);


            }
        });
    }

    private String getChannelAlbumUrl(Channel channel, int pageNo, int pageSize) {
        if (channel.getChannelId() == Channel.DOCUMENTRY) {
            return String.format(ALBUM_LIST_URL_DOCUMENTARY_FORMAT, conVertChannleId(channel), pageNo, pageSize);
        } else if (channel.getChannelId() == Channel.SHOW) {
            return String.format(ALBUM_LIST_URL_SHOW_FORMAT, conVertChannleId(channel), pageNo, pageSize);
        }
        return String.format(ALBUM_LIST_URL_FORMAT, conVertChannleId(channel), pageNo, pageSize);
    }
    private int conVertChannleId(Channel channel) {
        int channelId = -1;//-1 无效值
        switch (channel.getChannelId()) {
            case Channel.SHOW:
                channelId = LETV_CHANNELID_SERIES;
                break;
            case Channel.MOVIE:
                channelId = LETV_CHANNELID_MOVIE;
                break;
            case Channel.COMIC:
                channelId = LETV_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = LETV_CHANNELID_MUSIC;
                break;
            case Channel.DOCUMENTRY:
                channelId = LETV_CHANNELID_DOCUMENTRY;
                break;
            case Channel.VARIETY:
                channelId = LETV_CHANNELID_VARIETY;
                break;
        }
        return channelId;
    }
}
