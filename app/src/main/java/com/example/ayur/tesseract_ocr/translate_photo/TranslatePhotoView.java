package com.example.ayur.tesseract_ocr.translate_photo;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface TranslatePhotoView extends MvpView {

    public void setTranslatedText(String text);

    public void showDataLayer();

    public void hideDataLayer();

    public void showError();

    public void showLoading();

}
