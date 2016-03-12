package com.mary.mpvdemo.view.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mary.mpvdemo.presenter.BasePresenter;

/**
 * File Name:   IBaseView
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-17-12---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public interface IBaseView {
    /**
     * 获取对应的 Presenter
     * @return Presenter
     */
    BasePresenter getPresenter();

    /**
     * 创建布局视图
     * @param inflater  视图打气筒
     * @param container 父布局
     * @param savedInstanceState 数据储存
     * @return 布局视图
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 视图绑定
     * 推荐在此实现视图操作
     * @param savedInstanceState    数据储存
     */
    void bindView(Bundle savedInstanceState);

    /**
     * 获取 createView 创建的视图
     * @return  布局视图
     */
    View getView();

    /**
     * 获取布局ID
     * @return 布局ID
     */
    int getLayoutId();
}
