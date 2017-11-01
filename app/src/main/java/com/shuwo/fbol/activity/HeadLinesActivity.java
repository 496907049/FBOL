package com.shuwo.fbol.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.adapter.HeadLinesAdapter;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.IHeadLinePresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/19.
 */

public class HeadLinesActivity extends BaseActivity implements IBaseView,BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    private int typeId=0;
    private int page=1;
    private int pageSize=10;

    BaseQuickAdapter headLinesAdapter;
    private List<HeadLines> beanList = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private IBasePresent basePresent;

    private int delayMillis = 1000;

    BuildBean buildBean;   //加载中的 dialog


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBuildBean();
        basePresent = new IHeadLinePresentImpl(this, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));    //  2条数据
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));    //一条数据
        headLinesAdapter = new HeadLinesAdapter(R.layout.three_text_left_one_img_right, beanList);
        headLinesAdapter.setOnLoadMoreListener(this, mRecyclerView);
        headLinesAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        headLinesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mRecyclerView.setAdapter(headLinesAdapter);
        headLinesAdapter.setEnableLoadMore(true);
        basePresent.getInfoList(typeId, page, pageSize);

    }

    @Override
    public void onRefresh() {
        page = 1;
        basePresent.getInfoList(typeId, page, pageSize);

    }

    @Override
    public void onLoadMoreRequested() {
        page += 10;
        basePresent.getLoadMoreInfoList(typeId, page, pageSize);
    }

    @Override
    public void showInfoBean(Object bean) {

    }

    @Override
    public void showLoadMoreBean(Object bean) {

    }

    @Override
    public void showInfoList(List infoList) {
        beanList = infoList;
        headLinesAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headLinesAdapter.setNewData(beanList);
                mSwipeRefreshLayout.setRefreshing(false);
                headLinesAdapter.setEnableLoadMore(true);
               dismissBuildBean();
            }
        }, delayMillis);
    }

    @Override
    public void showLoadMoreInfoList(List infoList) {
        List<HeadLines> addList = infoList;
        mSwipeRefreshLayout.setEnabled(false);
        if (infoList.size() <= 0) {
            headLinesAdapter.loadMoreEnd(false);//true is gone,false is visible    //没有更多数据
        } else {
            headLinesAdapter.addData(addList);   //添加数据
            headLinesAdapter.loadMoreComplete();
        }
        mSwipeRefreshLayout.setEnabled(true);
    }
}
