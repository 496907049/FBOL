package com.shuwo.fbol.view;

import com.shuwo.fbol.bean.FootBallVideo;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.bean.LiveBroadCast;

import java.util.List;


/**
 * Created by asus01 on 2017/10/17.
 */

public interface IRecommendView {

    void showLiveBroadInfo(List<LiveBroadCast> beanList);

    void showHighLightsInfo(List<HighLights> beanList);

    void showHeaderLines(List<HeadLines> beanList);           //page_index = page       info_type=0  类别  page_size= pageSize

    void showFootBallVideo(List<FootBallVideo> beanList);
}
