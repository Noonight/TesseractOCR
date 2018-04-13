package com.example.ayur.tesseract_ocr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    private BitmapFactory bitmapFactory;

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
            file = createTmpFile();
            outStream = new FileOutputStream(file);
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

    public File getFile() {
        return file;
    }

    public Bitmap readFromFile() {
        Bitmap bitmap;

        bitmap = BitmapFactory.decodeFile(file.getPath());

        return bitmap;
    }


}
