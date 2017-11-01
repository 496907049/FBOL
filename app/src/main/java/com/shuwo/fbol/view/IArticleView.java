package com.shuwo.fbol.view;

import com.shuwo.fbol.bean.Article;

/**
 * Created by asus01 on 2017/10/13.
 */

public interface IArticleView {

    void showInfoList(Article article);

    void showNoLogin();

    void isToBeFive();

    void success();
}
