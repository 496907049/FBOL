package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/11.
 */

public class User {

    private int Id;
    private String UserName;
    private int Creation;
    private boolean IsAdmin;
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserName() {
        return UserName;
    }

    public void setCreation(int Creation) {
        this.Creation = Creation;
    }
    public int getCreation() {
        return Creation;
    }

    public void setIsAdmin(boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }
    public boolean getIsAdmin() {
        return IsAdmin;
    }

}
