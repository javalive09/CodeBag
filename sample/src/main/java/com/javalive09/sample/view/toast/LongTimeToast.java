package com.javalive09.sample.view.toast;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * 能自定义显示时间的toast 大于2秒钟
 */
public class LongTimeToast {

	private Handler mHandler;
	private Toast mToast;
	private int mDuration;
	
	public LongTimeToast(Toast toast, int duration) {
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
