package com.shuwo.fbol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.CommonTabUtil;
import com.shuwo.fbol.Util.ImageSliderUtil;
import com.shuwo.fbol.activity.HighlightsActivity;
import com.shuwo.fbol.activity.VideoViewActivity;
import com.shuwo.fbol.adapter.HighLightsAdapter;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.present.IHighLightsPresent;
import com.shuwo.fbol.present.impl.IHighLightsPresentImpl;
import com.shuwo.fbol.view.IHighLightsView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class HighLightsFragment extends BaseFragment implements IHighLightsView,SwipeRefreshLayout.OnRefreshListener{
    private View fragmentView;   //fragment界面
    private IHighLightsPresent highLightsPresent;

    private BaseQuickAdapter adapter;
    private RecyclerView  recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<HighLights> highLightsList = new ArrayList<>();
    private final int pageSize=10;    //一页显示几个
    private final int page=1;     //第几页
    public  static int currentLeagueIds = 7;
    private int delayMillis = 1000;
    private int[] leagueIds ={7,6,4,8};
    private String[] leagueName ={"法甲","西甲","英超","欧冠"};
    private int[] mIconUnselectIds = {
            R.mipmap.fajia, R.mipmap.xijia_default,
            R.mipmap.yinchao, R.mipmap.ouguang};
    private int[] mIconSelectIds = {
            R.mipmap.fajia_seleted, R.mipmap.xijai_seleted,
            R.mipmap.yinchao_seleted, R.mipmap.ouguang_seleted};


    //精彩视频clickmore
    private View moreView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_base_no_title, container, false);

        showBuildBean();   //显示加载中
        highLightsPresent = new IHighLightsPresentImpl(this,getActivity());

        //初始化头条更多的view
        moreView = inflater.inflate(R.layout.header_line_more,container,false);
        TextView moreTitle = (TextView) moreView.findViewById(R.id.tv_more_title);
        moreTitle.setText("精彩視頻");
        LinearLayout moreLL = (LinearLayout) moreView.findViewById(R.id.ll_header_line_more);
        moreLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HighlightsActivity.class);
                intent.putExtra("title",leagueName[CommonTabUtil.currentTab]+"视频");
                intent.putExtra("leagueId",leagueIds[CommonTabUtil.currentTab]);
                startActivity(intent);
            }
        });

       //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //初始化headerLineAdapter
        initAdapter();
        recyclerView.setAdapter(adapter);

        //获取集锦数据  默认是法甲
        highLightsPresent.getHighLightsInfo(currentLeagueIds,page,pageSize);
        highLightsPresent.getHotVideoList();
        return fragmentView;
    }


    //初始化 adapter
    private void initAdapter() {
        adapter = new HighLightsAdapter(R.layout.img_two_text_top_and_text_button, highLightsList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("url", highLightsList.get(position).getUrl());
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
    }


    //刷新
    @Override
    public void onRefresh() {
        highLightsPresent.getRefreshHighLightsInfo(currentLeagueIds,page,pageSize);
        adapter.removeAllHeaderView();
    }


    @Override
    public void showHighLightsInfo(List<HighLights> beanList) {
        highLightsList = beanList;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.setNewData(highLightsList);
                dismissBuildBean();
            }
        }, delayMillis);
    }

    @Override
    public void showLoadMoreInfoList(List<HighLights> beanList) {

    }

    @Override
    public void showRefreshHighLightsInfo(List<HighLights> beanList) {
        highLightsList = beanList;
        highLightsPresent.getHotVideoList();
    }

    @Override
    public void showHotVideoList(final List<HighLights> beanList) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //首页轮播图
                adapter.addHeaderView(ImageSliderUtil.initImageSlider2(getActivity(),getContext(),beanList));    //添加轮播图
                adapter.addHeaderView(CommonTabUtil.getTab(getActivity(),getContext(),leagueName,mIconUnselectIds,mIconSelectIds,leagueIds,highLightsPresent,mSwipeRefreshLayout));    //添加轮播图
                adapter.addHeaderView(moreView);    //更多
                if(highLightsList!=null || highLightsList.size()>0){
                    adapter.setNewData(highLightsList);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                dismissBuildBean();
            }
        }, delayMillis);
    }

}