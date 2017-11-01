package com.shuwo.fbol.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flyco.tablayout.SlidingTabLayout;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ViewFindUtils;
import com.shuwo.fbol.adapter.MyPagerAdapter;

import java.util.ArrayList;

/**
 * Created by asus01 on 2017/10/17.
 */

public class HomePageFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "推荐", "集锦", "NBA", "资讯"
    };
    private MyPagerAdapter mAdapter;

    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View fragmentView = inflater.inflate(R.layout.fragment_home_page,container,false);
        fragmentManager = getChildFragmentManager();
        mFragments.add(new RecommendFragment());
        mFragments.add(new HighLightsFragment());
        mFragments.add(new NBAFragment());
        mFragments.add(new InformationFragment2());

        ViewPager vp = (ViewPager) fragmentView.findViewById(R.id.vp);
        vp.setOffscreenPageLimit(mFragments.size()-1);
        mAdapter = new MyPagerAdapter(fragmentManager,mFragments,mTitles);
        vp.setAdapter(mAdapter);

        /** indicator矩形圆角 */
        final SlidingTabLayout tabLayout_7 = ViewFindUtils.find(fragmentView, R.id.tl_7);
        tabLayout_7.setViewPager(vp);

        return fragmentView;
    }



}


