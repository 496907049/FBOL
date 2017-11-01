package com.shuwo.fbol.present.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.bean.Forum;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.present.IForumPresent;
import com.shuwo.fbol.view.IForumView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by asus01 on 2017/10/11.
 */

public class IForumPresentImpl implements IForumPresent {

    private Context context;
    private IForumView forumView;

    public IForumPresentImpl(IForumView forumView, Context context) {
        this.context = context;
        this.forumView = forumView;
    }

    @Override
    public void getInfoList() {
        String url = Contants.BBS_HTTP_BASE+Contants.TOPIC_HTTP;
        HttpClientUtil.getInstance(context).get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject Info = new JSONObject(string);
                    if (Info != null) {
                        Gson gson = new Gson();
                        try {
                            forumView.showInfoList((List<Forum>) gson.fromJson(Info.getString("message"),new TypeToken<List<Forum>>(){}.getType()));
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

            }
        });

    }

}
