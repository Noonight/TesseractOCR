package com.example.ayur.tesseract_ocr.translate_photo;

import android.graphics.Bitmap;

import com.example.ayur.tesseract_ocr.App;
import com.example.ayur.tesseract_ocr.common.Log;
import com.example.ayur.tesseract_ocr.utils.TesseractHelper;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.io.File;

public class TranslatePhotoPresenter extends MvpBasePresenter<TranslatePhotoView> {

    private TesseractHelper tesseractHelper;

    public TranslatePhotoPresenter() {

    }

    public void convertPhotoToText(Bitmap bitmap) {
        //getView().setTranslatedText(tesseractExtractText(photo));

        String result;

        if (tesseractHelper != null) {
            result = tesseractHelper.doOCR(bitmap);
        } else {
            tesseractHelper = new TesseractHelper(App.getInstance(), TesseractHelper.RU);
            result = tesseractHelper.doOCR(bitmap);
        }
        getView().setTranslatedText(result);

        //Utils.tesseractExtractText(photo);
    }

    public  String tesseractExtractText(Bitmap bitmap) {
        TessBaseAPI tessBaseAPI = new TessBaseAPI();
        //tessBaseAPI.init(Constants.DATA_PATH, "eng");

        String datapath = "file:///android_asset/tesseract_ocr/";
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
