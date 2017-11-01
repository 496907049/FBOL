package com.shuwo.fbol.view;

import java.util.List;



/**
 * Created by asus01 on 2017/10/17.
 */

public interface IBaseView<T>  {

    void showInfoBean(T bean);

    void showLoadMoreBean(T bean);

    void showInfoList(List<T> infoList);

    void showLoadMoreInfoList(List<T> infoList);

}
