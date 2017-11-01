package com.shuwo.fbol.fragment;

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
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.ScoreItemActivity;
import com.shuwo.fbol.adapter.AnalysisAdapter;
import com.shuwo.fbol.bean.Analysis;
import com.shuwo.fbol.bean.HomeMatchs;
import com.shuwo.fbol.present.IAnalysisPresent;
import com.shuwo.fbol.present.impl.IAnalysisPresentImpl;
import com.shuwo.fbol.view.IAnalysisView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/23.
 */

public class AnalysisFragment extends BaseFragment implements IAnalysisView,SwipeRefreshLayout.OnRefreshListener{

    View fragmentView;   //fragment界面
    private IAnalysisPresent present;

    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<HomeMatchs> beanList = new ArrayList<>();
    private int delayMillis = 1000;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_analysis, container, false);
        showBuildBean();   //显示加载中

        present = new IAnalysisPresentImpl(getContext(),this);

        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //初始化Adapter
        initAdapter();
        present.getInfoList(ScoreItemActivity.matchId);
        return fragmentView;
    }

    //初始化  adapter
    private void initAdapter() {
        adapter = new AnalysisAdapter(R.layout.fragment_analysis_adapter, beanList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(), WebViewActivity1.class);
//                intent.putExtra("url", beanList.get(position).getInfo_url());
//                startActivity(intent);
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
        present.getInfoList(ScoreItemActivity.matchId);
    }


    @Override
    public void showInfoList(List<HomeMatchs> beanList1) {
        this.beanList = beanList1;
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
}
