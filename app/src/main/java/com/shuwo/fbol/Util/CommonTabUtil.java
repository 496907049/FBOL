package com.shuwo.fbol.Util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shuwo.fbol.R;
import com.shuwo.fbol.bean.TabEntity;
import com.shuwo.fbol.fragment.HighLightsFragment;
import com.shuwo.fbol.present.IHighLightsPresent;

import java.util.ArrayList;

/**
 * Created by asus01 on 2017/10/21.
 */

public class CommonTabUtil {

    private static ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    public static int currentTab = 0;

    public static View getTab(final Activity activity, final Context context, final String[] mTitles, int[] mIconUnselectIds, int[] mIconSelectIds, final int[] leagueIds, final IHighLightsPresent highLightsPresent,final SwipeRefreshLayout mSwipeRefreshLayout){
        mTabEntities.clear();
        View   tabView = View.inflate(context, R.layout.activity_common_tab, null);
        CommonTabLayout  mTabLayout_2 = ViewFindUtils.find(tabView, R.id.tl_2);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setCurrentTab(currentTab);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currentTab = position;
                mSwipeRefreshLayout.setRefreshing(true);
                HighLightsFragment.currentLeagueIds = leagueIds[position];
                highLightsPresent.getHighLightsInfo(HighLightsFragment.currentLeagueIds,1,10);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        return tabView;

    }

}
