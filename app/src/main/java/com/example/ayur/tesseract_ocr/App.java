package com.example.ayur.tesseract_ocr;

import android.app.Application;

import com.example.ayur.tesseract_ocr.utils.ShHelper;
import com.example.ayur.tesseract_ocr.utils.TmpPhotoFileHelper;

public class App extends Application {

    private static App instance;

    private static TmpPhotoFileHelper photoFileHelper;
    private static ShHelper shHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        photoFileHelper = new TmpPhotoFileHelper(instance);
        shHelper = new ShHelper(instance);
    }

    public static App getInstance() {
        return instance;
    }

    public static TmpPhotoFileHelper getPhotoFileHelper() {
        return photoFileHelper;
    }

    public static ShHelper getShHelper() {
        return shHelper;
    }
}
