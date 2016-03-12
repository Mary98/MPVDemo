package com.mary.mpvdemo.model.impl;

import com.mary.mpvdemo.model.OnLoginListener;

/**
 * File Name:   IMainModel
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-22-31---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public interface IMainModel extends IBaseModel {
    public void login(String username, String password, OnLoginListener loginListener);
}
