
package com.codebag.code.mycode.test;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class DeadLock extends MyCode {

	public DeadLock(MainActivity context) {
		super(context);
	}

	@Entry()
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
				Log.addLog("peter", this, "methodA");
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
				Log.addLog("peter", this, "methodB");
			}
		}
	}
	
	
}

