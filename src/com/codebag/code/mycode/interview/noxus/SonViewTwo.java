package com.codebag.code.mycode.interview.noxus;

import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

public class SonViewTwo extends Button {

	public SonViewTwo(Context context) {
		super(context);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean r = super.dispatchTouchEvent(ev);
		Log.addLog(this, "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		Log.addLog(this, "default onTouchEvent=" + r);
		
		return true;
	}

}
