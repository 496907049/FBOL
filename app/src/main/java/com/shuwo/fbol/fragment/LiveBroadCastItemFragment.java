package com.shuwo.fbol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ImageSliderUtil;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.activity.WebViewActivity1;
import com.shuwo.fbol.adapter.HeadLinesAdapter;
import com.shuwo.fbol.adapter.LiveBroadCastAdapter;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.LiveBroadCast;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.ILiveBroadCastPresentImpl;
import com.shuwo.fbol.present.impl.INBAPresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/24.
 */
@SuppressLint("ValidFragment")
public class LiveBroadCastItemFragment extends CommonLazyLoadFragment implements IBaseView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    private IBasePresent present;
    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<LiveBroadCast> beanList = new ArrayList<>();
    private int pageSize = 10;    //一页显示几个
    private int page = 0;     //第几页
    private int typeId = 1;
    private int delayMillis = 1000;

    public LiveBroadCastItemFragment(){

    }

    public LiveBroadCastItemFragment(int leagueId){
        this.typeId = leagueId;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_no_title;
    }

    @Override
    public void init(View view, Bundle savedInstanceState) {
        super.init(view, savedInstanceState);
        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //初始化Adapter
        initAdapter();
    }

    @Override
    public void lazyInit(View view, Bundle savedInstanceState) {
        showBuildBean();   //显示加载中
        present = new ILiveBroadCastPresentImpl(getContext(), this);
        present.getInfoList(typeId,page,pageSize);
    }


    //初始化  头条的adapter
    private void initAdapter() {
        adapter = new LiveBroadCastAdapter(R.layout.live_broadcast, beanList);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(beanList.get(position).getSinaMatchDataUrl()!=null &&beanList.get(position).getSinaMatchDataUrl()!="" &&beanList.get(position).getSinaMatchDataUrl().length()>1){
                    Intent intent = new Intent(getActivity(), WebViewActivity1.class);
                    intent.putExtra("url", beanList.get(position).getSinaMatchDataUrl());
                    intent.putExtra("title", "整容列表");
                    startActivity(intent);
                }else {
                    ToastUtils.show(getContext(),"没有阵容列表！");
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
        page = 1;
        present.getInfoList(typeId, page, pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        page += 1;
        present.getLoadMoreInfoList(typeId, page, pageSize);
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
    public void showLoadMoreInfoList(List infoList) {
        mSwipeRefreshLayout.setEnabled(false);
        if (infoList.size() <= 0) {
            adapter.loadMoreEnd(false);//true is gone,false is visible    //没有更多数据
        } else {
            adapter.addData(infoList);   //添加数据
            adapter.loadMoreComplete();
        }
        mSwipeRefreshLayout.setEnabled(true);
    }

}