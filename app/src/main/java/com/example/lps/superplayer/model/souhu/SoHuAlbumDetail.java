package com.example.lps.superplayer.model.souhu;

import java.util.List;

/**
 * Created by lps on 2017/9/11.
 *
 * @version 1
 * @see
 * @since 2017/9/11 16:38
 */


public class SoHuAlbumDetail {

    /**
     * status : 200
     * statusText : OK
     * data : {"actor":"吕良伟,陶虹,关咏荷,牛青峰","aid":9386370,"album_desc":"清嘉庆帝在位四年有心勤政，无奈太上皇乾隆诸多干涉，新政难展。奸臣和珅知悉乾隆病危，巧计讹乾隆立下免死敕旨，广结群党，欲篡位自尊。嘉庆四面受敌，不由得想起既敬且畏的二兄永硕。喜爱摔跤，饮酒，好管闲事，四处云游的他，此时不知身在何方。其实乾隆十分喜爱永硕，曾有意将他立为皇储。但他不愿当皇帝，把皇位让给了弟弟嘉庆。乾隆很想看到永硕娶妻，嘉庆贴出皇榜为二哥招亲，闹得京城沸腾。此事引起一位神秘蒙面人的关注。","album_name":"褡裢王爷","album_publish_time":"2017-08-17","album_sub_name":"","alias_name":"至尊计上计","area":"内地剧","area_id":6,"cate_code":"101;101104;101106","cid":2,"crid":0,"data_type":1,"director":"招振强","download_type":1,"effective":1,"fee":0,"fee_month":1,"hor_big_pic":"http://photocdn.sohu.com/20170817/vrsab_hor9386370.jpg","hor_high_pic":"http://photocdn.sohu.com/20170817/vrsa_hor9386370_54G23_pic25.jpg","hor_origin_pic":"http://photocdn.sohu.com/20170817/vrsa_hor9386370.jpg","hor_w16_pic":"http://photocdn.sohu.com/20170817/vrsa_hor9386370_x1JUZ_pic29.jpg","hor_w8_pic":"http://photocdn.sohu.com/20170817/vrsa_hor9386370_3kcZ3_pic28.jpg","ip_limit":1,"isRecording":1,"isUpdateFinish":0,"isVirtualAlbum":0,"is_download":1,"is_original_code":1,"is_show_title":0,"is_titbits":0,"is_trailer":0,"language":"普通话","latest_video_count":32,"main_actor":"吕良伟,陶虹,关咏荷,牛青峰","mobile_limit":0,"original_album_url":"http://tv.sohu.com/s2017/dsjtlwy/","original_work":"","pay_type":[0],"play_count":0,"score":7.1,"second_cate_name":"言情剧;古装剧","site":1,"total_video_count":32,"trailer_aid":0,"tv_is_danmu":0,"tv_is_early":0,"updateNotification":"32集全","ver_big_pic":"http://photocdn.sohu.com/20170817/vrsab_ver9386370.jpg","ver_high_pic":"http://photocdn.sohu.com/20170817/vrsa_ver9386370_4yU73_pic26.jpg","ver_w12_pic":"http://photocdn.sohu.com/20170817/vrsa_ver9386370.jpg","year":"2005"}
     */

