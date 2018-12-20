package com.zc.car;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.zc.car.bean.CarDataEntity;
import com.zc.car.bean.DateUtils;

public class TypeCarFragment extends PageFragment {

    @BindView(R.id.tv_type) TextView mTVCarType;
    @BindView(R.id.et_driver) EditText mEtDriver;
    @BindView(R.id.et_rego) EditText mEtRego;
    @BindView(R.id.btn_start) Button mBtnStart;
    @BindView(R.id.btn_f_b) Button mBtnFB;
    @BindView(R.id.btn_s_b) Button mBtnSB;
    @BindView(R.id.btn_end) Button mBtnEnd;

    @BindView(R.id.tv_s_time) TextView mTvStart;
    @BindView(R.id.tv_f_b) TextView mTvFB;
    @BindView(R.id.tv_s_b) TextView mTvSB;
    @BindView(R.id.tv_e_t) TextView mTvEnd;

    private int mCurrentCarType;


    @Override
    protected void initView(View rootView) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCurrentCarType = arguments.getInt("type", 0);
            initContentView();
        }
    }


    private void initContentView() {
        mTVCarType.setText(CarDataEntity.getTypeStr(mCurrentCarType));

    }


    @OnClick({ R.id.btn_start, R.id.btn_f_b, R.id.btn_s_b, R.id.btn_end, R.id.btn_save_log,
                 R.id.btn_show_log, R.id.btn_pre, R.id.btn_next, R.id.btn_home })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                mBtnStart.setVisibility(View.INVISIBLE);
                mBtnStart.setEnabled(false);
                mBtnFB.setEnabled(true);
                mBtnFB.setVisibility(View.VISIBLE);
                mTvStart.setVisibility(View.VISIBLE);
                mTvStart.setText(DateUtils.getStringToday());
                break;
            case R.id.btn_f_b:
                mBtnFB.setVisibility(View.INVISIBLE);
                mBtnFB.setEnabled(false);
                mBtnSB.setEnabled(true);
                mBtnSB.setVisibility(View.VISIBLE);
                mTvFB.setVisibility(View.VISIBLE);
                mTvFB.setText(DateUtils.getStringToday());
                break;
            case R.id.btn_s_b:
                mBtnSB.setVisibility(View.INVISIBLE);
                mBtnSB.setEnabled(false);
                mBtnEnd.setEnabled(true);
                mBtnEnd.setVisibility(View.VISIBLE);
                mTvSB.setVisibility(View.VISIBLE);
                mTvSB.setText(DateUtils.getStringToday());
                break;
            case R.id.btn_end:
                mBtnEnd.setVisibility(View.INVISIBLE);
                mBtnEnd.setEnabled(false);
                mTvEnd.setVisibility(View.VISIBLE);
                mTvEnd.setText(DateUtils.getStringToday());
                break;
            case R.id.btn_save_log:
                String driver = mEtDriver.getText().toString().trim();
                String rego = mEtRego.getText().toString().trim();
                String s_time = mTvStart.getText().toString();
                String f_breake = mTvFB.getText().toString();
                String s_breake = mTvSB.getText().toString();
                String e_time = mTvEnd.getText().toString();
                if (TextUtils.isEmpty(driver) || TextUtils.isEmpty(rego) ||
                    TextUtils.isEmpty(s_time) || TextUtils.isEmpty(f_breake) ||
                    TextUtils.isEmpty(s_breake) || TextUtils.isEmpty(e_time)) {
                    Toast.makeText(getContext(),
                        "Entry not saved as not all data entered.\nComplete all entries and try again.",
                        Toast.LENGTH_SHORT).show();
                } else {
                    switch (mCurrentCarType) {
                        case CarDataEntity.CAR:
                            activity.mCarList.add(
                                new CarDataEntity(mCurrentCarType, driver, rego, s_time, f_breake,
                                    s_breake, e_time));
                            Toast.makeText(getContext(),
                                "Save Success",
                                Toast.LENGTH_SHORT).show();
                            break;
                        case CarDataEntity.TRUCK_5:
                            activity.mTruck5List.add(
                                new CarDataEntity(mCurrentCarType, driver, rego, s_time, f_breake,
                                    s_breake, e_time));
                            Toast.makeText(getContext(),
                                "Save Success",
                                Toast.LENGTH_SHORT).show();
                            break;
                        case CarDataEntity.TRUCK_10:
                            activity.mTruck10List.add(
                                new CarDataEntity(mCurrentCarType, driver, rego, s_time, f_breake,
                                    s_breake, e_time));
                            Toast.makeText(getContext(),
                                "Save Success",
                                Toast.LENGTH_SHORT).show();
                            break;
                        case CarDataEntity.TIPPER:
                            activity.mTruck10List.add(
                                new CarDataEntity(mCurrentCarType, driver, rego, s_time, f_breake,
                                    s_breake, e_time));
                            Toast.makeText(getContext(),
                                "Save Success",
                                Toast.LENGTH_SHORT).show();
                            break;
                        case CarDataEntity.ARTICULATED:
                            activity.mArticulatedList.add(
                                new CarDataEntity(mCurrentCarType, driver, rego, s_time, f_breake,
                                    s_breake, e_time));
                            Toast.makeText(getContext(),
                                "Save Success",
                                Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                break;
            case R.id.btn_show_log:
                Bundle bundle = new Bundle();
                bundle.putInt("type", mCurrentCarType);
                activity.pushFragmentWithExtraAndTitle(CarListFragment.class, "MyVechicle",
                    mCurrentTitle, bundle, false);
                break;
            case R.id.btn_pre:
                activity.back();
                break;
            case R.id.btn_next:
                Bundle nextBundle = new Bundle();
                nextBundle.putInt("type", mCurrentCarType);
                activity.pushFragmentWithExtraAndTitle(TypeCarFragment.class, "MyVechicle",
                    mCurrentTitle, nextBundle, false);
                break;
            case R.id.btn_home:
                activity.mFragmentStack.push(HomePageFragment.class,"MyVechicle",
                    mCurrentTitle,true);
                break;
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_type_car;
    }
}
