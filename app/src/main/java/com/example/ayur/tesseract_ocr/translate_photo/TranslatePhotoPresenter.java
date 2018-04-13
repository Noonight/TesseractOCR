package com.example.ayur.tesseract_ocr.translate_photo;

import android.graphics.Bitmap;

import com.example.ayur.tesseract_ocr.utils.Utils;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public class TranslatePhotoPresenter extends MvpBasePresenter<TranslatePhotoView> {

    public void convertPhotoToText(Bitmap photo) {
        getView().setTranslatedText(Utils.tesseractExtractText(photo));
        //Utils.tesseractExtractText(photo);
    }

}
