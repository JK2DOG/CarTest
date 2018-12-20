package com.zc.car;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.zc.car.bean.ObjectPreference;
import com.zc.car.bean.User;

public class UserLoginFragment extends PageFragment {

    @BindView(R.id.et_name) EditText mEtName;
    @BindView(R.id.et_pwd) EditText mEtPwd;

    @BindView(R.id.et_pwd2) EditText mEtPwd2;


    @Override protected void initView(View rootView) {

    }


    @Override protected int getLayoutResId() {
        return R.layout.fragment_login_uer;
    }


    @OnClick({ R.id.btn_save_user, R.id.btn_cancel })
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_user:
                String name = mEtName.getText().toString().trim();
                String pwd = mEtPwd.getText().toString().trim();
                String pwd2 = mEtPwd2.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) ||
                    TextUtils.isEmpty(pwd2)) {
                    Toast.makeText(getContext(),
                        "have something empty.",
                        Toast.LENGTH_SHORT).show();
                } else {
                    ObjectPreference.saveObject(getContext(),new User(name,pwd,pwd2));
                    Toast.makeText(getContext(),
                        "Save User Success.",
                        Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                activity.back();
                break;
        }
    }

}
