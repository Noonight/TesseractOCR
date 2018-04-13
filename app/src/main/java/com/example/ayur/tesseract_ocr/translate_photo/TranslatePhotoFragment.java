package com.example.ayur.tesseract_ocr.translate_photo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayur.tesseract_ocr.App;
import com.example.ayur.tesseract_ocr.MainActivity;
import com.example.ayur.tesseract_ocr.R;
import com.example.ayur.tesseract_ocr.utils.TmpPhotoFileHelper;
import com.example.ayur.tesseract_ocr.utils.Utils;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslatePhotoFragment extends MvpFragment<TranslatePhotoView, TranslatePhotoPresenter> implements TranslatePhotoView {

    @BindView(R.id.not_data_layer_fragment_tranlsate_photo)
    FrameLayout fNoDataLayerFrame;
    @BindView(R.id.data_layer_fragment_translate_photo)
    FrameLayout fDataLayerFrame;
    @BindView(R.id.tv_translated_photo)
    TextView tvTranslatedPhoto;

    private TmpPhotoFileHelper photoFileHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        photoFileHelper = new TmpPhotoFileHelper(getActivity());
    }

    private void checkPhoto() {
        if (App.getInstance().getPhotoFileHelper().readFromFile() == null) {
            hideDataLayer();
        } else {
            showDataLayer();
            getPresenter().convertPhotoToText(photoFileHelper.readFromFile());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        checkPhoto();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate_photo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public TranslatePhotoPresenter createPresenter() {
        return new TranslatePhotoPresenter();
    }

    @Override
    public void setTranslatedText(String text) {
        tvTranslatedPhoto.setText(text);
    }

    @Override
    public void showDataLayer() {
        fDataLayerFrame.setVisibility(View.VISIBLE);
        fNoDataLayerFrame.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideDataLayer() {
        fDataLayerFrame.setVisibility(View.INVISIBLE);
        fNoDataLayerFrame.setVisibility(View.VISIBLE);
    }


    @Override
    public void showError() {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "loading ...", Toast.LENGTH_SHORT).show();
    }
}
