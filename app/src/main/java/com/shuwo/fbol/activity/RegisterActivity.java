package com.shuwo.fbol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuwo.fbol.R;
import com.shuwo.fbol.Util.ToastUtils;
import com.shuwo.fbol.present.IRegisterPresent;
import com.shuwo.fbol.present.impl.IRegisterPresentImpl;
import com.shuwo.fbol.view.IRegisterView;

/**
 * Created by asus01 on 2017/10/11.
 */

public class RegisterActivity extends BaseActivity implements IRegisterView,View.OnClickListener{

    EditText etUserName;
    EditText etPassWord;
    EditText etPassWord2;
    Button btnRegister;

    IRegisterPresent present;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("注册");
        RelativeLayout rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        rlBack.setOnClickListener(this);

        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassWord = (EditText) findViewById(R.id.et_password);
        etPassWord2 = (EditText) findViewById(R.id.et_password2);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        present = new IRegisterPresentImpl(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                if (etUserName.getText().toString().length()<1 || etPassWord.getText().toString().length()<1 || etPassWord2.getText().toString().length()<1){
                    ToastUtils.show(this,"用户名-密码不能为空");
                    return;
                }
                if (!etPassWord.getText().toString().equals(etPassWord2.getText().toString())){
                    ToastUtils.show(this,"2次密码不一致");
                  return;
                }
                present.toRegister(etUserName.getText().toString(),etPassWord.getText().toString());
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void showRegisterInformation() {
        ToastUtils.show(this,"注册成功");
        finish();
    }
}
