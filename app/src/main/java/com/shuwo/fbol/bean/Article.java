package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/13.
 */

public class Article {

    private int Id;
    private int Sid;
    private int Uid;
    private String SortTitle;
    private String UserName;
    private int CommentCount;
    private String Title;
    private String Content;
    private int Like;
    private int Creation;
    private boolean HaveLike;
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }

    public void setSid(int Sid) {
        this.Sid = Sid;
    }
    public int getSid() {
        return Sid;
    }

    public void setUid(int Uid) {
        this.Uid = Uid;
    }
    public int getUid() {
        return Uid;
    }

    public void setSortTitle(String SortTitle) {
        this.SortTitle = SortTitle;
    }
    public String getSortTitle() {
        return SortTitle;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserName() {
        return UserName;
    }

    public void setCommentCount(int CommentCount) {
        this.CommentCount = CommentCount;
    }
    public int getCommentCount() {
        return CommentCount;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getTitle() {
        return Title;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    public String getContent() {
        return Content;
    }

    public void setLike(int Like) {
        this.Like = Like;
    }
    public int getLike() {
        return Like;
    }

    public void setCreation(int Creation) {
        this.Creation = Creation;
    }
    public int getCreation() {
        return Creation;
    }

    public void setHaveLike(boolean HaveLike) {
        this.HaveLike = HaveLike;
    }
    public boolean getHaveLike() {
        return HaveLike;
    }

}
