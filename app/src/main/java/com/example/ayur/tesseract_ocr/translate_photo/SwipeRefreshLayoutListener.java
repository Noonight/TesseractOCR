package com.example.ayur.tesseract_ocr.translate_photo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutListener implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshCallback swipeRefreshCallback;

    public SwipeRefreshLayoutListener(SwipeRefreshCallback swipeRefreshCallback) {
        this.swipeRefreshCallback = swipeRefreshCallback;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshCallback.refreshCallback();
            }
        }, 2000);
    }

    public interface SwipeRefreshCallback {
        public void refreshCallback();
    }
}
