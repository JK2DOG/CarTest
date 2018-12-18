package com.zc.car;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;

/**
 * Created by margin on 2017/11/1.
 * 承载 Fragment 的 Activity
 * 包含了一个 ToolBa
 */

public abstract class FragmentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    protected FragmentStack mFragmentStack = new FragmentStack(
            getSupportFragmentManager(),
            getContainerId());

    protected abstract int getContainerId();


    @Override
    protected void initView(Bundle savedInstanceState) {
//        mToolbar.setTitle("");
//        mToolbar.setTitleTextColor(Color.WHITE);
//        mToolbar.setNavigationIcon(getNavigationIconId());
        initToolBar(mToolbar);
        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                back();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        back();
    }


    public void pushFragmentWithTitle(Class<? extends BaseFragment<? extends FragmentActivity>> clz,
                                      String newTitle, String oldTitle, boolean smooth) {
        mFragmentStack.pushWithTitle(clz, newTitle, oldTitle, smooth);
    }

    public void pushFragmentWithExtraAndTitle(Class<? extends BaseFragment<? extends FragmentActivity>> clz,
                                              String newTitle, String oldTitle,
                                              Bundle extra,
                                              boolean smooth) {
        mFragmentStack.pushWithExtraAndTitle(clz, extra, newTitle, oldTitle, smooth);
    }

    public void back() {
        if (mFragmentStack.stackCount() == 1) {
            finish();
        } else {
            mFragmentStack.pop();
        }
    }


    protected void initToolBar(Toolbar toolbar) {

    }

     public void setTitle(String title) {
        mToolbar.setTitle(title);
    }


//    public int getNavigationIconId() {
//        return R.drawable.back;
//    }
}
