package com.shuwo.fbol.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.shuwo.fbol.R;
import com.shuwo.fbol.fragment.ForumFragment;
import com.shuwo.fbol.fragment.HomePageFragment;
import com.shuwo.fbol.fragment.intelligenceFragment;
import com.shuwo.fbol.fragment.ScoreFragment;
import com.shuwo.fbol.fragment.SpectatorsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mRadioGroup;
    private FrameLayout mLayout;

    private FragmentManager fragmentManager;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup= (RadioGroup) findViewById(R.id.rg_radio_navigation);
        mRadioGroup.setOnCheckedChangeListener(this);
//        mLayout= (FrameLayout) findViewById(R.id.fl_radio_show);
        fragmentManager =getSupportFragmentManager();

        if (savedInstanceState != null) { // “内存重启”时调用
            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0+""));
            fragments.add(fragmentManager.findFragmentByTag(1+""));
            fragments.add(fragmentManager.findFragmentByTag(2+""));
            fragments.add(fragmentManager.findFragmentByTag(3+""));
            fragments.add(fragmentManager.findFragmentByTag(4+""));
            //恢复fragment页面
            restoreFragment();

        }else{      //正常启动时调用
            fragments.add(new HomePageFragment());
            fragments.add(new ScoreFragment());
            fragments.add(new SpectatorsFragment());
            fragments.add(new intelligenceFragment());
            fragments.add(new ForumFragment());
            showFragment();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(CURRENT_FRAGMENT,currentIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_home_page:
                currentIndex = 0;
                break;
            case R.id.rb_radio_quotation:
                currentIndex = 1;
                break;
            case R.id.rb_radio_spectators:
                currentIndex = 2;
                break;
            case R.id.rb_radio_intelligence:
                currentIndex = 3;
                break;
            case R.id.rb_radio_forum:
                currentIndex = 4;
                break;
        }
        showFragment();
    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果之前没有添加过
        if(!fragments.get(currentIndex).isAdded()){
            int a = currentIndex;
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_radio_show,fragments.get(currentIndex),""+currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag
        }else{
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(currentIndex));
        }
        currentFragment = fragments.get(currentIndex);
        transaction.commit();
    }

    /**
     * 恢复fragment
     */
    private void restoreFragment(){
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if(i == currentIndex){
                mBeginTreansaction.show(fragments.get(i));
            }else{
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);
    }
}
