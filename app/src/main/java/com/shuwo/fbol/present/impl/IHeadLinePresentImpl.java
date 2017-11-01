package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.view.IBaseView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus01 on 2017/10/19.
 */

public class IHeadLinePresentImpl implements IBasePresent {

    private Context context;
    private IBaseView baseView;

    public IHeadLinePresentImpl(IBaseView baseView, Context context) {
        this.context = context;
        this.baseView = baseView;
    }

    @Override
    public void getInfoList(int typeId, int page, int pageSize) {
        String url = Contants.HEADLINE_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("page_size",pageSize);
        params.put("info_type",typeId);
        params.put("page_index",page);
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
                            baseView.showInfoList(beanList);

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
    public void getLoadMoreInfoList(int typeId, int page, int pageSize) {
        String url = Contants.HEADLINE_HTTP_BASE;
        RequestParams params = new RequestParams();
        params.put("page_size",pageSize);
        params.put("info_type",typeId);
        params.put("page_index",page);
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
                            baseView.showLoadMoreInfoList(beanList);

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
