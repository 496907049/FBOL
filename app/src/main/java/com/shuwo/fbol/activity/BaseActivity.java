package com.shuwo.fbol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;

/**
 * Created by asus01 on 2017/10/9.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    BuildBean buildBean;   //加载中的 dialog
    String title;
    TextView titleTV;
    RelativeLayout backRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        backRl = (RelativeLayout) findViewById(R.id.rl_back);
        backRl.setOnClickListener(this);

        //获取上个界面传递过来的值
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
        }
        titleTV = (TextView) findViewById(R.id.tv_title);
        titleTV.setText(""+title);

    }

    protected void showBuildBean(){
        buildBean = DialogUIUtils.showLoading(this, "加载中...", false, true, false, true);
        buildBean.show();
    }

    protected void dismissBuildBean(){
        DialogUIUtils.dismiss(buildBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
