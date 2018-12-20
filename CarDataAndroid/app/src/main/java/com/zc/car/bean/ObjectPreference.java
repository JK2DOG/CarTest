package com.zc.car.bean;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.google.gson.Gson;


public class ObjectPreference {

    static private final String PRF = ObjectPreference.class.getName();

    @Nullable
    static public <T> T getObject(Context context, Class<T> cls) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return new Gson().fromJson(prf.getString(cls.getName(), null), cls);
    }

    static public void saveObject(Context context, Object obj) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putString(obj.getClass().getName(), new Gson().toJson(obj));
        editor.apply();
    }

    static public <T> void clearObject(Context context, Class<T> cls) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.remove(cls.getName());
        editor.apply();
    }


    static public void saveString(Context context, String key, String str) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putString(key, str);
        editor.apply();
    }

    @Nullable
    static public String getString(Context context, String key) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getString(key, null);
    }

    static public void clear(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }


    static public void saveInt(Context context, String key, int i) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putInt(key, i);
        editor.apply();
    }

    @Nullable
    static public int getInt(Context context, String key) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getInt(key, 0);
    }

    static public void saveBoolean(Context context, String key, boolean flag) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, flag);
        editor.apply();
    }

    @Nullable
    static public boolean getBoolean(Context context, String key) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getBoolean(key, true);
    }
}
