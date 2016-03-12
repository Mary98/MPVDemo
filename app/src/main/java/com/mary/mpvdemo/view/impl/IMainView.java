package com.mary.mpvdemo.view.impl;

import com.mary.mpvdemo.bean.User;

/**
 * File Name:   IMainView
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-22-38---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public interface IMainView extends IBaseView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
