package com.shuwo.fbol.present.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.HttpClientUtil;
import com.shuwo.fbol.Util.SharePreferenceUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.bean.User;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.ILoginPresent;
import com.shuwo.fbol.view.ILoginView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by asus01 on 2017/10/11.
 */

public class ILoginPresentImpl implements ILoginPresent {

    private ILoginView loginView;
    private Context context;

    public ILoginPresentImpl(Context context, ILoginView loginView) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void getUserInformation(String userName, final String password) {
        String url = Contants.BBS_HTTP_BASE + Contants.LOGIN_HTTP;
        RequestParams params = new RequestParams();
        params.put("username",userName);
        params.put("password",password);
        HttpClientUtil.getInstance(context).post(context,url,params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String string = new String(responseBody);
                        try {
                            JSONObject info = new JSONObject(string);
                            if (info != null) {
                                String code = info.getString("code");
                                String message = info.getString("message");
                                if ("1".equals(code)) {
                                    //账号密码错误
                                    ToastUtils.show(context, "账号密码错误");
                                } else {
                                    Gson gson = new Gson();
                                    User bean = gson.fromJson(message, User.class);
                                    loginView.showLoginInformation();
                                    SharePreferenceUtil.put(context, "userName", bean.getUserName());
                                    SharePreferenceUtil.put(context, "password", password);
                                    SharePreferenceUtil.put(context, "userId", bean.getId());
                                    SharePreferenceUtil.put(context, "isLogin", true);
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
