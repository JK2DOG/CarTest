package com.zc.car;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.zc.car.bean.CarDataEntity;

public class HomePageFragment extends PageFragment {

    @Override
    protected void initView(View rootView) {

    }


    @OnClick({ R.id.btn_car, R.id.btn_5t, R.id.btn_10t, R.id.btn_tip, R.id.btn_art })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_car:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", CarDataEntity.CAR);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, bundle1, false);
                break;
            case R.id.btn_5t:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", CarDataEntity.TRUCK_5);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, bundle2, false);
                break;
            case R.id.btn_10t:
                Bundle bundle3 = new Bundle();
                bundle3.putInt("type", CarDataEntity.TRUCK_10);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, bundle3, false);
                break;
            case R.id.btn_tip:
                Bundle bundle4 = new Bundle();
                bundle4.putInt("type", CarDataEntity.TIPPER);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, bundle4, false);
                break;
            case R.id.btn_art:
                Bundle bundle5 = new Bundle();
                bundle5.putInt("type", CarDataEntity.ARTICULATED);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, bundle5, false);
                break;
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_page;
    }
}
