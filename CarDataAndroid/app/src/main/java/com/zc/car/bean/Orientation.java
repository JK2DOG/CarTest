package com.zc.car.bean;

import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@IntDef({ LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL})
@Retention(RetentionPolicy.SOURCE)
public @interface Orientation {
}
