package com.zwb.varyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zwb.varyview.varyview.VaryViewHelper;

/**
 * Created by zwb
 * Description
 * Date 17/9/16.
 */

public class BaseActivity extends AppCompatActivity {

    private VaryViewHelper viewHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected VaryViewHelper getViewHelper() {
        if (viewHelper == null) {
            synchronized (BaseActivity.class) {
                if (viewHelper == null) {
                    viewHelper = new VaryViewHelper(this);
                    viewHelper.setOnRetryClickListener(new VaryViewHelper.OnRetryClickListener() {
                        @Override
                        public void onRetryClick() {
                            onRetry();
                        }
                    });
                }
            }
        }
        return viewHelper;
    }

    /**
     * 重试
     */
    protected void onRetry() {

    }
}
