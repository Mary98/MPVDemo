package com.mary.mpvdemo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mary.mpvdemo.R;
import com.mary.mpvdemo.presenter.BasePresenter;
import com.mary.mpvdemo.utils.ActivityManager;
import com.mary.mpvdemo.utils.ContextUtils;
import com.mary.mpvdemo.utils.SystemBarTintManager;
import com.mary.mpvdemo.view.impl.IBaseView;

import butterknife.ButterKnife;

/**
 * File Name:   BaseActivity
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description: 基础 Activity
 * Change log:
 * 16/3/12-17-29---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public abstract class BaseActivity<T extends BasePresenter<IBaseView>> extends Activity implements IBaseView {
    /** 主线程 */
    private long mUIThreadId;

    public void setActionBar() {}

    public void getIntentValue() {}

    @Override
    protected void onResume() {
        super.onResume();
        /*MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(this);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(this);*/
    }

    /**
     * 是否设置沉浸式
     * @return 是否设置了沉浸式
     */
    protected boolean isSetStatusBar() {
        return false;
    }

    /** 对应的 Presenter */
    protected BasePresenter mPresenter;
    private Toolbar mToolBar;
    protected View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIThreadId = android.os.Process.myTid();
        ActivityManager.getInstance().addActivity(this);
        mPresenter = getPresenter();
        if (null != mPresenter && this instanceof IBaseView) {
            mPresenter.attach(this);
        }
        initWindow();
        getIntentValue();
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        mToolBar = (Toolbar) this.findViewById(getToolBarId());
        setActionBar();
        bindView(savedInstanceState);
    }

    public abstract int getToolBarId();

    private SystemBarTintManager tintManager;

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isSetStatusBar()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.material_drawer_primary);
        }
    }

    protected  void setStatusBarTintRes(int color) {
        if (null != tintManager) {
            tintManager.setStatusBarTintResource(color);
        }
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ContextUtils.inflate(this, getLayoutId());
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 获取主布局
     * @return  布局
     */
    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mUIThreadId = android.os.Process.myTid();
        super.onNewIntent(intent);
    }

    /**
     * 获取UI线程ID
     *
     * @return UI线程ID
     */
    public long getUIThreadId() {
        return mUIThreadId;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
