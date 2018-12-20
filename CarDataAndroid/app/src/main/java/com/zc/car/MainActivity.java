package com.zc.car;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.zc.car.bean.CarDataEntity;
import java.util.ArrayList;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {

    protected List<CarDataEntity> mAllList;

    public static final String SD_CARD = Environment.getExternalStorageDirectory()
        .getAbsolutePath();

    public static final String DB_NAME = SD_CARD + "/lite/orm/car.db";

    public static LiteOrm liteOrm;
    private ProgressDialog mProgressDialog;

    private static final int RC_LOCATION_CONTACTS_PERM = 124;

    private static final String[] LOCATION_AND_CONTACTS =
        { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mAllList = new ArrayList<>();

        if (savedInstanceState == null) {
            pushFragmentWithTitle(HomePageFragment.class, "MyVechicle", "MyVechicle", false);
        }

        if (hasStoragePermission()) {
            if (liteOrm == null) {
                DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
                config.debugged = true; // open the log
                config.dbVersion = 1; // set database version
                config.onUpdateListener = null; // set database update listener
                liteOrm = LiteOrm.newCascadeInstance(config);// cascade
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_download),
                RC_LOCATION_CONTACTS_PERM, LOCATION_AND_CONTACTS);
        }

    }


    private boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(this, LOCATION_AND_CONTACTS);
    }


    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        if (hasStoragePermission()) {
            if (liteOrm == null) {
                DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
                config.debugged = true; // open the log
                config.dbVersion = 1; // set database version
                config.onUpdateListener = null; // set database update listener
                liteOrm = LiteOrm.newCascadeInstance(config);// cascade
            }
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_download),
                RC_LOCATION_CONTACTS_PERM,
                LOCATION_AND_CONTACTS);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d("CAR", "onPermissionsGranted:" + requestCode + ":" + perms.size());
        if (hasStoragePermission()) {
            if (liteOrm == null) {
                DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
                config.debugged = true; // open the log
                config.dbVersion = 1; // set database version
                config.onUpdateListener = null; // set database update listener
                liteOrm = LiteOrm.newCascadeInstance(config);// cascade
            }
        }else {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d("CAR", "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_send:
                exit();
                return true;
            case R.id.menu_save:
                showProgress();
                saveAll();
                dismissProgress();
                return true;
            case R.id.menu_profile:
                // startActivity(new Intent(VipMainActivity.this, VipOrderListActivity.class));
                pushFragmentWithTitle(UserLoginFragment.class, "MyVechicle", "MyVechicle", false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean needChangeStatusBarColor() {
        return false;
    }


    @Override
    protected int getContainerId() {
        return R.id.fl_container;
    }


    private void exit() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("Are you sure?This will delete all entries.");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    liteOrm.deleteAll(CarDataEntity.class);
                    dialog.dismiss();
                }
            });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        dialog.show();
    }


    private void save() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("Save entries to DB first?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showProgress();
                    saveAll();
                    dismissProgress();
                    dialog.dismiss();
                }
            });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    MainActivity.this.finish();
                }
            });

        dialog.show();
    }


    private void saveAll() {
        if (mCarList != null && mCarList.size() > 0) {
            mAllList.addAll(mCarList);
        }
        if (mTruck5List != null && mTruck5List.size() > 0) {
            mAllList.addAll(mTruck5List);
        }
        if (mTruck10List != null && mTruck10List.size() > 0) {
            mAllList.addAll(mTruck10List);
        }
        if (mTipperList != null && mTipperList.size() > 0) {
            mAllList.addAll(mTipperList);
        }
        if (mArticulatedList != null && mArticulatedList.size() > 0) {
            mAllList.addAll(mArticulatedList);
        }
        liteOrm.insert(mAllList);
    }


    @Override
    public void onBackPressed() {
        save();
    }


    private void showProgress() {
        dismissProgress();
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("waitting~");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }


    private void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
