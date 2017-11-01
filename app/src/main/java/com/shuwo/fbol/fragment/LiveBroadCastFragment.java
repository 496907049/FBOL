package com.shuwo.fbol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ViewFindUtils;

import java.util.ArrayList;

/**
 * Created by asus01 on 2017/9/19.
 */

public class LiveBroadCastFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "NBA", "CBA", "德甲", "英超", "意甲", "西甲"
    };
    private final int[] leagueId ={
            1,2,3,4,5,6
    };
    private MyPagerAdapter mAdapter;

    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_live_broadcast,container,false);

        fragmentManager = getChildFragmentManager();
        for (int i :leagueId){
            mFragments.add(new LiveBroadCastItemFragment(i));
        }

        ViewPager vp = (ViewPager) fragmentView.findViewById(R.id.vp);
        vp.setOffscreenPageLimit(mFragments.size()-1);
        mAdapter = new MyPagerAdapter(fragmentManager);
        vp.setAdapter(mAdapter);

        /**自定义部分属性*/
        SlidingTabLayout tabLayout_2 = ViewFindUtils.find(fragmentView, R.id.tl_2);
        tabLayout_2.setViewPager(vp);
        return fragmentView;
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment)super.instantiateItem(container,position);
            fragmentManager.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            Fragment fragment = mFragments.get(position);
            fragmentManager.beginTransaction().hide(fragment).commit();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            fragment = mFragments.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id",""+position);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

}

