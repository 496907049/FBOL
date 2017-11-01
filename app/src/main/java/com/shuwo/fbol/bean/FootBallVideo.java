package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/18.
 */

public class FootBallVideo {

    private int videoId;

    private String videoUrl;

    private String title;

    private String picUrl;

    private int videoLength;

    private String createTime;

    private int playTimes;

    private int initialPlayTimes;

    private int praised;

    private int praiseCount;

    private int commentCount;

    private int index;

    public void setVideoId(int videoId){
        this.videoId = videoId;
    }
    public int getVideoId(){
        return this.videoId;
    }
    public void setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl(){
        return this.videoUrl;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }
    public String getPicUrl(){
        return this.picUrl;
    }
    public void setVideoLength(int videoLength){
        this.videoLength = videoLength;
    }
    public int getVideoLength(){
        return this.videoLength;
    }
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setPlayTimes(int playTimes){
        this.playTimes = playTimes;
    }
    public int getPlayTimes(){
        return this.playTimes;
    }
    public void setInitialPlayTimes(int initialPlayTimes){
        this.initialPlayTimes = initialPlayTimes;
    }
    public int getInitialPlayTimes(){
        return this.initialPlayTimes;
    }
    public void setPraised(int praised){
        this.praised = praised;
    }
    public int getPraised(){
        return this.praised;
    }
    public void setPraiseCount(int praiseCount){
        this.praiseCount = praiseCount;
    }
    public int getPraiseCount(){
        return this.praiseCount;
    }
    public void setCommentCount(int commentCount){
        this.commentCount = commentCount;
    }
    public int getCommentCount(){
        return this.commentCount;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }

}
