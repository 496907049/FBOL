package com.shuwo.fbol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuwo.fbol.R;

/**
 * Created by asus01 on 2017/10/9.
 */

public class BaseBackAndTitleActivity extends AppCompatActivity implements View.OnClickListener{

    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();

        //从Intent当中根据key取得value
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
        }
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText(""+title);
        RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        rlBack.setOnClickListener(this);

        super.onCreate(savedInstanceState);
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
