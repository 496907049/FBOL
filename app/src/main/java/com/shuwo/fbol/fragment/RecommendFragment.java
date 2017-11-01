package com.shuwo.fbol.fragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.ImageSliderUtil;
import com.shuwo.fbol.activity.HeadLinesActivity;
import com.shuwo.fbol.activity.VideoViewActivity;
import com.shuwo.fbol.activity.WebViewActivity1;
import com.shuwo.fbol.adapter.FootBallVideoAdapter;
import com.shuwo.fbol.adapter.HeadLinesAdapter;
import com.shuwo.fbol.bean.FootBallVideo;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.HighLights;
import com.shuwo.fbol.bean.LiveBroadCast;
import com.shuwo.fbol.present.IRecommendPresent;
import com.shuwo.fbol.present.impl.IRecommendPresentImpl;
import com.shuwo.fbol.view.IRecommendView;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends BaseFragment implements IRecommendView,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    View fragmentView;   //fragment界面
    private IRecommendPresent homePagePresent;

    private SliderLayout sliderLayout;     //轮播图
    private PagerIndicator indicator;     ////轮播图

    private HeadLinesAdapter headLinesAdapter;
    private RecyclerView headerLineRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<HeadLines> headLinesList = new ArrayList<>();
    private int headLinesPageSize=10;    //一页显示几个
    private int headLinesIndex=1;     //第几页
    private int delayMillis = 1000;

    //横向RecyclerView控件
    private View horizontalView;                       //横向的iew
    private RecyclerView horizontalRecyclerView;    //横向的RecyclerView
    private FootBallVideoAdapter horizontalAdapter;      //横向的RecyclerView的adapter
    private List<FootBallVideo> horizontalList = new ArrayList<>();
    //直播信息控件
//    private View liveBroadView;
//    private TextView homeTeamNameTV;   //主场队伍名字
//    private TextView awayTeamNameTV;   //客场队伍名字
//    private TextView leagueNameTV;      //联盟名字
//    private TextView timeStringTV;      //时间
//    private ImageView homeTeamLogoIV;   //主场队伍logo
//    private ImageView awayTeamLogoIV;   //客场队伍logo
//    private List<LiveBroadCast> liveBroadCastList;

    //头条clickmore
    private View headLineClickMoreView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home_page_item_one, container, false);
        showBuildBean();   //显示加载中

        homePagePresent = new IRecommendPresentImpl(this,getActivity());

        //准备好要显示的数据
        List<Integer> imageUrls = new ArrayList<>();
        final List<String> descriptions = new ArrayList<>();
        imageUrls.add(R.mipmap.lunbo1);
        imageUrls.add(R.mipmap.lunbo2);
        imageUrls.add(R.mipmap.lunbo3);
        descriptions.add(" ");
        descriptions.add(" ");
        descriptions.add(" ");
        //首页轮播图
        View imageSliderView = ImageSliderUtil.initImageSlider(getContext(),imageUrls,descriptions);

        //初始化《直播界面》接口的控件
//        liveBroadView = inflater.inflate(R.layout.live_broadcast,container,false);
//        liveBroadView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WebViewActivity1.class);
//                intent.putExtra("url",liveBroadCastList.get(0).getSinaMatchDataUrl());
//                startActivity(intent);
//            }
//        });
//        homeTeamNameTV = (TextView) liveBroadView.findViewById(R.id.tv_home_team_name);
//        awayTeamNameTV = (TextView) liveBroadView.findViewById(R.id.tv_away_team_name);
//        leagueNameTV = (TextView) liveBroadView.findViewById(R.id.tv_league_name);
//        timeStringTV = (TextView) liveBroadView.findViewById(R.id.tv_time_string);
//        homeTeamLogoIV = (ImageView) liveBroadView.findViewById(R.id.iv_home_team_logo);
//        awayTeamLogoIV = (ImageView) liveBroadView.findViewById(R.id.iv_away_team_logo);

        //初始化横向控件
        horizontalView = inflater.inflate(R.layout.fragment_home_page_item_one_a, container, false);
        horizontalRecyclerView = (RecyclerView) horizontalView.findViewById(R.id.horizontalLineRecyclerView);
        //设置布局管理器  可以横向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        //初始化横向控件的Adapter
        initHorizontalAdapter();
        horizontalRecyclerView.setAdapter(horizontalAdapter);

        //初始化头条更多的view
        headLineClickMoreView = inflater.inflate(R.layout.header_line_more,container,false);
        LinearLayout moreLL = (LinearLayout) headLineClickMoreView.findViewById(R.id.ll_header_line_more);
        moreLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HeadLinesActivity.class);
                intent.putExtra("title","头条");
                startActivity(intent);
            }
        });

        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        headerLineRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.headerLineRecyclerView);
        headerLineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //初始化headerLineAdapter
        initHeaderLineAdapter();
        headerLineRecyclerView.setAdapter(headLinesAdapter);
        //添加头部
        headLinesAdapter.addHeaderView(imageSliderView);    //添加轮播图