    private int status;
    private String statusText;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * actor : 吕良伟,陶虹,关咏荷,牛青峰
         * aid : 9386370
         * album_desc : 清嘉庆帝在位四年有心勤政，无奈太上皇乾隆诸多干涉，新政难展。奸臣和珅知悉乾隆病危，巧计讹乾隆立下免死敕旨，广结群党，欲篡位自尊。嘉庆四面受敌，不由得想起既敬且畏的二兄永硕。喜爱摔跤，饮酒，好管闲事，四处云游的他，此时不知身在何方。其实乾隆十分喜爱永硕，曾有意将他立为皇储。但他不愿当皇帝，把皇位让给了弟弟嘉庆。乾隆很想看到永硕娶妻，嘉庆贴出皇榜为二哥招亲，闹得京城沸腾。此事引起一位神秘蒙面人的关注。
         * album_name : 褡裢王爷
         * album_publish_time : 2017-08-17
         * album_sub_name :
         * alias_name : 至尊计上计
         * area : 内地剧
         * area_id : 6
         * cate_code : 101;101104;101106
         * cid : 2
         * crid : 0
         * data_type : 1
         * director : 招振强
         * download_type : 1
         * effective : 1
         * fee : 0
         * fee_month : 1
         * hor_big_pic : http://photocdn.sohu.com/20170817/vrsab_hor9386370.jpg
         * hor_high_pic : http://photocdn.sohu.com/20170817/vrsa_hor9386370_54G23_pic25.jpg
         * hor_origin_pic : http://photocdn.sohu.com/20170817/vrsa_hor9386370.jpg
         * hor_w16_pic : http://photocdn.sohu.com/20170817/vrsa_hor9386370_x1JUZ_pic29.jpg
         * hor_w8_pic : http://photocdn.sohu.com/20170817/vrsa_hor9386370_3kcZ3_pic28.jpg
         * ip_limit : 1
         * isRecording : 1
         * isUpdateFinish : 0
         * isVirtualAlbum : 0
         * is_download : 1
         * is_original_code : 1
         * is_show_title : 0
         * is_titbits : 0
         * is_trailer : 0
         * language : 普通话
         * latest_video_count : 32
         * main_actor : 吕良伟,陶虹,关咏荷,牛青峰
         * mobile_limit : 0
         * original_album_url : http://tv.sohu.com/s2017/dsjtlwy/
         * original_work :
         * pay_type : [0]
         * play_count : 0
         * score : 7.1
         * second_cate_name : 言情剧;古装剧
         * site : 1
         * total_video_count : 32
         * trailer_aid : 0
         * tv_is_danmu : 0
         * tv_is_early : 0
         * updateNotification : 32集全
         * ver_big_pic : http://photocdn.sohu.com/20170817/vrsab_ver9386370.jpg
         * ver_high_pic : http://photocdn.sohu.com/20170817/vrsa_ver9386370_4yU73_pic26.jpg
         * ver_w12_pic : http://photocdn.sohu.com/20170817/vrsa_ver9386370.jpg
         * year : 2005
         */

        private String actor;
        private int aid;
        private String album_desc;
        private String album_name;
        private String album_publish_time;
        private String album_sub_name;
        private String alias_name;
        private String area;
        private int area_id;
        private String cate_code;
        private int cid;
        private int crid;
        private int data_type;
        private String director;
        private int download_type;
        private int effective;
        private int fee;
        private int fee_month;
        private String hor_big_pic;
        private String hor_high_pic;
        private String hor_origin_pic;
        private String hor_w16_pic;
        private String hor_w8_pic;
        private int ip_limit;
        private int isRecording;
        private int isUpdateFinish;
        private int isVirtualAlbum;
        private int is_download;
        private int is_original_code;
        private int is_show_title;
        private int is_titbits;
        private int is_trailer;
        private String language;
        private int latest_video_count;
        private String main_actor;
        private int mobile_limit;
        private String original_album_url;
        private String original_work;
        private int play_count;
        private double score;
        private String second_cate_name;
        private int site;
        private int total_video_count;
        private int trailer_aid;
        private int tv_is_danmu;
        private int tv_is_early;
        private String updateNotification;
        private String ver_big_pic;
        private String ver_high_pic;
        private String ver_w12_pic;
        private String year;
        private List<Integer> pay_type;

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getAlbum_desc() {
            return album_desc;
        }

