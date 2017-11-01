package com.shuwo.fbol.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;

/**
 * Created by asus01 on 2017/10/9.
 */

public class BaseFragment extends Fragment {

    BuildBean buildBean;   //加载中的 dialog
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showBuildBean(){
        buildBean = DialogUIUtils.showLoading(getActivity(), "加载中...", false, true, false, true);
        buildBean.show();
    }

    protected void dismissBuildBean(){
        DialogUIUtils.dismiss(buildBean);
    }


}
