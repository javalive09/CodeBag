package com.javalive09.sample.study.interview.noxus;

import com.javalive09.codebag.LogUtil;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

public class SonViewOne extends Button {

	public SonViewOne(Context context) {
		super(context);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean r = super.dispatchTouchEvent(ev);
		LogUtil.i( "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		LogUtil.i("default onTouchEvent=" + r);
		
		return false;
	}

}
