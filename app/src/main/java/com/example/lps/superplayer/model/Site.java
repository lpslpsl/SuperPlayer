package com.example.lps.superplayer.model;

/**
 * Created by lps on 2017/8/16.
 *
 * @version 1
 * @see
 * @since 2017/8/16 10:38
 */


public class Site {
    public static final int SOHU = 1;
    public static final int LETV = 2;

    private int siteId;
    private String siteName;
    public static final int MAX_SITE = 2;

    public Site(int id) {
        siteId = id;
        switch (siteId) {
            case SOHU:
                siteName = "搜狐视频";
                break;
            case LETV:
                siteName = "乐视视频";
                break;
        }
    }

    public int getSiteId() {
        return siteId;
    }
}
