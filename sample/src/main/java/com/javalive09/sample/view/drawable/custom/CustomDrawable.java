package com.javalive09.sample.view.drawable.custom;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.javalive09.codebag.Entry;

/**
 * Created by peter on 2017/7/11.
 */

public class CustomDrawable extends Entry {

    int i = 1;

    public void show() {
        TextDrawable drawable = new TextDrawable(getActivity());
        drawable.setText(i);
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundDrawable(drawable);
        showView(imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drawable.setText(i++);
                return false;
            }
        });
    }

}
