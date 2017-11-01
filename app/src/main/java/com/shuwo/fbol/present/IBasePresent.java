package com.shuwo.fbol.present;

import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

/**
 * Created by asus01 on 2017/10/17.
 */

public interface IBasePresent {

    void getInfoList(int typeId, int page, int pageSize);           //page= 第几页      typeId  类别  page_size= 一页有几条数据

    void getLoadMoreInfoList(int typeId, int page, int pageSize);       //加载更多的时候用

}
