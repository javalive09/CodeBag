
package com.javalive09.sample.function.deadlock;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

/**
 * 死锁demo
 */
public class DeadLock extends Entry {

	public void action() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				methodA();
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				methodB();
			}
			
		}).start();
		
	}
	
	Object lockOne = new Object();
	Object lockTwo = new Object();
	
	
	private void methodA() {
		synchronized (lockOne) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockTwo) {
				Log.i("methodA");
			}
		}
	}
	
	private void methodB() {
		synchronized(lockTwo) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockOne) {
				Log.i( "methodB");
			}
		}
	}
	
	
}

