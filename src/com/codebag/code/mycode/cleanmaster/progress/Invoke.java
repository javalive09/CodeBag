package com.codebag.code.mycode.cleanmaster.progress;

import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void show() {
		CircleView v = new CircleView(getActivity());
		int color = Color.parseColor("#1e83d1");
		v.setBackgroundColor(color);
		showView(v);
		v.setDuration(0);
		v.startAnim();
		
	}
	
	@Entry
	public void showAll() {
		ProgressView v = new ProgressView(getActivity());
		int color = Color.parseColor("#1e83d1");
		v.setBackgroundColor(color);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;
		showView(v, params);
		
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
