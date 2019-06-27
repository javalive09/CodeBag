package com.javalive09.demos;

import java.io.InputStream;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by peter on 2019-06-21
 */
public class HugeBitmap {

    @Run
    public void show(CodeActivity codeActivity) throws Exception{
        InputStream inputStream = codeActivity.getAssets().open("world.jpg");
        BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Resources resources = codeActivity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(0, 0, width, height), options);
        ImageView imageView = new ImageView(codeActivity);
        imageView.setImageBitmap(bitmap);
        codeActivity.setContentView(imageView);
    }

}
