package com.mary.mpvdemo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mary.mpvdemo.R;
import com.mary.mpvdemo.bean.User;
import com.mary.mpvdemo.presenter.BasePresenter;
import com.mary.mpvdemo.presenter.MainPresenter;
import com.mary.mpvdemo.view.impl.IMainView;

/**
 * File Name:   MainActivity
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-12-23---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class MainActivity extends BaseActivity implements IMainView {

    private EditText mEtUsername, mEtPassword;
    private Button mBtnLogin, mBtnClear;
    private ProgressBar mPbLoading;

    private MainPresenter mMainPresenter = new MainPresenter();

    @Override
    public int getToolBarId() {
        return 0;
    }

    @Override
    public BasePresenter getPresenter() {
        return mMainPresenter;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);

        mBtnClear = (Button) findViewById(R.id.id_btn_clear);
        mBtnLogin = (Button) findViewById(R.id.id_btn_login);

        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.login();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.clear();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +"login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }
}
