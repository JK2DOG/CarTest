package com.zc.car;

import android.os.Bundle;

public class MainActivity extends FragmentActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            pushFragmentWithTitle(HomePageFragment.class, "MyVechicle", "MyVechicle", false);
        }
    }

    @Override
    public boolean needChangeStatusBarColor() {
        return false;
    }


    @Override
    protected int getContainerId() {
        return R.id.fl_container;
    }
}
