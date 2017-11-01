package com.shuwo.fbol.widget;

import android.app.Application;

import com.dou361.dialogui.DialogUIUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onCreate() {    	     
    	 Logger.d(TAG, "[ExampleApplication] onCreate");
         super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
         DialogUIUtils.init(getApplicationContext());
    }
}
