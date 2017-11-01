package com.shuwo.fbol.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.ScoreItemActivity;
import com.shuwo.fbol.adapter.HeadLinesAdapter;
import com.shuwo.fbol.adapter.ScoreItemAdapter;
import com.shuwo.fbol.bean.HeadLines;
import com.shuwo.fbol.bean.ScoreItem;
import com.shuwo.fbol.present.IBasePresent;
import com.shuwo.fbol.present.impl.INBAPresentImpl;
import com.shuwo.fbol.present.impl.IScoreItemOnePresentImpl;
import com.shuwo.fbol.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ScoreItemOneFragment extends BaseFragment implements IBaseView, SwipeRefreshLayout.OnRefreshListener {

    View fragmentView;   //fragment界面
    private IBasePresent present;

    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ScoreItem> beanList = new ArrayList<>();
    private int status = 1;
    private int matchDiffDay = 0;
    private int delayMillis = 1000;

//    public ScoreItemOneFragment(int status, int matchDiffDay){
//        this.status = status;
//        this.matchDiffDay = matchDiffDay;
//    }

    public static ScoreItemOneFragment getInstance(int status1, int matchDiffDay) {
        ScoreItemOneFragment fragment = new ScoreItemOneFragment();
        fragment.status = status1;
        fragment.matchDiffDay = matchDiffDay;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_base_no_title, container, false);
        showBuildBean();   //显示加载中
        present = new IScoreItemOnePresentImpl(getContext(), this);
        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //初始化Adapter
        initAdapter();
        present.getInfoList(0, status, matchDiffDay);
        return fragmentView;
    }

    //初始化  adapter
    private void initAdapter() {
        adapter = new ScoreItemAdapter(R.layout.fragment_score_item_adapter, beanList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ScoreItemActivity.class);
                intent.putExtra("matchId", beanList.get(position).getMatch_id());
                intent.putExtra("title", beanList.get(position).getHome_team_name());
                intent.putExtra("time", beanList.get(position).getGroup_date());
                intent.putExtra("homeTeamName", beanList.get(position).getHome_team_name());
                intent.putExtra("guestTeamName", beanList.get(position).getGuest_team_name());
                intent.putExtra("homeTeamScore", beanList.get(position).getHome_team_score());
                intent.putExtra("guestTeamScore", beanList.get(position).getGuest_team_score());
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


    @Override
    public void onRefresh() {
        present.getInfoList(0, status, matchDiffDay);
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