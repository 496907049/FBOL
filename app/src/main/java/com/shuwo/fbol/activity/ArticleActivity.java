package com.shuwo.fbol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.DateUtil;
import com.shuwo.fbol.bean.Article;
import com.shuwo.fbol.present.IArticlePresent;
import com.shuwo.fbol.present.impl.IArticlePresentImpl;
import com.shuwo.fbol.view.IArticleView;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by asus01 on 2017/10/13.
 */

public class ArticleActivity extends BaseActivity implements IArticleView,View.OnClickListener {

    private IArticlePresent articlePresent;
    private BuildBean buildBean;   //加载中的 dialog
    private int id;

    private TextView articleTitle;
    private TextView time;
    private ImageView dianZan;
    private WebView mWebView;

    RelativeLayout commentRL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_item);

        //从Intent当中根据key取得value
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id",0);
        }

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("详情");
        RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        rlBack.setOnClickListener(this);

        articleTitle = (TextView) findViewById(R.id.tv_article_title);
        time = (TextView) findViewById(R.id.tv_article_time);
        dianZan = (ImageView) findViewById(R.id.dian_zan);
        dianZan.setOnClickListener(this);

        commentRL = (RelativeLayout) findViewById(R.id.rl_comment);
        commentRL.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.wv_article);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new myWebViewClient());

        buildBean = DialogUIUtils.showLoading(this, "加载中...", false, true, false, true);
        buildBean.show();


        articlePresent = new IArticlePresentImpl(this,this);
        articlePresent.getInfoList(id);
    }

    @Override
    public void showInfoList(final Article article) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                articleTitle.setText(article.getTitle());
                time.setText("时间"+ DateUtil.timedate(String.valueOf(article.getCreation())));
                DialogUIUtils.dismiss(buildBean);
                mWebView.loadDataWithBaseURL("about:blank", article.getContent(), "text/html", "utf-8", null);
            }
        }, 1000);
    }

    @Override
    public void showNoLogin() {
        DialogUIUtils.dismiss(buildBean);
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText("请先登录")
                .setConfirmText("登陆")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(ArticleActivity.this,LoginActivity.class);
                             startActivity(intent);
                        sweetAlertDialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void isToBeFive() {
        DialogUIUtils.dismiss(buildBean);
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("提示")
                .setContentText("已经点过赞了")
                .show();
    }

    @Override
    public void success() {
        DialogUIUtils.dismiss(buildBean);
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("提示")
                .setContentText("点赞成功了！")
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dian_zan:
                buildBean.show();
                articlePresent.giveMeFive(id);
                break;
            case R.id.rl_comment:
                Intent intent = new Intent(ArticleActivity.this,CommentActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(mWebView!=null){
            mWebView.destroy();
        }
        super.onDestroy();
    }
    public class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

}
