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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.activity.ArticleActivity;
import com.shuwo.fbol.activity.LoginActivity;
import com.shuwo.fbol.adapter.ForumAdapter;
import com.shuwo.fbol.bean.Forum;
import com.shuwo.fbol.present.IForumPresent;
import com.shuwo.fbol.present.impl.IForumPresentImpl;
import com.shuwo.fbol.view.IForumView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus01 on 2017/10/17.
 */

public class ForumFragment extends BaseFragment implements IForumView,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{

    private RecyclerView mRecyclerView;
    private ForumAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Forum> beanList = new ArrayList<>();
    private int delayMillis = 1000;

    private IForumPresent present;

    private int markId = 0;
    private int num = 10;
    private int tagId = 0;
    BuildBean buildBean;   //加载中的 dialog

    View fragmentView;

    TextView title;
    RelativeLayout loginRL;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_forum, null);

        showBuildBean();

        title = (TextView) fragmentView.findViewById(R.id.tv_title);
        title.setText("论坛");
        loginRL = (RelativeLayout) fragmentView.findViewById(R.id.rl_login);
        loginRL.setOnClickListener(this);

        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ForumAdapter(R.layout.forum_adapter, beanList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //添加headview
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),ArticleActivity.class);
                intent.putExtra("id",beanList.get(position).getId());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);

        //获取数据
        present = new IForumPresentImpl(ForumFragment.this,getContext());
        present.getInfoList();

        return fragmentView;
    }

    @Override
    public void onRefresh() {
        num = 10;
        present.getInfoList();
    }

    @Override
    public void showInfoList(List<Forum> infoList) {
        beanList = infoList;
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
        switch (v.getId()){
            case R.id.rl_login:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
