package com.codebag.code.mycode.view.toast;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class SuperToast3 {

	private Handler mHandler;
	private Toast mToast;
	private int mDuration;
	
	public SuperToast3(Toast toast, int duration) {
		mToast = toast;
		mDuration = duration;
		mHandler = new Handler(Looper.getMainLooper());   
	}
	
	public void show() {
		Timer timer = new Timer();
		final long firstTime = System.currentTimeMillis();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				long delta = System.currentTimeMillis() - firstTime;
				long end = mDuration - 2000;
				if(delta <= end) {
					mHandler.post(runnable);
				}
			}
			
		}, 0, 100);
	}
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			mToast.show();
		}
	};
	
}
