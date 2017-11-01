package com.shuwo.fbol.activity;

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
import com.shuwo.fbol.adapter.HighLightsAdapter;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.IHighLightsPresent;
import com.shuwo.fbol.present.impl.IHeadLinePresentImpl;
import com.shuwo.fbol.present.impl.IHighLightsPresentImpl;
import com.shuwo.fbol.view.IBaseView;
import com.shuwo.fbol.view.IHighLightsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/21.
 */

public class HighlightsActivity extends BaseActivity implements IHighLightsView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private int leagueId = 0;
    private int page = 1;
    private int pageSize = 10;

    BaseQuickAdapter adapter;
    private List<HighLights> beanList = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private IHighLightsPresent present;

    private int delayMillis = 1000;

    BuildBean buildBean;   //加载中的 dialog

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showBuildBean();

        //从Intent当中根据key取得value
        Intent intent = getIntent();
        if (intent != null) {
            leagueId = intent.getIntExtra("leagueId",1);
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

        present = new IHighLightsPresentImpl(this, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new HighLightsAdapter(R.layout.img_two_text_top_and_text_button, beanList);
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HighlightsActivity.this, VideoViewActivity.class);
                intent.putExtra("url", beanList.get(position).getUrl());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        present.getHighLightsInfo(leagueId, page, pageSize);

    }

    @Override
    public void onRefresh() {
        page = 1;
        present.getHighLightsInfo(leagueId, page, pageSize);

    }

    @Override
    public void onLoadMoreRequested() {
        page += 1;
        present.getLoadMoreInfoList(leagueId, page, pageSize);
    }

    @Override
    public void showHighLightsInfo(List<HighLights> beanList1) {
        beanList = beanList1;
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(beanList);
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
                dismissBuildBean();
            }
        }, delayMillis);
    }

    @Override
    public void showLoadMoreInfoList(List<HighLights> beanList1) {
        mSwipeRefreshLayout.setEnabled(false);
        if (beanList1.size() <= 0) {
            adapter.loadMoreEnd(false);//true is gone,false is visible    //没有更多数据
        } else {
            adapter.addData(beanList1);   //添加数据
            adapter.loadMoreComplete();
        }
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void showRefreshHighLightsInfo(List<HighLights> beanList) {

    }

    @Override
    public void showHotVideoList(List<HighLights> beanList) {

    }
}
