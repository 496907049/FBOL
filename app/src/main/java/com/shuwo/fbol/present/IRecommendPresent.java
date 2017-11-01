package com.shuwo.fbol.present;

import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

/**
 * Created by asus01 on 2017/10/17.
 */

public interface IRecommendPresent {

    void getLiveBroadInfo(int leagueId,int page,int pageSize);     //直播界面全部：0，NBA：1，英超：4，CBA：2，西甲：6，中超：9，德甲：3，意甲：5， 欧冠：8，法甲：7

    void getHighLightsInfo(int leagueId,int page,int pageSize);      //获取集锦

    void getHeadLines(int infoType,int pageIndex,int pageSize);           //page_index = page       info_type=0  类别  page_size= pageSize

    void getFootballVideo(String uuid,int start,int count);

}
