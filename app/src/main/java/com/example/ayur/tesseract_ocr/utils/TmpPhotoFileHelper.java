package com.example.ayur.tesseract_ocr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ayur.tesseract_ocr.App;
import com.example.ayur.tesseract_ocr.common.Constants;
import com.example.ayur.tesseract_ocr.common.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TmpPhotoFileHelper {

    private File file;
    private Context context;
    private FileOutputStream outStream;

    public TmpPhotoFileHelper(Context context) {
        this.context = context;
        //file = new File(context.getCacheDir(), Constants.CURRENT_PHOTO);
    }

    private File createTmpFile() {
        try {
            file = File.createTempFile(Constants.CURRENT_PHOTO, null, context.getCacheDir());
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeFilePathToSh() {
        ShHelper shHelper = new ShHelper(context);
        shHelper.putString(Constants.PHOTO_PATH, file.getPath());
        Log.d(shHelper.getString(Constants.PHOTO_PATH));
    }

    public void writeToFile(Bitmap bitmap) {
        try {
            file = createTmpFile();
            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            writeFilePathToSh();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }

    public Bitmap readFromFile() {
        return BitmapFactory.decodeFile(App.getShHelper().getString(Constants.PHOTO_PATH));
    }


}
