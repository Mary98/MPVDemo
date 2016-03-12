package com.mary.mpvdemo.presenter;

import android.os.Handler;

import com.mary.mpvdemo.model.MainModel;
import com.mary.mpvdemo.model.OnLoginListener;
import com.mary.mpvdemo.bean.User;
import com.mary.mpvdemo.view.impl.IMainView;

/**
 * File Name:   MainPresenter
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-22-43---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class MainPresenter extends BasePresenter<IMainView> {
    private MainModel mainModel;
    private Handler mHandler = new Handler();

    public MainPresenter() {
        this.mainModel = new MainModel();
    }

    public void login() {
        this.mainModel.login(mView.getUserName(), mView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.toMainActivity(user);
                        mView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showFailedError();
                        mView.hideLoading();
                    }
                });
            }
        });
    }

    /**
     * 清空
     */
    public void clear() {
        mView.clearUserName();
        mView.clearPassword();
    }

}
