package com.shuwo.fbol.bean;

import java.util.List;

/**
 * Created by asus01 on 2017/10/17.
 */

public class HighLights {

    private String summary;

    private boolean hasVideo;

    private boolean redirect;

    private String image;

    private int read;

    private boolean original;

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    private String pubTime;

    private String index;

    private boolean lottery;

    private String source;

    private int priority;

    private String title;

    private String url;

    private String leagueName;

    private String newsId;

    private int topicId;

    private boolean top;

    private String playType;

    private int leagueId;

    private int comment;

    private int category;

    private boolean first;

    private String hash;

    private List<Videos> videos;

    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }
    public void setHasVideo(boolean hasVideo){
        this.hasVideo = hasVideo;
    }
    public boolean getHasVideo(){
        return this.hasVideo;
    }
    public void setRedirect(boolean redirect){
        this.redirect = redirect;
    }
    public boolean getRedirect(){
        return this.redirect;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setRead(int read){
        this.read = read;
    }
    public int getRead(){
        return this.read;
    }
    public void setOriginal(boolean original){
        this.original = original;
    }
    public boolean getOriginal(){
        return this.original;
    }
    public void setLottery(boolean lottery){
        this.lottery = lottery;
    }
    public boolean getLottery(){
        return this.lottery;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public int getPriority(){
        return this.priority;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setLeagueName(String leagueName){
        this.leagueName = leagueName;
    }
    public String getLeagueName(){
        return this.leagueName;
    }
    public void setNewsId(String newsId){
        this.newsId = newsId;
    }
    public String getNewsId(){
        return this.newsId;
    }
    public void setTopicId(int topicId){
        this.topicId = topicId;
    }
    public int getTopicId(){
        return this.topicId;
    }
    public void setTop(boolean top){
        this.top = top;
    }
    public boolean getTop(){
        return this.top;
    }
    public void setPlayType(String playType){
        this.playType = playType;
    }
    public String getPlayType(){
        return this.playType;
    }
    public void setLeagueId(int leagueId){
        this.leagueId = leagueId;
    }
    public int getLeagueId(){
        return this.leagueId;
    }
    public void setComment(int comment){
        this.comment = comment;
    }
    public int getComment(){
        return this.comment;
    }
    public void setCategory(int category){
        this.category = category;
    }
    public int getCategory(){
        return this.category;
    }
    public void setFirst(boolean first){
        this.first = first;
    }
    public boolean getFirst(){
        return this.first;
    }
    public void setHash(String hash){
        this.hash = hash;
    }
    public String getHash(){
        return this.hash;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
    public boolean isHasVideo() {
        return hasVideo;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public boolean isOriginal() {
        return original;
    }

    public boolean isLottery() {
        return lottery;
    }

    public boolean isTop() {
        return top;
    }

    public boolean isFirst() {
        return first;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

}
