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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ImageSliderUtil;
import com.shuwo.fbol.activity.WebViewActivity1;
import com.shuwo.fbol.adapter.HeadLinesAdapter;
import com.shuwo.fbol.adapter.IntelligenceAdapter;
import com.shuwo.fbol.adapter.NBAAdapter;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.NBA;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.IHeadLinePresentImpl;
import com.shuwo.fbol.present.impl.INBAPresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class IntelligenceItemFragment extends BaseFragment implements IBaseView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    View fragmentView;   //fragment界面
    private int typeId=0;
    private int page=1;
    private int pageSize=10;
    BaseQuickAdapter headLinesAdapter;
    private List<HeadLines> beanList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private IBasePresent basePresent;
    private int delayMillis = 1000;

    String oneStyleOrTwoStyle;

    public IntelligenceItemFragment(String oneStyleOrTwoStyle,int typeId){
            this.oneStyleOrTwoStyle = oneStyleOrTwoStyle;
            this.typeId = typeId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_base_no_title, container, false);
        showBuildBean();   //显示加载中
        basePresent = new IHeadLinePresentImpl(this, getContext());
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

        if(oneStyleOrTwoStyle.equals("one")){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));    //一条数据
            headLinesAdapter = new HeadLinesAdapter(R.layout.three_text_left_one_img_right, beanList);
        }else{
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));    //  2条数据
            headLinesAdapter = new IntelligenceAdapter(R.layout.img_two_text_top_and_text_button, beanList);
        }

        headLinesAdapter.setOnLoadMoreListener(this, mRecyclerView);
        headLinesAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        headLinesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity1.class);
                intent.putExtra("url", beanList.get(position).getInfo_url());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(headLinesAdapter);
        headLinesAdapter.setEnableLoadMore(true);
        basePresent.getInfoList(typeId, page, pageSize);

        return fragmentView;
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