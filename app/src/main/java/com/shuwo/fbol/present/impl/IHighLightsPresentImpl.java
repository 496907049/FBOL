package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IHighLightsPresent;
import com.shuwo.fbol.view.IHighLightsView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/19.
 */

public class IHighLightsPresentImpl implements IHighLightsPresent {

    private Context context;
    private IHighLightsView highLightsView;

    public IHighLightsPresentImpl(IHighLightsView highLightsView, Context context) {
        this.context = context;
        this.highLightsView = highLightsView;
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
                            highLightsView.showHighLightsInfo(beanList);

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
    public void getLoadMoreInfoList(int leagueId, int page, int pageSize) {
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
                            highLightsView.showLoadMoreInfoList(beanList);

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
    public void getRefreshHighLightsInfo(int leagueId, int page, int pageSize) {
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
                            highLightsView.showRefreshHighLightsInfo(beanList);

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
    public void getHotVideoList() {
        String url = Contants.HOT_MORE_HTTP;
        HttpClientUtil.getInstance(context).get(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        Gson gson = new Gson();
                            List<HighLights> beanList = (List<HighLights>) gson.fromJson(info.getString("data"),new TypeToken<List<HighLights>>(){}.getType());
                            highLightsView.showHotVideoList(beanList);
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
