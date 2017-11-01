package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.BeanTypeOne;
import com.shuwo.fbol.bean.Comment;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.ICommentPresent;
import com.shuwo.fbol.view.ICommentView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/13.
 */

public class ICommentPresentImpl implements ICommentPresent {

    private Context context;
    private ICommentView commentView;

    public ICommentPresentImpl(ICommentView commentView, Context context) {
        this.context = context;
        this.commentView = commentView;
    }


    @Override
    public void getInfoList(int id) {
        String url = Contants.BBS_HTTP_BASE + Contants.COMMENT_HTTP;

        RequestParams params = new RequestParams();
        params.put("tid",id);
        params.put("alive","off");

        HttpClientUtil.getInstance(context).post(context,url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                       String message= info.getString("message");
                        if("null".equals(message)){
                            commentView.showNoComment();
                            return;
                        }
                        Gson gson = new Gson();
                        try {
                            List<Comment> beanList = (List<Comment>) gson.fromJson(info.getString("message"),new TypeToken<List<Comment>>(){}.getType());
                                commentView.showInfoList(beanList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "操作失败1", Toast.LENGTH_LONG);
            }
        });


//        AsyncHttpClient client = new AsyncHttpClient();
//        client.addHeader("Content-Type","application/x-www-form-urlencoded");
//        RequestParams params = new RequestParams();
//        params.put("tid",id);
//        params.put("alive","off");
//        client.post(context,url,params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String string = new String(responseBody);
//                try {
//                    JSONObject info = new JSONObject(string);
//                    if (info != null) {
//                       String message= info.getString("message");
//                        if("null".equals(message)){
//                            commentView.showNoComment();
//                            return;
//                        }
//                        Gson gson = new Gson();
//                        try {
//                            List<Comment> beanList = (List<Comment>) gson.fromJson(info.getString("message"),new TypeToken<List<Comment>>(){}.getType());
//                                commentView.showInfoList(beanList);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(context, "操作失败1", Toast.LENGTH_LONG);
//            }
//        });
    }

    @Override
    public void sendComment(int id, String content) {
        String url = Contants.BBS_HTTP_BASE + Contants.ADD_HTTP;
        RequestParams params = new RequestParams();
        params.put("tid",id);
        params.put("content",content);

        HttpClientUtil.getInstance(context).post(context,url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                List<BeanTypeOne> beanList= new ArrayList<>();
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        String code = info.getString("code");
                        if("1".equals(code)){
                            //发表评论失败
                            String message = info.getString("message");
                            if("user not login".equals(message)){
                                commentView.showNoLogin();
                            }
                        }else {
                            //发表评论成功
                            commentView.success();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "操作失败1", Toast.LENGTH_LONG);
            }
        });
    }


}
