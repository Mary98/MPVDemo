package com.mary.mpvdemo.model;

import com.mary.mpvdemo.bean.User;

/**
 * File Name:   OnLoginListener
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-22-32---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
