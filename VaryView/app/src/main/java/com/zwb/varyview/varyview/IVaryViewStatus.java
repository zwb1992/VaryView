package com.zwb.varyview.varyview;

import android.view.View;

/**
 * Created by zwb
 * Description
 * Date 17/9/16.
 */

public interface IVaryViewStatus {

    /**
     * 显示内容区域
     */
    void showContentView();

    /**
     * 显示错误view
     */
    void showErrorView();

    /**
     * 显示空白view
     */
    void showEmptyView();

    /**
     * 显示加载中view
     */
    void showLoadingView();

    /**
     * 显示网络设置view
     */
    void showNetworkView();

    /**
     * 绑定内容视图
     *
     * @param view
     */
    void bindView(View view);
}
