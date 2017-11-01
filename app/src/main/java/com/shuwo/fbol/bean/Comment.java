package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/13.
 */

public class Comment {

    private int Id;
    private int Uid;
    private int Tid;
    private String UserName;
    private String TopicTitle;
    private String Content;
    private int Creation;
    private boolean Alive;
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }

    public void setUid(int Uid) {
        this.Uid = Uid;
    }
    public int getUid() {
        return Uid;
    }

    public void setTid(int Tid) {
        this.Tid = Tid;
    }
    public int getTid() {
        return Tid;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserName() {
        return UserName;
    }

    public void setTopicTitle(String TopicTitle) {
        this.TopicTitle = TopicTitle;
    }
    public String getTopicTitle() {
        return TopicTitle;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    public String getContent() {
        return Content;
    }

    public void setCreation(int Creation) {
        this.Creation = Creation;
    }
    public int getCreation() {
        return Creation;
    }

    public void setAlive(boolean Alive) {
        this.Alive = Alive;
    }
    public boolean getAlive() {
        return Alive;
    }

}
