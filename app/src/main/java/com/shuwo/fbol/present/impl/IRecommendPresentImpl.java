package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.FootBallVideo;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.bean.LiveBroadCast;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IRecommendPresent;
import com.shuwo.fbol.view.IRecommendView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus01 on 2017/9/20.
 */

public class IRecommendPresentImpl implements IRecommendPresent {

    private Context context;
    private IRecommendView homePageView;

    public IRecommendPresentImpl(IRecommendView homePageView, Context context) {
        this.context = context;
        this.homePageView = homePageView;
    }

    @Override
    public void getLiveBroadInfo(int leagueId, int page, int pageSize) {
        String url = Contants.LIVE_BROADCAST_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("leagueId",leagueId);
        params.put("page",page);
        params.put("pageSize",pageSize);
        HttpClientUtil.getInstance(context).get(url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        Gson gson = new Gson();
                        try {
                            List<LiveBroadCast> beanList = (List<LiveBroadCast>) gson.fromJson(info.getString("data"),new TypeToken<List<LiveBroadCast>>(){}.getType());
                            homePageView.showLiveBroadInfo(beanList);

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

    }

    @Override
    public void getHighLightsInfo(int leagueId, int page, int pageSize) {
        String url = Contants.Highlights_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("leagueId",leagueId);
        params.put("page",page);
        params.put("pageSize",pageSize);
        HttpClientUtil.getInstance(context).get(url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        Gson gson = new Gson();
                        try {
                            List<HighLights> beanList = (List<HighLights>) gson.fromJson(info.getString("data"),new TypeToken<List<HighLights>>(){}.getType());
                            homePageView.showHighLightsInfo(beanList);

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

    }

    @Override
    public void getHeadLines(int infoType, int pageIndex, int pageSize) {
        String url = Contants.HEADLINE_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("page_size",pageSize);
        params.put("info_type",infoType);
        params.put("page_index",pageIndex);
        HttpClientUtil.getInstance(context).get(url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    JSONObject infos = new JSONObject(info.getString("qry_infos"));
                    if (infos != null) {
                        Gson gson = new Gson();
                        try {
                            List<HeadLines> beanList = (List<HeadLines>) gson.fromJson(infos.getString("data"),new TypeToken<List<HeadLines>>(){}.getType());
                                homePageView.showHeaderLines(beanList);

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

    }

    @Override
    public void getFootballVideo(String uuid, int start, int count) {
        String url = Contants.FOOTBALL_VIDEO_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("uuid",uuid);
        params.put("start",start);
        params.put("count",count);
        HttpClientUtil.getInstance(context).get(url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        Gson gson = new Gson();
                        try {
                            List<FootBallVideo> beanList = (List<FootBallVideo>) gson.fromJson(info.getString("datas"),new TypeToken<List<FootBallVideo>>(){}.getType());
                            homePageView.showFootBallVideo(beanList);

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

    }

}
