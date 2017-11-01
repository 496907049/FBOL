package com.shuwo.fbol.bean;

import java.util.List;

/**
 * Created by asus01 on 2017/10/17.
 */

public class LiveBroadCast {

    private String date;

    private int awaySupport;

    private String sinaMatchId;

    private boolean top;

    private String awayScore;

    private String homeScore;

    private boolean history;

    private String timeString;

    private int homeSupport;

    private String milliseconds;

    private boolean custom;

    private boolean get258;

    private int newsStatus;

    private String leagueName;

    private String awayTeamLogo;

    private int type;

    private boolean liveLiteral;

    private String awayTeamId;

    private String homeTeamLogo;

    private String homeTeamId;

    private String awayTeamName;

    private String homeTeamName;

    private String gameId;

    private String beginTime;

    private int league;

    private boolean hot;

    private int visit;

    private String desc;

    private String Title;

    private String s;

    private String t;

    private String channelNames;

    private boolean onFire;

    private int status;

    private String leagueType;

    private String sinaMatchDataUrl;

    private boolean hasJC;

    private String officialNum;

    private int typeStatus;

    private String backGround;

    //    private LiveChannels LiveChannels;
    private List<LiveChannels> liveChannels;

    private List<BackLooks> backLooks;

    public List<LiveChannels> getLiveChannels() {
        return liveChannels;
    }

    public void setLiveChannels(List<LiveChannels> liveChannels) {
        this.liveChannels = liveChannels;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setAwaySupport(int awaySupport){
        this.awaySupport = awaySupport;
    }
    public int getAwaySupport(){
        return this.awaySupport;
    }
    public void setSinaMatchId(String sinaMatchId){
        this.sinaMatchId = sinaMatchId;
    }
    public String getSinaMatchId(){
        return this.sinaMatchId;
    }
    public void setTop(boolean top){
        this.top = top;
    }
    public boolean getTop(){
        return this.top;
    }
    public void setAwayScore(String awayScore){
        this.awayScore = awayScore;
    }
    public String getAwayScore(){
        return this.awayScore;
    }
    public void setHomeScore(String homeScore){
        this.homeScore = homeScore;
    }
    public String getHomeScore(){
        return this.homeScore;
    }
    public void setHistory(boolean history){
        this.history = history;
    }
    public boolean getHistory(){
        return this.history;
    }
    public void setTimeString(String timeString){
        this.timeString = timeString;
    }
    public String getTimeString(){
        return this.timeString;
    }
    public void setHomeSupport(int homeSupport){
        this.homeSupport = homeSupport;
    }
    public int getHomeSupport(){
        return this.homeSupport;
    }
    public void setCustom(boolean custom){
        this.custom = custom;
    }
    public boolean getCustom(){
        return this.custom;
    }
    public void setGet258(boolean get258){
        this.get258 = get258;
    }
    public boolean getGet258(){
        return this.get258;
    }
    public void setNewsStatus(int newsStatus){
        this.newsStatus = newsStatus;
    }
    public int getNewsStatus(){
        return this.newsStatus;
    }
    public void setLeagueName(String leagueName){
        this.leagueName = leagueName;
    }
    public String getLeagueName(){
        return this.leagueName;
    }
    public void setAwayTeamLogo(String awayTeamLogo){
        this.awayTeamLogo = awayTeamLogo;
    }
    public String getAwayTeamLogo(){
        return this.awayTeamLogo;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setLiveLiteral(boolean liveLiteral){
        this.liveLiteral = liveLiteral;
    }
    public boolean getLiveLiteral(){
        return this.liveLiteral;
    }
    public void setAwayTeamId(String awayTeamId){
        this.awayTeamId = awayTeamId;
    }
    public String getAwayTeamId(){
        return this.awayTeamId;
    }
    public void setHomeTeamLogo(String homeTeamLogo){
        this.homeTeamLogo = homeTeamLogo;
    }
    public String getHomeTeamLogo(){
        return this.homeTeamLogo;
    }
    public void setHomeTeamId(String homeTeamId){
        this.homeTeamId = homeTeamId;
    }
    public String getHomeTeamId(){
        return this.homeTeamId;
    }
    public void setAwayTeamName(String awayTeamName){
        this.awayTeamName = awayTeamName;
    }
    public String getAwayTeamName(){
        return this.awayTeamName;
    }
    public void setHomeTeamName(String homeTeamName){
        this.homeTeamName = homeTeamName;
    }
    public String getHomeTeamName(){
        return this.homeTeamName;
    }
    public void setGameId(String gameId){
        this.gameId = gameId;
    }
    public String getGameId(){
        return this.gameId;
    }
    public void setLeague(int league){
        this.league = league;
    }
    public int getLeague(){
        return this.league;
    }
    public void setHot(boolean hot){
        this.hot = hot;
    }
    public boolean getHot(){
        return this.hot;
    }
    public void setVisit(int visit){
        this.visit = visit;
    }
    public int getVisit(){
        return this.visit;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getTitle(){
        return this.Title;
    }
    public void setS(String s){
        this.s = s;
    }
    public String getS(){
        return this.s;
    }
    public void setT(String t){
        this.t = t;
    }
    public String getT(){
        return this.t;
    }
    public void setChannelNames(String channelNames){
        this.channelNames = channelNames;
    }
    public String getChannelNames(){
        return this.channelNames;
    }
    public void setOnFire(boolean onFire){
        this.onFire = onFire;
    }
    public boolean getOnFire(){
        return this.onFire;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setLeagueType(String leagueType){
        this.leagueType = leagueType;
    }
    public String getLeagueType(){
        return this.leagueType;
    }
    public void setSinaMatchDataUrl(String sinaMatchDataUrl){
        this.sinaMatchDataUrl = sinaMatchDataUrl;
    }
    public String getSinaMatchDataUrl(){
        return this.sinaMatchDataUrl;
    }
    public void setHasJC(boolean hasJC){
        this.hasJC = hasJC;
    }
    public boolean getHasJC(){
        return this.hasJC;
    }
    public void setOfficialNum(String officialNum){
        this.officialNum = officialNum;
    }
    public String getOfficialNum(){
        return this.officialNum;
    }
    public void setTypeStatus(int typeStatus){
        this.typeStatus = typeStatus;
    }
    public int getTypeStatus(){
        return this.typeStatus;
    }
    public void setBackGround(String backGround){
        this.backGround = backGround;
    }
    public String getBackGround(){
        return this.backGround;
    }
    public boolean isTop() {
        return top;
    }

    public boolean isHistory() {
        return history;
    }

    public boolean isCustom() {
        return custom;
    }

    public boolean isGet258() {
        return get258;
    }

    public boolean isLiveLiteral() {
        return liveLiteral;
    }

    public boolean isHot() {
        return hot;
    }

    public boolean isOnFire() {
        return onFire;
    }

    public boolean isHasJC() {
        return hasJC;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public List<BackLooks> getBackLooks() {
        return backLooks;
    }

    public void setBackLooks(List<BackLooks> backLooks) {
        this.backLooks = backLooks;
    }
}
