package com.codebag.code.mycode.interview.noxus;

import com.codebag.code.mycode.utils.Log;

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
		Log.addLog("peter", this, "dispatchTouchEvent=" + r);
		return r;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean r = super.onTouchEvent(event);
		Log.addLog("peter", this, "default onTouchEvent=" + r);
		
		return false;
	}

}
