package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/17.
 */

public class LiveChannels {

    private String startTime;

    private int tvid;

    private String startDate;

    private String channelName;

    private String endDate;

    private String from;

    private String endTime;

    private String url;

    private String videoType;

    private String index;

    private int ios_play_status;

    private int android_play_status;

    private int cst;

    private String android_url;

    private String ios_url;

    private String android_down;

    private String ios_down;

    private int cf;

    private int isShow;

    private int platform;
    public void setTvid(int tvid){
        this.tvid = tvid;
    }
    public int getTvid(){
        return this.tvid;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    public String getStartDate(){
        return this.startDate;
    }
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }
    public String getChannelName(){
        return this.channelName;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    public String getEndDate(){
        return this.endDate;
    }
    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return this.from;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setVideoType(String videoType){
        this.videoType = videoType;
    }
    public String getVideoType(){
        return this.videoType;
    }
    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return this.index;
    }
    public void setIos_play_status(int ios_play_status){
        this.ios_play_status = ios_play_status;
    }
    public int getIos_play_status(){
        return this.ios_play_status;
    }
    public void setAndroid_play_status(int android_play_status){
        this.android_play_status = android_play_status;
    }
    public int getAndroid_play_status(){
        return this.android_play_status;
    }
    public void setCst(int cst){
        this.cst = cst;
    }
    public int getCst(){
        return this.cst;
    }
    public void setAndroid_url(String android_url){
        this.android_url = android_url;
    }
    public String getAndroid_url(){
        return this.android_url;
    }
    public void setIos_url(String ios_url){
        this.ios_url = ios_url;
    }
    public String getIos_url(){
        return this.ios_url;
    }
    public void setAndroid_down(String android_down){
        this.android_down = android_down;
    }
    public String getAndroid_down(){
        return this.android_down;
    }
    public void setIos_down(String ios_down){
        this.ios_down = ios_down;
    }
    public String getIos_down(){
        return this.ios_down;
    }
    public void setCf(int cf){
        this.cf = cf;
    }
    public int getCf(){
        return this.cf;
    }
    public void setIsShow(int isShow){
        this.isShow = isShow;
    }
    public int getIsShow(){
        return this.isShow;
    }
    public void setPlatform(int platform){
        this.platform = platform;
    }
    public int getPlatform(){
        return this.platform;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
