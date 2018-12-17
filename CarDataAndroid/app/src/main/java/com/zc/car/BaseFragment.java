package com.zc.car;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dongjijin on 2017/10/20 0020.
 */

public abstract class BaseFragment<T extends Activity> extends Fragment {
    private Unbinder unbinder;
    protected T activity;
    protected View mRootView;
    protected boolean isBinded = false;
    protected boolean isRestored = false;

    protected abstract void initView(View rootView);

    protected abstract int getLayoutResId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (T) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        int layoutId = getLayoutResId();
        if (mRootView == null) {
            mRootView = inflater.inflate(layoutId, container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        unbinder = ButterKnife.bind(this, mRootView);
        isBinded = true;
        isRestored = (savedInstanceState != null);
        initView(mRootView);
        return mRootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isBinded = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView != null && isBinded) {
            if (isVisibleToUser) {
                onUserVisible();
            } else {
                onUserInvisible();
            }
        }
    }

    protected void onUserInvisible() {

    }

    protected void onUserVisible() {

    }


}
