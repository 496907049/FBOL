package com.shuwo.fbol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ImageSliderUtil;
import com.shuwo.fbol.activity.WebViewActivity1;
import com.shuwo.fbol.adapter.NBAAdapter;
import com.shuwo.fbol.bean.NBA;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.INBAPresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class NBAFragment extends BaseFragment implements IBaseView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    View fragmentView;   //fragment界面
    private IBasePresent present;

    private SliderLayout sliderLayout;     //轮播图
    private PagerIndicator indicator;     ////轮播图

    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<NBA> beanList = new ArrayList<>();
    private int pageSize=10;    //一页显示几个
    private int pageIndex=1;     //第几页
    private int typeId =17;
    private int delayMillis = 1000;
    View imageSliderView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_base_no_title, container, false);
        showBuildBean();   //显示加载中

        present = new INBAPresentImpl(getContext(),this);

        //准备好要显示的数据
        List<Integer> imageUrls = new ArrayList<>();
        final List<String> descriptions = new ArrayList<>();
        imageUrls.add(R.mipmap.tupian1);
        descriptions.add(" ");
        //首页轮播图
        imageSliderView = ImageSliderUtil.initImageSlider(getContext(),imageUrls,descriptions);

        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //初始化Adapter
        initAdapter();
        present.getInfoList(typeId,pageIndex,pageSize);

        return fragmentView;
    }


    //初始化  头条的adapter
    private void initAdapter() {
        adapter = new NBAAdapter(R.layout.three_text_left_one_img_right, beanList);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity1.class);
                intent.putExtra("url", beanList.get(position).getShare_url());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
        //添加头部
        adapter.addHeaderView(imageSliderView);    //添加轮播图
    }


    @Override
    public void onRefresh() {
        pageIndex = 1;
        present.getInfoList(typeId,pageIndex,pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex += 10;
        present.getLoadMoreInfoList(typeId,pageIndex,pageSize);
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