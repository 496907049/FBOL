package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.FirstInterface;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.present.ILogoPresent;
import com.shuwo.fbol.view.ILogoView;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by asus01 on 2017/9/20.
 */

public class ILogoPresentImpl implements ILogoPresent {

    private Context context;
    private ILogoView logoView;

    public ILogoPresentImpl(ILogoView logoView, Context context) {
        this.context = context;
        this.logoView = logoView;
    }

    @Override
    public void getFirstInterface() {
        String url = Contants.JUMP_HTTP;
        HttpClientUtil.getInstance(context).get(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    Gson gson = new Gson();
                    JSONObject Info = new JSONObject(string);
                    JSONObject root = new JSONObject(Info.getString("msg"));
                    String bb = root.toString();
                    FirstInterface firstInterface  = gson.fromJson(bb,FirstInterface.class);
                    logoView.showFirstInterface(firstInterface);
                } catch (Exception e) {
                    e.printStackTrace();
                    logoView.showFailure();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "请求失败,正在重新请求", Toast.LENGTH_LONG);
                logoView.showFailure();
            }
        });

    }
}
