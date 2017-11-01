package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.Analysis;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.Odds;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.present.IOddsPresent;
import com.shuwo.fbol.view.IOddsView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus01 on 2017/10/24.
 */

public class IOddsPresentImpl implements IOddsPresent {

    private Context context;
    private IOddsView oddsView;

    public IOddsPresentImpl(Context context, IOddsView oddsView) {
        this.context = context;
        this.oddsView = oddsView;
    }

    @Override
    public void getInfoList(String matchId, int oddsType) {
        String url = Contants.ODDS_HTTP + "match_id=" + matchId + "&odds_type=" + oddsType;
        HttpClientUtil.getInstance(context).get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        JSONObject qry = new JSONObject(info.getString("qry_odds"));
                        Gson gson = new Gson();
                        List<Odds> beanList = (List<Odds>) gson.fromJson(qry.getString("data"), new TypeToken<List<Odds>>() {}.getType());
                        oddsView.showInfoList(beanList);
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
