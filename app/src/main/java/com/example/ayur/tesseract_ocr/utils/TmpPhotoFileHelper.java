package com.example.ayur.tesseract_ocr.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.ayur.tesseract_ocr.common.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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

    public void writeToFile(Bitmap bitmap) {
        try {
            outStream = new FileOutputStream(createTmpFile());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
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

    public Bitmap readFromFile() {
        Bitmap bitmap = null;
        try {
            FileReader reader = new FileReader(file);
            try {
                int bit = reader.read();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
