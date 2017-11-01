package com.shuwo.fbol.present.impl;

import android.content.Context;
import android.widget.Toast;

import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.http.AsyncHttpClient;
import com.shuwo.fbol.http.AsyncHttpResponseHandler;
import com.shuwo.fbol.http.RequestParams;
import com.shuwo.fbol.present.IRegisterPresent;
import com.shuwo.fbol.view.IRegisterView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by asus01 on 2017/10/11.
 */

public class IRegisterPresentImpl implements IRegisterPresent {

    private IRegisterView registerView;
    private Context context;

    public IRegisterPresentImpl(Context context, IRegisterView registerView) {
        this.registerView = registerView;
        this.context = context;
    }

    @Override
    public void toRegister(final String userName, final String password) {
        String url = Contants.BBS_HTTP_BASE + Contants.REGISTER_HTTP;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("password", password);
        client.post(context,url,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = new String(responseBody);
                try {
                    JSONObject info = new JSONObject(string);
                    if (info != null) {
                        String code = info.getString("code");
                        String message = info.getString("message");
                        if("0".equals(code)){
                            //注册成功
                            registerView.showRegisterInformation();
                        }else {
                            ToastUtils.show(context,"注册失败,用户名相同");
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
