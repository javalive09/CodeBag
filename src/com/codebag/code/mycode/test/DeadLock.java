
package com.codebag.code.mycode.test;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class DeadLock extends CaseListView {

	public DeadLock(Context context) {
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
				Log.addLog(this, "methodA");
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
				Log.addLog(this, "methodB");
			}
		}
	}
	
	
}

