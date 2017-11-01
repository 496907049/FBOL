package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.Analysis;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IAnalysisPresent;
import com.shuwo.fbol.view.IAnalysisView;
import com.shuwo.fbol.view.IBaseView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus01 on 2017/10/24.
 */

public class IAnalysisPresentImpl implements IAnalysisPresent{
    private Context context;
    private IAnalysisView analysisView;

    public IAnalysisPresentImpl(Context context, IAnalysisView analysisView){
        this.context = context;
        this.analysisView = analysisView;
    }

    @Override
    public void getInfoList(String matchId) {
        String url = Contants.ANALYSIS_HTTP+matchId;
        HttpClientUtil.getInstance(context).get(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        Gson gson = new Gson();
                        String a = info.getString("get_team_matchs");
                        Analysis analysis = gson.fromJson(a, Analysis.class);
                        analysisView.showInfoList(analysis.getHome_matchs());
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
