package com.shuwo.fbol.bean;

/**
 * Created by asus01 on 2017/10/24.
 */

public class Odds {
    private String company_id;
    private String company_name;
    private String even_odds;
    private int even_odds_chg;
    private String first_even_odds;
    private String first_lost_odds;
    private String first_win_odds;
    private int is_live;
    private String lost_odds;
    private int lost_odds_chg;
    private String win_odds;
    private int win_odds_chg;
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public String getCompany_name() {
        return company_name;
    }

    public void setEven_odds(String even_odds) {
        this.even_odds = even_odds;
    }
    public String getEven_odds() {
        return even_odds;
    }

    public void setEven_odds_chg(int even_odds_chg) {
        this.even_odds_chg = even_odds_chg;
    }
    public int getEven_odds_chg() {
        return even_odds_chg;
    }

    public void setFirst_even_odds(String first_even_odds) {
        this.first_even_odds = first_even_odds;
    }
    public String getFirst_even_odds() {
        return first_even_odds;
    }

    public void setFirst_lost_odds(String first_lost_odds) {
        this.first_lost_odds = first_lost_odds;
    }
    public String getFirst_lost_odds() {
        return first_lost_odds;
    }

    public void setFirst_win_odds(String first_win_odds) {
        this.first_win_odds = first_win_odds;
    }
    public String getFirst_win_odds() {
        return first_win_odds;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }
    public int getIs_live() {
        return is_live;
    }

    public void setLost_odds(String lost_odds) {
        this.lost_odds = lost_odds;
    }
    public String getLost_odds() {
        return lost_odds;
    }

    public void setLost_odds_chg(int lost_odds_chg) {
        this.lost_odds_chg = lost_odds_chg;
    }
    public int getLost_odds_chg() {
        return lost_odds_chg;
    }

    public void setWin_odds(String win_odds) {
        this.win_odds = win_odds;
    }
    public String getWin_odds() {
        return win_odds;
    }

    public void setWin_odds_chg(int win_odds_chg) {
        this.win_odds_chg = win_odds_chg;
    }
    public int getWin_odds_chg() {
        return win_odds_chg;
    }
}
