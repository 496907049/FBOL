package com.shuwo.fbol.view;

import com.shuwo.fbol.bean.Comment;

import java.util.List;

/**
 * Created by asus01 on 2017/10/13.
 */

public interface ICommentView {

    void showInfoList(List<Comment> infoList);

    void showNoComment();

    void showNoLogin();

    void success();
}
