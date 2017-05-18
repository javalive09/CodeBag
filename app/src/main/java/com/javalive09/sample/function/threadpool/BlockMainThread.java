package com.javalive09.sample.function.threadpool;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

public class BlockMainThread extends Entry {

	volatile int count = 0;
	volatile boolean finish = false;
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
