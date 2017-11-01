package com.shuwo.fbol.present;

import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

/**
 * Created by asus01 on 2017/10/17.
 */

public interface IHighLightsPresent {

    void getHighLightsInfo(int leagueId, int page, int pageSize);      //获取集锦

    void getLoadMoreInfoList(int leagueId, int page, int pageSize);      //获取集锦

    void getRefreshHighLightsInfo(int leagueId, int page, int pageSize);      //获取集锦

    void getHotVideoList();      //热门更多

}
