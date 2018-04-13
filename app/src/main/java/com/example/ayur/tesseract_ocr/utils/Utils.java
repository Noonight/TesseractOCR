package com.example.ayur.tesseract_ocr.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import com.example.ayur.tesseract_ocr.common.Log;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

public class Utils {

    public static String tesseractExtractText(Bitmap bitmap) {
        TessBaseAPI tessBaseAPI = new TessBaseAPI();
        //tessBaseAPI.init(Constants.DATA_PATH, "eng");

        String datapath = Environment.getExternalStorageDirectory() + "/TesseractOCR/";
        String language = "eng";
        File dir = new File(datapath + "tessdata/");
        if (!dir.exists())
            Log.d(String.valueOf(dir.mkdirs()));
        tessBaseAPI.init(datapath, language);

        tessBaseAPI.setImage(bitmap);
        String extractedText = tessBaseAPI.getUTF8Text();
        tessBaseAPI.end();
        return extractedText;
    }

}
