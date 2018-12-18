package com.zc.car;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by margin on 2017/11/17.
 * 除去一级界面,其余的 Fragment 页面的父类
 */

public abstract class PageFragment extends BaseFragment<FragmentActivity> {
    protected String mCurrentTitle;
    protected String mPrevTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                mCurrentTitle = arguments.getString("currentTitle", "");
                mPrevTitle = arguments.getString("prevTitle", "");
            }
        } else {
            mCurrentTitle = savedInstanceState.getString("currentTitle", "");
            mPrevTitle = savedInstanceState.getString("prevTitle", "");
        }
        activity.setTitle(mCurrentTitle);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!TextUtils.isEmpty(mPrevTitle)) {
            activity.setTitle(mPrevTitle);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentTitle", mCurrentTitle);
        outState.putString("prevTitle", mPrevTitle);
    }
}
