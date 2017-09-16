package com.zwb.varyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewHelper().bindView(findViewById(R.id.tv_content));
    }

    /**
     * 显示内容
     *
     * @param view
     */
    public void button1(View view) {
        getViewHelper().showContentView();
    }

    /**
     * 加载中
     *
     * @param view
     */
    public void button2(View view) {
        getViewHelper().showLoadingView();
    }

    /**
     * 空白
     *
     * @param view
     */
    public void button3(View view) {
        getViewHelper().showEmptyView();
    }

    /**
     * 错误
     *
     * @param view
     */
    public void button4(View view) {
        getViewHelper().showErrorView();
    }

    /**
     * 网络设置
     *
     * @param view
     */
    public void button5(View view) {
        getViewHelper().showNetworkView();
    }

    @Override
    protected void onRetry() {
        super.onRetry();
        Toast.makeText(this, "重试...", Toast.LENGTH_SHORT).show();
    }
}
