package com.example.ayur.tesseract_ocr;

import android.app.Application;
import android.util.Log;

import com.example.ayur.tesseract_ocr.utils.TmpPhotoFileHelper;

public class App extends Application {

    private static App instance;

    private TmpPhotoFileHelper photoFileHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public void createPhotoFileHelper() {
        if (photoFileHelper == null)
            photoFileHelper = new TmpPhotoFileHelper(instance);
    }

    public TmpPhotoFileHelper getPhotoFileHelper() {
        if (photoFileHelper != null)
            return photoFileHelper;
        else {
            Log.d("App", "PhotoFileHelper is null " + photoFileHelper);
            photoFileHelper = new TmpPhotoFileHelper(instance);
            return photoFileHelper;
        }
    }
}
