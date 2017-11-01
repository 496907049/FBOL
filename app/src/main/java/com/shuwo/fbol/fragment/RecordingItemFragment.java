package com.shuwo.fbol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.OddVideoActivity;
import com.shuwo.fbol.activity.VideoViewActivity;
import com.shuwo.fbol.activity.VideoWebViewActivity;
import com.shuwo.fbol.adapter.LiveBroadCastAdapter;
import com.shuwo.fbol.bean.BackLooks;
import com.shuwo.fbol.bean.LiveBroadCast;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.ILiveBroadCastPresentImpl;
import com.shuwo.fbol.present.impl.IReCordingPresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/24.
 */
@SuppressLint("ValidFragment")
public class RecordingItemFragment extends CommonLazyLoadFragment implements IBaseView,SwipeRefreshLayout.OnRefreshListener {

    private IBasePresent present;

    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<LiveBroadCast> beanList = new ArrayList<>();
    private int pageSize = 10;    //一页显示几个
    private int page = 0;     //第几页
    private int typeId = 1;
    private int delayMillis = 1000;

    public RecordingItemFragment(){

    }

    public RecordingItemFragment(int leagueId){
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
        //初始化Adapter
        initAdapter();

    }

    @Override
    public void lazyInit(View view, Bundle savedInstanceState) {
        showBuildBean();   //显示加载中
        present = new IReCordingPresentImpl(getContext(), this);
        present.getInfoList(typeId,page,pageSize);
    }


    //初始化  adapter
    private void initAdapter() {
        adapter = new LiveBroadCastAdapter(R.layout.live_broadcast, beanList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), OddVideoActivity.class);
                intent.putParcelableArrayListExtra("backLooks", (ArrayList<? extends Parcelable>) beanList.get(position).getBackLooks());
                intent.putExtra("title", "视频列表");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        //添加头部
    }


    @Override
    public void onRefresh() {
        page = 1;
        present.getInfoList(typeId, page, pageSize);
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

    }

}