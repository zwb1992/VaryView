package com.zwb.varyview.varyview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zwb.varyview.R;

/**
 * Created by zwb
 * Description 空白，错误，加载中，网络设置等view切换
 * Date 17/9/16.
 */

public class VaryViewHelper implements IVaryViewStatus {
    private View dataView;
    private View errorView;
    private View emptyView;
    private View networkView;
    private View loadingView;
    private View currentView;
    private FrameLayout rootView;
    private Context mContext;
    private LayoutInflater inflater;
    private FrameLayout.LayoutParams rootParams;
    private OnRetryClickListener onRetryClickListener;

    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    public VaryViewHelper(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        errorView = inflater.inflate(R.layout.status_error, null);
        emptyView = inflater.inflate(R.layout.status_empty, null);
        loadingView = inflater.inflate(R.layout.status_loading, null);
        networkView = inflater.inflate(R.layout.status_network_error, null);
        registerListener();
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
        registerListener();
    }

    public void setErrorView(int id) {
        this.errorView = inflater.inflate(id, null);
        registerListener();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public void setEmptyView(int id) {
        this.emptyView = inflater.inflate(id, null);
    }

    public void setNetworkView(View networkView) {
        this.networkView = networkView;
        registerListener();
    }

    public void setNetworkView(int id) {
        this.networkView = inflater.inflate(id, null);
        registerListener();
    }


    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

    public void setLoadingView(int id) {
        this.loadingView = inflater.inflate(id, null);
    }


    @Override
    public void showContentView() {
        if (rootView == null || dataView == null) {
            return;
        }
        if (currentView != dataView) {//如果是当前view，将不切换
            rootView.removeAllViews();
            rootView.addView(dataView, rootParams);
            currentView = dataView;
        }
    }

    @Override
    public void showErrorView() {
        if (rootView == null || errorView == null) {
            return;
        }
        if (currentView != errorView) {//如果是当前view，将不切换
            rootView.removeAllViews();
            rootView.addView(errorView, rootParams);
            currentView = errorView;
        }
    }

    @Override
    public void showEmptyView() {
        if (rootView == null || emptyView == null) {
            return;
        }
        if (currentView != emptyView) {//如果是当前view，将不切换
            rootView.removeAllViews();
            rootView.addView(emptyView, rootParams);
            currentView = emptyView;
        }
    }

    @Override
    public void showLoadingView() {
        if (rootView == null || loadingView == null) {
            return;
        }
        if (currentView != loadingView) {//如果是当前view，将不切换
            rootView.removeAllViews();
            rootView.addView(loadingView, rootParams);
            currentView = loadingView;
        }
    }

    @Override
    public void showNetworkView() {
        if (rootView == null || networkView == null) {
            return;
        }
        if (currentView != networkView) {//如果是当前view，将不切换
            rootView.removeAllViews();
            rootView.addView(networkView, rootParams);
            currentView = networkView;
        }
    }

    @Override
    public void bindView(View dataView) {
        if (dataView == null) {
            return;
        }
        this.dataView = dataView;
        int index = 0;
        ViewGroup parent = (ViewGroup) dataView.getParent();
        if (parent == null) {
            parent = (ViewGroup) dataView.getRootView().findViewById(android.R.id.content);
        }
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            if (dataView == view) {
                index = i;
            }
        }
        rootView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = dataView.getLayoutParams();
        parent.removeView(dataView);
        rootParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(dataView, rootParams);
        View view = new View(mContext);
        view.setBackgroundColor(Color.WHITE);
        rootView.addView(view,rootParams);
        parent.addView(rootView, index, params);
    }

    /**
     * 注册监听器
     */
    private void registerListener() {
        if (errorView != null) {
            this.errorView.setClickable(true);
            this.errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRetryClickListener != null) {
                        onRetryClickListener.onRetryClick();
                    }
                }
            });
        }
        if (networkView != null) {
            this.networkView.setClickable(true);
            this.networkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    networkSetting();
                }
            });
        }
    }


    private void networkSetting() {
        Intent intent;
        if (android.os.Build.MANUFACTURER.equals("Meizu")) {
            intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        } else if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName(
                    "com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        mContext.startActivity(intent);
    }

    public interface OnRetryClickListener {
        void onRetryClick();
    }
}
