package com.codebag.code.mycode.interview.noxus;

import com.codebag.bag.Log;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class FatherView extends LinearLayout {

	public FatherView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FatherView(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean r = super.dispatchTouchEvent(ev);
		Log.addLog(this, "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean r = super.onInterceptTouchEvent(ev);
		Log.addLog(this, "onInterceptTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		Log.addLog(this, "onTouchEvent＝" + r);
		return false;
	}

}
