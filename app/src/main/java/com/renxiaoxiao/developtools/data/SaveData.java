package com.renxiaoxiao.developtools.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.renxiaoxiao.developtools.utils.StringAction;

/**
 * Created by renxiaoxiao on 2017/6/10.
 * SharedPreferences便捷实例类
 */

public class SaveData {
    private SharedPreferences Obj = null;

    public void open(Context ct, String fileName) {
        Obj = ct.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key, boolean value) {
        if (Obj != null) {
            return Obj.getBoolean(key, value);
        }
        return false;
    }

    public boolean setBoolean(String key, boolean value) {
        if (Obj != null) {
            SharedPreferences.Editor editor = Obj.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }
        return false;
    }

    public int getInt(String key, int value) {
        if (Obj != null) {
            String sValue = Obj.getString(key, null);
            if (sValue != null && StringAction.isNumber(sValue)) {
                return StringAction.toNumber(sValue);
            }
        }
        return value;
    }

    public  boolean setInt(String key, int value) {
        if (Obj != null) {
            SharedPreferences.Editor editor = Obj.edit();
            String sValue = String.valueOf(value);
            editor.putString(key, sValue);
            return editor.commit();
        }
        return false;
    }

    public String getString(String key, String value) {
        if (Obj != null) {
            return Obj.getString(key, value);
        }
        return null;
    }

    public boolean setString(String key, String value) {
        if (Obj != null) {
            SharedPreferences.Editor editor = Obj.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public boolean contains(String key) {
        if (Obj != null) {
            return Obj.contains(key);
        }
        return false;
    }
}
