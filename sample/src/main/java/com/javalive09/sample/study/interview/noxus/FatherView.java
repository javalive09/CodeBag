package com.javalive09.sample.study.interview.noxus;

import com.javalive09.codebag.LogUtil;

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
		LogUtil.i("dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean r = super.onInterceptTouchEvent(ev);
		LogUtil.i( "onInterceptTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		LogUtil.i( "onTouchEvent＝" + r);
		return false;
	}

}