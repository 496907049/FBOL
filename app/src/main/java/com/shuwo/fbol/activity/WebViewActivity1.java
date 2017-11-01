package com.shuwo.fbol.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.shuwo.fbol.R;
import com.shuwo.fbol.widget.DownPicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/9/25.
 */

public class WebViewActivity1 extends BaseActivity {
    String url;
    String title = "详情";
    private WebView mWebView;
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从Intent当中根据key取得value
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }
        setContentView(R.layout.activity_web_view);
        showBuildBean();

        TextView textView = (TextView) findViewById(R.id.tv_title);
        textView.setText("" + title);
        RelativeLayout backRL = (RelativeLayout) findViewById(R.id.rl_back);
        backRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mWebView = (WebView) findViewById(R.id.wv_act);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new myWebViewClient());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadUrl(url);
    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

    public class myWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return false;
//        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl(videoHide()); //加载js方法代码
            view.loadUrl(changeImg()); //加载js方法代码
            view.loadUrl(deleteHeadAndFood()); //加载js方法代码
            view.loadUrl(hideSource()); //加载js方法代码
            dismissBuildBean();
            mWebView.setVisibility(View.VISIBLE);

//            view.loadUrl("javascript:function hiddenAd(){document.querySelector('.shareheader').style.display=\"none\";document.querySelector('.footer').style.display=\"none\";}hiddenAd();");
        }

        /**
         * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
         **/
        private String changeImg() {
            String js =
                    "javascript:(function() {"
//                    +"setTimeout(function(){$('img').css('width','100%');},1000);"
                    +"setTimeout(function(){$('div.head_inner').hide();)},1000);"
                    + "})()";
//                        "$(function($) {"
//                            +"setTimeout(function(){$('img').css('width','100%');},1000);"
//                            +"});";
            return js;
        }

        /**
         * 隐藏视频上下
         **/
        private String videoHide() {
            String js =
                    "javascript:(function() {"
                            +"setTimeout(function(){($('#myFlash').length > 0 ? $('#myFlash').parent() : $('.site_player')).siblings().hide();},100);"
                            + "})()";
            return js;
        }
    }

    public String deleteHeadAndFood() {
        String js = "javascript:(function() {"
                + "document.getElementsByClassName('shareheader')[0].style.display=\"none\";"
                + "document.getElementsByClassName('footer')[0].style.display=\"none\""
                + "})()";
//        js +="document.querySelector('.shareheader').style.display=\"none\";";
//        js +="document.querySelector('.footer').style.display=\"none\"";
//        js += "}";
        return js;
    }

    public String hideSource() {
        String js = "javascript:(function(){"
                + "document.getElementsByClassName('text-header')[0].getElementsByTagName('div')[0].getElementsByTagName('span')[0].style.display=\"none\""
                + "})()";
        return js;
    }


//    public String changeImgWidth() {
//        String js = "javascript:(function(){"
////                    +"for(var i=0;i<$('img').length;i++){"
////                    + "if($('img')[i].className==''){$('img')[i].style.display=\"none\"}"
////                    +"}"
//                +"document.getElementsByTagName('img')[2].style.display=\"none\""
//                + "})()";
//        return js;
//    }


    //$("img")   获取到了html文件里面所有的img控件
//    var img=[]; for(var i=0;i<$("img").length;i++){
//        //获取所有符合放大要求的图片，将图片路径(src)获取
//        img[i] = $("img")[i].className;
//    }


}
