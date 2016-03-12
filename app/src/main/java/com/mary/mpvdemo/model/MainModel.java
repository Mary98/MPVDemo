package com.mary.mpvdemo.model;

import com.mary.mpvdemo.bean.User;
import com.mary.mpvdemo.model.impl.IMainModel;

/**
 * File Name:   MainModel
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-22-34---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class MainModel implements IMainModel {

    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("zhy".equals(username) && "123".equals(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
