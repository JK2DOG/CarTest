package com.zc.car;

import android.view.View;

import butterknife.OnClick;

public class HomePageFragment extends  PageFragment {









    @Override
    protected void initView(View rootView) {

    }




    @OnClick({})void onClick(View view){
        switch (view.getId()){
            case R.id.btn_car:
                break;
            case R.id.btn_5t:
                break;
            case R.id.btn_10t:
                break;
            case R.id.btn_tip:
                break;
            case R.id.btn_art:
                break;
        }
    }



    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_page;
    }
}
