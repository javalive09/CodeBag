package com.javalive09.demos.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * onResume 中post 和 onMeasure onLayout onDraw的顺序
 */
public class PostActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView view = new MyView(this);
        view.setText("peter");
        setContentView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Log.i("peter", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("peter", "onRsume");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("peter", "onRsume post");
            }
        });

    }

    private static class MyView extends TextView {

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.i("peter", "onMeasure");
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            Log.i("peter", "onLayout");
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.i("peter", "onDraw");
        }
    }

}
