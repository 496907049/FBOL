package com.shuwo.fbol.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilinlin on 16/7/20 11:20.
 * email 973635949@qq.com
 *
 * @desc Fragment 基类  使用方法 直接在getLayoutId中传入布局
 */
public abstract class CommonLazyLoadFragment extends Fragment {

    BuildBean buildBean;   //加载中的 dialog

    /**
     * 标记已加载完成，只能加载一次
     */
    private boolean hasLoaded = false;
    /**
     * 标记是否已经onCreate
     */
    private boolean isCreated = false;
    /**
     * 界面对于用户是否可见
     */
    private boolean isVisibleToUser = false;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        init(view, savedInstanceState);
        return view;
    }

    /**
     * 返回当前fragment需要引用的布局Id
     */
    public abstract int getLayoutId();

    public void init(View view, Bundle savedInstanceState) {
        isCreated = true;
        this.view = view;
        lazyLoad(this.view, savedInstanceState);
    }

    /**
     * 监听界面是否展示给用户，实现懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad(view, null);
    }

    /**
     * 懒加载方法，获取数据什么的放到这边来使用，在切换到这个界面时才进行网络请求
     */
    private void lazyLoad(View view, Bundle savedInstanceState) {
        //如果该界面不对用户显示、已经加载、fragment还没有创建，
        //三种情况任意一种，不获取数据
        if (!isVisibleToUser || hasLoaded || !isCreated) {
            return;
        }
        lazyInit(view, savedInstanceState);
        hasLoaded = true;
    }

    /**
     * 懒加载的初始化方法
     */
    public abstract void lazyInit(View view, Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isCreated = false;
        hasLoaded = false;
    }

    protected void showBuildBean(){
        buildBean = DialogUIUtils.showLoading(getActivity(), "加载中...", false, true, false, true);
        buildBean.show();
    }

    protected void dismissBuildBean(){
        DialogUIUtils.dismiss(buildBean);
    }
}
