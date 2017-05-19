package com.javalive09.sample.study.interview.noxus;

import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.widget.Button;

public class SonViewTwo extends AppCompatButton {

	public SonViewTwo(Context context) {
		super(context);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean r = super.dispatchTouchEvent(ev);
		Log.i( "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		Log.i( "default onTouchEvent=" + r);
		
		return true;
	}

}
