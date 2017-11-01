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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.ScoreItemActivity;
import com.shuwo.fbol.adapter.OddsAdapter;
import com.shuwo.fbol.bean.Odds;
import com.shuwo.fbol.present.IOddsPresent;
import com.shuwo.fbol.present.impl.IOddsPresentImpl;
import com.shuwo.fbol.view.IOddsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/24.
 */

public class OddsFragment extends BaseFragment implements IOddsView,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{

    View fragmentView;   //fragment界面
    private IOddsPresent present;

    private BaseQuickAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Odds> beanList = new ArrayList<>();
    private int delayMillis = 1000;

    private int oddTypes= 0;

    TextView oddOne,oddTwo,oddThree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_odds, container, false);
        showBuildBean();   //显示加载中

        oddOne = (TextView) fragmentView.findViewById(R.id.odd_item_one);
        oddOne.setOnClickListener(this);
        oddTwo = (TextView) fragmentView.findViewById(R.id.odd_item_two);
        oddTwo.setOnClickListener(this);
        oddThree = (TextView) fragmentView.findViewById(R.id.odd_item_three);
        oddThree.setOnClickListener(this);

        present = new IOddsPresentImpl(getContext(),this);

        //创建布局管理
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //初始化Adapter
        initAdapter();
        present.getInfoList(ScoreItemActivity.matchId,oddTypes);
        return fragmentView;
    }

    //初始化  adapter
    private void initAdapter() {
        adapter = new OddsAdapter(R.layout.fragment_odd_adapter, beanList);
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
    }


    @Override
    public void onRefresh() {
        present.getInfoList(ScoreItemActivity.matchId,oddTypes);
    }


    @Override
    public void showInfoList(List<Odds> beanList1) {
        this.beanList = beanList1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(beanList);
                mSwipeRefreshLayout.setRefreshing(false);
                dismissBuildBean();
            }
        }, delayMillis);
    }

    @Override
    public void onClick(View v) {
        textBackGroundToMyGray();
        switch (v.getId()){
            case R.id.odd_item_one:
                oddOne.setBackgroundResource(R.color.white);
                oddTypes = 0;
                break;
            case R.id.odd_item_two:
                oddTwo.setBackgroundResource(R.color.white);
                oddTypes = 1;
                break;
            case R.id.odd_item_three:
                oddThree.setBackgroundResource(R.color.white);
                oddTypes = 2;
                break;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        present.getInfoList(ScoreItemActivity.matchId,oddTypes);
    }

    private void textBackGroundToMyGray(){
        oddOne.setBackgroundResource(R.color.my_gray);
        oddTwo.setBackgroundResource(R.color.my_gray);
        oddThree.setBackgroundResource(R.color.my_gray);
    }
}
