package com.shuwo.fbol.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.adapter.CommentAdapter;
import com.shuwo.fbol.bean.Comment;
import com.shuwo.fbol.present.ICommentPresent;
import com.shuwo.fbol.present.impl.ICommentPresentImpl;
import com.shuwo.fbol.view.ICommentView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by asus01 on 2017/10/13.
 */

public class CommentActivity extends BaseActivity implements ICommentView,SwipeRefreshLayout.OnRefreshListener,View.OnClickListener{

    private RecyclerView mRecyclerView;
    private CommentAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Comment> beanList = new ArrayList<>();
    private int delayMillis = 1000;

    private ICommentPresent present;

    BuildBean buildBean;   //加载中的 dialog

    private int id;
    private Button sendCommentBtn;
    private EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //从Intent当中根据key取得value
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id",0);
        }

        buildBean = DialogUIUtils.showLoading(this, "加载中...", false, true, false, true);
        buildBean.show();

        sendCommentBtn = (Button) findViewById(R.id.bt_send_comment);
        sendCommentBtn.setOnClickListener(this);

        editTextContent = (EditText) findViewById(R.id.et_comment);

        TextView tv = (TextView) findViewById(R.id.tv_title);
        tv.setText("评论");
        RelativeLayout backRL = (RelativeLayout) findViewById(R.id.rl_back);
        backRL.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentAdapter(R.layout.activity_comment_adapter, beanList);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(adapter);

        //获取数据
        present = new ICommentPresentImpl(this,this);
        present.getInfoList(id);
    }

    @Override
    public void onRefresh() {
        present.getInfoList(id);
    }

    @Override
    public void showInfoList(List<Comment> infoList) {
        beanList = infoList;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(beanList);
                mSwipeRefreshLayout.setRefreshing(false);
                DialogUIUtils.dismiss(buildBean);
            }
        }, delayMillis);
    }

    @Override
    public void showNoComment() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(CommentActivity.this,"没有信息！");
                DialogUIUtils.dismiss(buildBean);
            }
        }, delayMillis);
    }

    @Override
    public void showNoLogin() {
        DialogUIUtils.dismiss(buildBean);

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText("请先登录")
                .setConfirmText("登陆")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(CommentActivity.this,LoginActivity.class);
                        startActivity(intent);
                        sweetAlertDialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void success() {
        DialogUIUtils.dismiss(buildBean);
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("评论成功")
                .setContentText("等待管理员确认")
                .show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_send_comment:
                if (editTextContent.getText().toString().length()<5){
                    ToastUtils.show(this,"评论最少5个字！！！");
                    return;
                }
                present.sendComment(id,editTextContent.getText().toString());
                break;
        }
    }
}
