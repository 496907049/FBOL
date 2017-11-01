package com.shuwo.fbol.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.Contants;
import com.shuwo.fbol.Util.NetWorkUtils;
import com.shuwo.fbol.Util.PreferencesUtils;
import com.shuwo.fbol.bean.FirstInterface;
import com.shuwo.fbol.bean.SafeSwitch;
import com.shuwo.fbol.present.ILogoPresent;
import com.shuwo.fbol.present.impl.ILogoPresentImpl;
import com.shuwo.fbol.view.ILogoView;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by asus01 on 2017/10/23.
 */

public class LogoActivity extends AppCompatActivity implements ILogoView{

    ILogoPresent logoPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_logo);

        logoPresent = new ILogoPresentImpl(this,this);

        if(NetWorkUtils.isNetworkConnected(this)!=true){
            Intent intent = new Intent(LogoActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            logoPresent.getFirstInterface();
        }
        //第一：默认初始化
        Bmob.initialize(this, Contants.BMOB_KEY);

    }

    @Override
    public void showFirstInterface(FirstInterface bean) {
        if("0".equals(bean.getOpen())){
//            第一：默认初始化
            checkBmob();
        }else{
            Intent intent = new Intent(LogoActivity.this,WebViewActivity2.class);
            intent.putExtra("url",bean.getLinks());
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showFailure() {
        checkBmob();
    }

    private void checkBmob(){
        BmobQuery<SafeSwitch> query = new BmobQuery<SafeSwitch>();
        Contants.ApplicationID = getPackageName();
        query.addWhereEqualTo("BundleID",Contants.ApplicationID);     //添加字段  不是获取值
        query.setLimit(1);
        try {
            query.findObjects(new FindListener<SafeSwitch>() {
                @Override
                public void done(List<SafeSwitch> list, BmobException e) {
                    if(e == null){
                        //bmob查询成功
                        Intent intent = new Intent();
                        SafeSwitch ss = list.get(0);
                        if ("true".equals(ss.getIsOpen())) {
                            PreferencesUtils.putBoolean(LogoActivity.this, Contants.IS_OPEN, true);
                            intent.setClass(LogoActivity.this, WebViewActivity2.class);
                            intent.putExtra("url", ss.getLink());
                            intent.putExtra("title", LogoActivity.this.getResources().getString(R.string.app_name));
                        } else {
                            intent.setClass(LogoActivity.this, MainActivity.class);
                        }
                        LogoActivity.this.startActivity(intent);
                        LogoActivity.this.finish();
                    }else {
                        //Bmob查询失败
                        reConnection();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reConnection(){
        logoPresent.getFirstInterface();
    }
}
