package com.example.ayur.tesseract_ocr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.ayur.tesseract_ocr.common.Log;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TesseractHelper {

    private TessBaseAPI tessBaseApi;

    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/tesseract_ocr/";
    private static final String TESSDATA = "tessdata";

    public static final String ENG = "eng";
    public static final String RU = "rus";

    private String lang;
    private Context mContext;

    public TesseractHelper(Context context, String lang) {
        mContext = context;
        this.lang = lang;
    }

    public String doOCR(Bitmap bitmap) {
        prepareTesseract(mContext);
        String value = startOCR(bitmap);
        Log.d(value);

        return value;
    }

    private String startOCR(Bitmap bitmap) {
        String result = "nothing translate";
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
            //Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);

            result = extractText(bitmap);



        } catch (Exception e) {
            Log.d(e.getMessage());
        }
        return result;
    }

    private void prepareDirectory(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.d("ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
            }
        } else {
            Log.d("Created directory " + path);
        }
    }


    private void prepareTesseract(Context context) {
        try {
            prepareDirectory(DATA_PATH + TESSDATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        copyTessDataFiles(TESSDATA, context);
    }

    /**
     * Copy tessdata files (located on assets/tessdata) to destination directory
     *
     * @param path - name of directory with .traineddata files
     */
    private void copyTessDataFiles(String path, Context context) {
        try {
            String fileList[] = context.getAssets().list(path);

            for (String fileName : fileList) {

                // open file within the assets folder
                // if it is not already there copy it to the sdcard
                String pathToDataFile = DATA_PATH + path + "/" + fileName;
                if (!(new File(pathToDataFile)).exists()) {

                    InputStream in = context.getAssets().open(path + "/" + fileName);

                    OutputStream out = new FileOutputStream(pathToDataFile);

                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                    Log.d("Copied " + fileName + "to tessdata");
                }
            }
        } catch (IOException e) {
            Log.d("Unable to copy files to tessdata " + e.toString());
        }
    }

    private String extractText(Bitmap bitmap) {
        try {
            tessBaseApi = new TessBaseAPI();
        } catch (Exception e) {
            Log.d(e.getMessage());
            if (tessBaseApi == null) {
                Log.d("TessBaseAPI is null. TessFactory not returning tess object.");
            }
        }

        tessBaseApi.init(DATA_PATH, lang);

//       //EXTRA SETTINGS
//        //For example if we only want to detect numbers
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
//
//        //blackList Example
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-qwertyuiop[]}{POIU" +
//                "YTRWQasdASDfghFGHjklJKLl;L:'\"\\|~`xcvXCVbnmBNM,./<>?");

        Log.d("Training file loaded");
        tessBaseApi.setImage(bitmap);
        String extractedText = "empty result";
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (Exception e) {
            Log.d("Error in recognizing text.");
        }
        tessBaseApi.end();
        return extractedText;
    }

}
