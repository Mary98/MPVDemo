package com.mary.mpvdemo.presenter;

import com.mary.mpvdemo.view.impl.IBaseView;

/**
 * File Name:   BasePresenter
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description: 基础 Presenter
 * Change log:
 * 16/3/12-17-13---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public abstract class BasePresenter<T extends IBaseView> {

    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

}
