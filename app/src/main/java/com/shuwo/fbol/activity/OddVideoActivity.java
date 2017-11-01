package com.shuwo.fbol.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.adapter.OddsVideoAdapter;
import com.shuwo.fbol.bean.BackLooks;
import com.shuwo.fbol.bean.LiveBroadCast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/9/23.
 */

public class OddVideoActivity extends BaseActivity  {

    private int markId=0;
    private int num=10;
    private int tagId=5;

    BaseQuickAdapter adapter;
    private List<BackLooks> beanList = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private int delayMillis = 1000;

    BuildBean buildBean;   //加载中的 dialog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            beanList = intent.getParcelableArrayListExtra("backLooks");
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new OddsVideoAdapter(R.layout.img_two_text_top_and_text_button, beanList);
            adapter.openLoadAnimation();
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(OddVideoActivity.this,WebViewActivity1.class);
                    intent.putExtra("url",beanList.get(position).getWebUrl());
                    intent.putExtra("title","视频");
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(adapter);
            adapter.setEnableLoadMore(true);


    }



}
