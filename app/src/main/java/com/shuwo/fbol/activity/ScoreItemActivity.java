package com.shuwo.fbol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ViewFindUtils;
import com.shuwo.fbol.fragment.AnalysisFragment;
import com.shuwo.fbol.fragment.OddsFragment;

import java.util.ArrayList;

/**
 * Created by asus01 on 2017/10/23.
 */

public class ScoreItemActivity extends AppCompatActivity {

    private View mDecorView;
    private String[] mTitles = {"分析", "赔率"};
    private SegmentTabLayout tabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static String matchId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_item);
        getSupportActionBar().hide();

        RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView titleTV = (TextView) findViewById(R.id.tv_title);
        TextView timeTV = (TextView) findViewById(R.id.tv_time);
        TextView homeTeamName = (TextView) findViewById(R.id.home_team_name);
        TextView guestTeamName = (TextView) findViewById(R.id.guest_team_name);
        TextView homeTeamScore = (TextView) findViewById(R.id.home_team_score);
        TextView guestTeamScore = (TextView) findViewById(R.id.guest_team_score);


        Intent intent = getIntent();
        if (intent != null) {
            matchId = intent.getStringExtra("matchId");
            titleTV.setText(intent.getStringExtra("title"));
            timeTV.setText(intent.getStringExtra("time"));
            homeTeamName.setText(intent.getStringExtra("homeTeamName"));
            guestTeamName.setText(intent.getStringExtra("guestTeamName"));
            homeTeamScore.setText(intent.getStringExtra("homeTeamScore"));
            guestTeamScore.setText(intent.getStringExtra("guestTeamScore"));
        }

        mFragments.add(new AnalysisFragment());
        mFragments.add(new OddsFragment());

        mDecorView = getWindow().getDecorView();
        tabLayout = ViewFindUtils.find(mDecorView, R.id.tl_1);
        final ViewPager vp_3 = ViewFindUtils.find(mDecorView, R.id.vp_2);
        vp_3.setAdapter(new MyPagerAdapter(this.getSupportFragmentManager()));

        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_3.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp_3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_3.setCurrentItem(1);
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
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
