package com.example.ayur.tesseract_ocr.photo;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface PhotoView extends MvpView {

    public void showError();

    public void showLoading();
}
