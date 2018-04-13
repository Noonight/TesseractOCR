package com.example.ayur.tesseract_ocr;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ayur.tesseract_ocr.common.Constants;
import com.example.ayur.tesseract_ocr.common.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_main)
    ViewPager viewPager;
    @BindView(R.id.tab_main)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        SimpleFragmentPagerAdapter fragmentPagerAdapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Log.d(Constants.DATA_PATH);

        AssetManager myAssetManager = getApplicationContext().getAssets();

        try {
            Log.d(String.valueOf(this.getResources().getAssets().open("tessdata").read()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getRoots();

        try {
            String[] Files = myAssetManager.list(""); // массив имен файлов
            for (String s : Files) {
                Log.d(s);
            }
            Log.d(Arrays.toString(myAssetManager.getLocales()) +"");
            //Toast.makeText(getApplicationContext(), Files[0] + ", " + Files[1], Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void getRoots() {
        File file = new File(Environment.getExternalStorageDirectory() + "/tesseract_ocr/");
        Log.d(file.getPath());
        Log.d(file.getName());
    }

}
