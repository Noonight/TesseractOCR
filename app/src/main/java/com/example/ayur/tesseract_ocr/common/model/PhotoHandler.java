package com.example.ayur.tesseract_ocr.common.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotoHandler implements Parcelable{

    private Bitmap photo;

    public PhotoHandler(Bitmap photo) {
        this.photo = photo;
    }

    protected PhotoHandler(Parcel in) {
        photo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<PhotoHandler> CREATOR = new Creator<PhotoHandler>() {
        @Override
        public PhotoHandler createFromParcel(Parcel in) {
            return new PhotoHandler(in);
        }

        @Override
        public PhotoHandler[] newArray(int size) {
            return new PhotoHandler[size];
        }
    };

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(photo, flags);
    }
}
