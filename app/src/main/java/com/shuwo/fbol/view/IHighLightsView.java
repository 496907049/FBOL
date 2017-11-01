package com.shuwo.fbol.view;

import com.shuwo.fbol.bean.FootBallVideo;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.bean.LiveBroadCast;

import java.util.List;


/**
 * Created by asus01 on 2017/10/17.
 */

public interface IHighLightsView {

    void showHighLightsInfo(List<HighLights> beanList);

    void showLoadMoreInfoList(List<HighLights> beanList);

    void showRefreshHighLightsInfo(List<HighLights> beanList);

    void showHotVideoList(List<HighLights> beanList);    //意甲
}
