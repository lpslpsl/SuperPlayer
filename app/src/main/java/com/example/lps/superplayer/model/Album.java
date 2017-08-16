package com.example.lps.superplayer.model;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:38
 */


public class Album {
    private String albumId;//专辑id
    private int videoTotal;//集数
    private String title;//专辑名称
    private String subTitle;//专辑子标题
    private String director;//导演
    private String mainActor;//主演
    private String verImgUrl;//专辑竖图
    private String horImgUrl;//专辑横图
    private String albumDesc;//专辑描述
    private Site site;//网站
    private String tip;//提示
    private boolean isCompleted;//专辑是否更新完
    private String letvStyle;//乐视特殊字段

    public Album(int mLetv) {
        site=new Site(mLetv);
    }


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String mAlbumId) {
        albumId = mAlbumId;
    }

    public int getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(int mVideoTotal) {
        videoTotal = mVideoTotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String mSubTitle) {
        subTitle = mSubTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String mDirector) {
        director = mDirector;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mMainActor) {
        mainActor = mMainActor;
    }

    public String getVerImgUrl() {
        return verImgUrl;
    }

    public void setVerImgUrl(String mVerImgUrl) {
        verImgUrl = mVerImgUrl;
    }

    public String getHorImgUrl() {
        return horImgUrl;
    }

    public void setHorImgUrl(String mHorImgUrl) {
        horImgUrl = mHorImgUrl;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String mAlbumDesc) {
        albumDesc = mAlbumDesc;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site mSite) {
        site = mSite;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String mTip) {
        tip = mTip;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean mCompleted) {
        isCompleted = mCompleted;
    }

    public String getLetvStyle() {
        return letvStyle;
    }

    public void setLetvStyle(String mLetvStyle) {
        letvStyle = mLetvStyle;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", videoTotal=" + videoTotal +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", verImgUrl='" + verImgUrl + '\'' +
                ", horImgUrl='" + horImgUrl + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", site=" + site +
                ", tip='" + tip + '\'' +
                ", isCompleted=" + isCompleted +
                ", letvStyle='" + letvStyle + '\'' +
                '}';
    }
}
