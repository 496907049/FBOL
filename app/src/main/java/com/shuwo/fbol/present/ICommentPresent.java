package com.shuwo.fbol.present;

/**
 * Created by asus01 on 2017/10/11.
 */

public interface ICommentPresent {
    //帖子ID
    void getInfoList(int id);

    //帖子ID   回复的内容
    void sendComment(int id, String content);

}
