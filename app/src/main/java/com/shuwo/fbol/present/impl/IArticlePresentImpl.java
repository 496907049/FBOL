package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.Article;
import com.shuwo.fbol.bean.BeanTypeOne;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IArticlePresent;
import com.shuwo.fbol.view.IArticleView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/13.
 */

public class IArticlePresentImpl implements IArticlePresent {

    private Context context;
    private IArticleView articleView;

    public IArticlePresentImpl(IArticleView articleView, Context context) {
        this.context = context;
        this.articleView = articleView;
    }


    @Override
    public void getInfoList(int id) {
        String url = Contants.BBS_HTTP_BASE+Contants.POST_DETAILS_HTTP+String.valueOf(id);
        RequestParams params = new RequestParams();
        HttpClientUtil.getInstance(context).post(context,url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                List<BeanTypeOne> crudeOilList= new ArrayList<>();
                String string = new String(responseBody);
                try {
                    JSONObject Info = new JSONObject(string);
                    if (Info != null) {
                        Gson gson = new Gson();
                        Article bean  = gson.fromJson(Info.getString("message"),Article.class);
                        articleView.showInfoList(bean);
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

    @Override
    public void giveMeFive(int id) {
        String url = Contants.BBS_HTTP_BASE + Contants.GIVE_FIVE_HTTP;

        RequestParams params = new RequestParams();
        params.put("tid",id);

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
                            //点赞失败
                            String message = info.getString("message");
                            if("unrepeatable like".equals(message)){
                                //点过攒了
                                articleView.isToBeFive();
                            }else{
                                //user not login   //没有登陆
                                articleView.showNoLogin();
                            }
                        }else {
                            articleView.success();
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
//        client.post(context,url,params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String string = new String(responseBody);
//                try {
//                    JSONObject info = new JSONObject(string);
//                    if (info != null) {
//                        String code = info.getString("code");
//                        if("1".equals(code)){
//                            //点赞失败
//                            String message = info.getString("message");
//                            if("unrepeatable like".equals(message)){
//                                //点过攒了
//                                articleView.isToBeFive();
//                            }else{
//                                //user not login   //没有登陆
//                                articleView.showNoLogin();
//                            }
//                        }else {
//
//                        }
//
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


}