        public void setAlbum_desc(String album_desc) {
            this.album_desc = album_desc;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getAlbum_publish_time() {
            return album_publish_time;
        }

        public void setAlbum_publish_time(String album_publish_time) {
            this.album_publish_time = album_publish_time;
        }

        public String getAlbum_sub_name() {
            return album_sub_name;
        }

        public void setAlbum_sub_name(String album_sub_name) {
            this.album_sub_name = album_sub_name;
        }

        public String getAlias_name() {
            return alias_name;
        }

        public void setAlias_name(String alias_name) {
            this.alias_name = alias_name;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getCate_code() {
            return cate_code;
        }

        public void setCate_code(String cate_code) {
            this.cate_code = cate_code;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getCrid() {
            return crid;
        }

        public void setCrid(int crid) {
            this.crid = crid;
        }

        public int getData_type() {
            return data_type;
        }

        public void setData_type(int data_type) {
            this.data_type = data_type;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getDownload_type() {
            return download_type;
        }

        public void setDownload_type(int download_type) {
            this.download_type = download_type;
        }

        public int getEffective() {
            return effective;
        }

        public void setEffective(int effective) {
            this.effective = effective;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getFee_month() {
            return fee_month;
        }

        public void setFee_month(int fee_month) {
            this.fee_month = fee_month;
        }

        public String getHor_big_pic() {
            return hor_big_pic;
        }

        public void setHor_big_pic(String hor_big_pic) {
            this.hor_big_pic = hor_big_pic;
        }

        public String getHor_high_pic() {
            return hor_high_pic;
        }

        public void setHor_high_pic(String hor_high_pic) {
            this.hor_high_pic = hor_high_pic;
        }

        public String getHor_origin_pic() {
            return hor_origin_pic;
        }

        public void setHor_origin_pic(String hor_origin_pic) {
            this.hor_origin_pic = hor_origin_pic;
        }

        public String getHor_w16_pic() {
            return hor_w16_pic;
        }

        public void setHor_w16_pic(String hor_w16_pic) {
            this.hor_w16_pic = hor_w16_pic;
        }

        public String getHor_w8_pic() {
            return hor_w8_pic;
        }

        public void setHor_w8_pic(String hor_w8_pic) {
            this.hor_w8_pic = hor_w8_pic;
        }

        public int getIp_limit() {
            return ip_limit;
        }

        public void setIp_limit(int ip_limit) {
            this.ip_limit = ip_limit;
        }

        public int getIsRecording() {
            return isRecording;
        }

        public void setIsRecording(int isRecording) {
            this.isRecording = isRecording;
        }

        public int getIsUpdateFinish() {
            return isUpdateFinish;
        }

        public void setIsUpdateFinish(int isUpdateFinish) {
            this.isUpdateFinish = isUpdateFinish;
        }

        public int getIsVirtualAlbum() {
            return isVirtualAlbum;
        }

        public void setIsVirtualAlbum(int isVirtualAlbum) {
            this.isVirtualAlbum = isVirtualAlbum;
        }

        public int getIs_download() {
            return is_download;
        }

        public void setIs_download(int is_download) {
            this.is_download = is_download;
        }

        public int getIs_original_code() {
            return is_original_code;
        }

        public void setIs_original_code(int is_original_code) {
            this.is_original_code = is_original_code;
        }

        public int getIs_show_title() {
            return is_show_title;
        }

        public void setIs_show_title(int is_show_title) {
            this.is_show_title = is_show_title;
        }

        public int getIs_titbits() {
            return is_titbits;
        }

        public void setIs_titbits(int is_titbits) {
            this.is_titbits = is_titbits;
        }

        public int getIs_trailer() {
            return is_trailer;
        }

        public void setIs_trailer(int is_trailer) {
            this.is_trailer = is_trailer;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getLatest_video_count() {
            return latest_video_count;
        }

        public void setLatest_video_count(int latest_video_count) {
            this.latest_video_count = latest_video_count;
        }

        public String getMain_actor() {
            return main_actor;
        }

        public void setMain_actor(String main_actor) {
            this.main_actor = main_actor;
        }

        public int getMobile_limit() {
            return mobile_limit;
        }

        public void setMobile_limit(int mobile_limit) {
            this.mobile_limit = mobile_limit;
        }

        public String getOriginal_album_url() {
            return original_album_url;
        }

        public void setOriginal_album_url(String original_album_url) {
            this.original_album_url = original_album_url;
        }

        public String getOriginal_work() {
            return original_work;
        }

        public void setOriginal_work(String original_work) {
            this.original_work = original_work;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getSecond_cate_name() {
            return second_cate_name;
        }

        public void setSecond_cate_name(String second_cate_name) {
            this.second_cate_name = second_cate_name;
        }

        public int getSite() {
            return site;
        }

        public void setSite(int site) {
            this.site = site;
        }

        public int getTotal_video_count() {
            return total_video_count;
        }

        public void setTotal_video_count(int total_video_count) {
            this.total_video_count = total_video_count;
        }

        public int getTrailer_aid() {
            return trailer_aid;
        }

        public void setTrailer_aid(int trailer_aid) {
            this.trailer_aid = trailer_aid;
        }

        public int getTv_is_danmu() {
            return tv_is_danmu;
        }

        public void setTv_is_danmu(int tv_is_danmu) {
            this.tv_is_danmu = tv_is_danmu;
        }

        public int getTv_is_early() {
            return tv_is_early;
        }

        public void setTv_is_early(int tv_is_early) {
            this.tv_is_early = tv_is_early;
        }

        public String getUpdateNotification() {
            return updateNotification;
        }

        public void setUpdateNotification(String updateNotification) {
            this.updateNotification = updateNotification;
        }

        public String getVer_big_pic() {
            return ver_big_pic;
        }

        public void setVer_big_pic(String ver_big_pic) {
            this.ver_big_pic = ver_big_pic;
        }

        public String getVer_high_pic() {
            return ver_high_pic;
        }

        public void setVer_high_pic(String ver_high_pic) {
            this.ver_high_pic = ver_high_pic;
        }

        public String getVer_w12_pic() {
            return ver_w12_pic;
        }

        public void setVer_w12_pic(String ver_w12_pic) {
            this.ver_w12_pic = ver_w12_pic;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<Integer> getPay_type() {
            return pay_type;
        }

        public void setPay_type(List<Integer> pay_type) {
            this.pay_type = pay_type;
        }
    }
}
