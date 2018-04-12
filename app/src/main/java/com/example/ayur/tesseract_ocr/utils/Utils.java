package com.example.ayur.tesseract_ocr.utils;

import android.graphics.Bitmap;

import com.example.ayur.tesseract_ocr.common.Constants;
import com.googlecode.tesseract.android.TessBaseAPI;

public class Utils {

    public static String tesseractExtractText(Bitmap bitmap) {
        TessBaseAPI tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.init(Constants.DATA_PATH, "eng");
        tessBaseAPI.setImage(bitmap);
        String extractedText = tessBaseAPI.getUTF8Text();
        tessBaseAPI.end();
        return extractedText;
    }

}
