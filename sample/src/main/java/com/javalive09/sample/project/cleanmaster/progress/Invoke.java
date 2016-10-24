package com.javalive09.sample.project.cleanmaster.progress;

import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

	public void showCircleView() {
		final CircleView v = new CircleView(getViewActivity());
		int color = Color.parseColor("#1e83d1");
		v.setBackgroundColor(color);
		showView(v);
		v.setDuration(0);
		v.postDelayed(new Runnable() {
			@Override
			public void run() {
				v.startAnim();
			}
		}, 500);

	}
	
	public void showProgressView() {
		ProgressView v = new ProgressView(getViewActivity());
		int color = Color.parseColor("#1e83d1");
		v.setBackgroundColor(color);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;
		showView(v);
		
		v.startAnim();
		v.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup vg = (ViewGroup) v;
                vg.getChildAt(0).setVisibility(View.GONE);
                return true;
            }
        });
	}
	
}
