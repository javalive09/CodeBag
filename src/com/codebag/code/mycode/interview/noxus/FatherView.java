package com.codebag.code.mycode.interview.noxus;

import com.codebag.code.mycode.utils.Log;

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
		Log.addLog("peter", this, "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean r = super.onInterceptTouchEvent(ev);
		Log.addLog("peter", this, "onInterceptTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		Log.addLog("peter", this, "onTouchEvent＝" + r);
		return false;
	}

}