//        headLinesAdapter.addHeaderView(liveBroadView);    //添加直播信息
        headLinesAdapter.addHeaderView(horizontalView);    //添加横向RecyclerView
        headLinesAdapter.addHeaderView(headLineClickMoreView);    //添加头条跟多的view

        //获取集锦数据  默认是英超
//        homePagePresent.getHighLightsInfo(4,0,10);
        //获取《直播界面》数据
//        homePagePresent.getLiveBroadInfo(8,0,1);
        //获取《足球视频》数据
        homePagePresent.getFootballVideo(Contants.FOOTBALL_VIDEO_UUID,0,10);
        //获取《头条》的数据
        homePagePresent.getHeadLines(0,headLinesIndex,headLinesPageSize);

        return fragmentView;
    }


    //初始化  头条的adapter
    private void initHeaderLineAdapter() {
        headLinesAdapter = new HeadLinesAdapter(R.layout.three_text_left_one_img_right, headLinesList);
        headLinesAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        headLinesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity1.class);
                intent.putExtra("url", headLinesList.get(position).getInfo_url());
                startActivity(intent);
            }
        });
        headerLineRecyclerView.setAdapter(headLinesAdapter);
        headerLineRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    //初始化  横向reclerview的adapter
    private void initHorizontalAdapter() {
        horizontalAdapter = new FootBallVideoAdapter(R.layout.horizontal_adapter, horizontalList);
        horizontalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("url", horizontalList.get(position).getVideoUrl());
                startActivity(intent);
            }
        });
        horizontalRecyclerView.setAdapter(headLinesAdapter);
        horizontalRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    //刷新
    @Override
    public void onRefresh() {
        //获取《直播界面》数据
//        homePagePresent.getLiveBroadInfo(8,0,1);
        //获取<足球视频>数据
        homePagePresent.getFootballVideo(Contants.FOOTBALL_VIDEO_UUID,0,10);
        //获取头条的数据
        homePagePresent.getHeadLines(0,headLinesIndex,headLinesPageSize);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void showLiveBroadInfo(final List<LiveBroadCast> beanList) {
//        liveBroadCastList = beanList;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (LiveBroadCast bean : beanList){
//                    homeTeamNameTV.setText(bean.getHomeTeamName());
//                    awayTeamNameTV.setText(bean.getAwayTeamName());
//                    leagueNameTV.setText(bean.getLeagueName());
//                    timeStringTV.setText(bean.getTimeString());
//                    // 加载网络图片
//                    Glide.with(getContext()).load(bean.getHomeTeamLogo()).placeholder(R.mipmap.ic_launcher).into(homeTeamLogoIV);
//                    Glide.with(getContext()).load(bean.getHomeTeamLogo()).placeholder(R.mipmap.ic_launcher).into(awayTeamLogoIV);
//                }
//            }
//        }, delayMillis);
    }

    @Override
    public void showHighLightsInfo(List<HighLights> beanList) {
//        horizontalList = beanList;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                horizontalAdapter.setNewData(horizontalList);
//            }
//        }, delayMillis);
    }

    @Override
    public void showHeaderLines(List<HeadLines> beanList) {
        headLinesList = beanList;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headLinesAdapter.setNewData(headLinesList);
                mSwipeRefreshLayout.setRefreshing(false);
                dismissBuildBean();
            }
        }, delayMillis);
    }

    @Override
    public void showFootBallVideo(List<FootBallVideo> beanList) {
        horizontalList = beanList;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalAdapter.setNewData(horizontalList);
            }
        }, delayMillis);
    }
}
