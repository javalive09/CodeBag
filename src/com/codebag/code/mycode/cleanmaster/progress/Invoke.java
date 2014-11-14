package com.codebag.code.mycode.cleanmaster.progress;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
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
	}
	
}
