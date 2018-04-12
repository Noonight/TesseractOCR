package com.example.ayur.tesseract_ocr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ayur.tesseract_ocr.common.Constants;

public class ShHelper {
    private SharedPreferences sh;

    public ShHelper(Context context) {
        sh = context.getSharedPreferences(Constants.APP_SH, context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return sh.getString(key, null);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }
}
