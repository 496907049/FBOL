package com.shuwo.fbol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.present.ILoginPresent;
import com.shuwo.fbol.present.impl.ILoginPresentImpl;
import com.shuwo.fbol.view.ILoginView;

/**
 * Created by asus01 on 2017/10/11.
 */

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener{

    EditText etUserName;
    EditText etPassWord;
    Button btnLogin;
    Button btnRegister;
    RelativeLayout rlForgetPW;
//    RelativeLayout rlRegister;

    ILoginPresent loginPresent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("登陆");
        RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        rlBack.setOnClickListener(this);

        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassWord = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        rlForgetPW = (RelativeLayout) findViewById(R.id.rl_forget_pw);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        rlForgetPW.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        loginPresent = new ILoginPresentImpl(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                loginPresent.getUserInformation(etUserName.getText().toString(),etPassWord.getText().toString());
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void showLoginInformation() {
        ToastUtils.show(this,"登陆成功");
        finish();
    }
}
