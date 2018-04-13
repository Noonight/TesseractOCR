package com.example.ayur.tesseract_ocr;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ayur.tesseract_ocr.photo.PhotoFragment;
import com.example.ayur.tesseract_ocr.translate_photo.TranslatePhotoFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhotoFragment();
            case 1:
                return new TranslatePhotoFragment();
            default:
                return new PhotoFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.create_photo);
            case 1:
                return context.getString(R.string.translated_photo);
            default:
                return context.getString(R.string.create_photo);
        }
    }
}
