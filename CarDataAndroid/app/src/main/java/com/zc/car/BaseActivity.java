package com.zc.car;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import butterknife.ButterKnife;


/**
 * Created by dongjijin on 2017/10/24 0024.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected View mStatusView;

    protected abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    public abstract boolean needChangeStatusBarColor();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        注册生命周期
        setContentView(getLayoutResId());
//        setStatusBarStyle();
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    private void setStatusBarStyle() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (needChangeStatusBarColor()) {
            // 生成一个状态栏大小的矩形
            mStatusView = createStatusBarView();
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            decorView.addView(mStatusView);

            ViewGroup contentView = findViewById(android.R.id.content);
            ViewGroup rootView = (ViewGroup) contentView.getChildAt(0);
            //如果不是设置参数，会使内容显示到状态栏上
            rootView.setFitsSystemWindows(true);
        }
    }

    /**
     * 重置状态栏样式
     */
    public void resetStatusBarStyle(int value, boolean isColor) {
        if (mStatusView == null) {
            // 生成一个状态栏大小的矩形
            mStatusView = createStatusBarView();
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            decorView.addView(mStatusView);

            ViewGroup contentView = findViewById(android.R.id.content);
            ViewGroup rootView = (ViewGroup) contentView.getChildAt(0);
            //如果不是设置参数，会使内容显示到状态栏上
            rootView.setFitsSystemWindows(true);
        }
        if (isColor) {
            mStatusView.setBackgroundColor(value);
        } else {
            mStatusView.setBackgroundResource(value);
        }
    }

    /**
     * 设置状态栏替代背景
     *
     * @return
     */
    public int getStatusBarColorResId() {
        return -1;
    }

    /**
     * 设置状态栏替代颜色
     *
     * @return
     */
    public int getStatusBarColor() {
        return Color.WHITE;
    }


    private View createStatusBarView() {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                DeviceHelper.getStatusBarHeight(this));
//        statusBarView.setLayoutParams(params);
//        if (getStatusBarColorResId() > 0) {
//            statusBarView.setBackgroundResource(getStatusBarColorResId());
//        } else {
//            statusBarView.setBackgroundColor(getStatusBarColor());
//        }
        return statusBarView;
    }


}
