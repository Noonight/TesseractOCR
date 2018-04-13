package com.example.ayur.tesseract_ocr.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayur.tesseract_ocr.App;
import com.example.ayur.tesseract_ocr.R;
import com.example.ayur.tesseract_ocr.utils.TmpPhotoFileHelper;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoFragment extends MvpFragment<PhotoView, PhotoPresenter> implements PhotoView {

    @BindView(R.id.btn_startCam)
    Button btnStartCam;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    private Bitmap photo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        //btnStartCam.setOnClickListener(view -> startCamera());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        btnStartCam.setOnClickListener(view -> startCamera());
    }

    @Override
    public PhotoPresenter createPresenter() {
        return new PhotoPresenter();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Some error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
    }

    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photo = (Bitmap) data.getExtras().get("data");
        ivPhoto.setImageBitmap(photo);
        App.getInstance().getPhotoFileHelper().writeToFile(photo);
    }
}
