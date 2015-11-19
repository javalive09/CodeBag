package com.codebag.code.mycode.test.threadpool;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class BlockMainThread extends MyCode {

	public BlockMainThread(InovkedViewActivity act) {
		super(act);
	}
	
	volatile int count = 0;
	volatile boolean finish = false;
	@Entry
	public void invoke() {
		finish = false;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(count != 20) {
					count++;
					Log.i("peter", "count=" + count);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				if(count == 20) {
					finish = true;
					Log.i("peter", "notify");
				}
			}
		}).start();
		
		while(!finish) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Log.i("peter", "finish====");
	}

}
