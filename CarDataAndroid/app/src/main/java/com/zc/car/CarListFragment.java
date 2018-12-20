package com.zc.car;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zc.car.bean.CarDataEntity;
import com.zc.car.bean.LinearLayoutSpaceItemDecoration;
import java.util.List;

public class CarListFragment extends PageFragment {

    @BindView(R.id.rv_car) RecyclerView mRecyclerView;

    @BindView(R.id.btn_retrun) Button mBtnReturn;

    private CarAdapter mCarAdapter;

    private int mCurrentCarType;


    @Override protected void initView(View rootView) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCurrentCarType = arguments.getInt("type", 0);
            initContentView();
        }
    }


    private void initContentView() {
        mBtnReturn.setText("Return to" + CarDataEntity.getTypeStr(mCurrentCarType));
        switch (mCurrentCarType) {
            case CarDataEntity.CAR:
                mCarAdapter = new CarAdapter(activity.mCarList);
                break;
            case CarDataEntity.TRUCK_5:
                mCarAdapter = new CarAdapter(activity.mTruck5List);
                break;
            case CarDataEntity.TRUCK_10:
                mCarAdapter = new CarAdapter(activity.mTruck10List);
                break;
            case CarDataEntity.TIPPER:
                mCarAdapter = new CarAdapter(activity.mTipperList);
                break;
            case CarDataEntity.ARTICULATED:
                mCarAdapter = new CarAdapter(activity.mArticulatedList);
                break;
        }
        initRecyclerViewSpaceVNoFirst(getContext(), mRecyclerView, mCarAdapter, 1);
    }


    @OnClick(R.id.btn_retrun) void onClick() {
        activity.back();
    }


    @Override protected int getLayoutResId() {
        return R.layout.fragment_car_list;
    }


    private static class CarAdapter extends BaseQuickAdapter<CarDataEntity, BaseViewHolder> {

        public CarAdapter(@Nullable List<CarDataEntity> data) {
            super(R.layout.item_car, data);

        }


        @Override
        protected void convert(BaseViewHolder helper, CarDataEntity item) {
            helper.setText(R.id.tv_content,
                item.getDriver() + " " + item.getRego() + " Star:" + item.getS_time() +
                    " 1st break:" + item.getF_breake() + " 2st break:" + item.getS_breake() +
                    " End:" + item.getE_time());

        }
    }


    /**
     * 垂直方向Space上下左右无背景间隔第一个没有itemdecoration
     */
    private void initRecyclerViewSpaceVNoFirst(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
            new LinearLayoutSpaceItemDecoration.Builder().setSpaceSize(space).build());
        view.setAdapter(adapter);
    }

}
