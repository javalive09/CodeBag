package com.codebag.code.mycode.function.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void multiAsyncTask() {
		for(int i=0; i< 2000; i++) {
			doAsyncTask();
		}
	}

	private void doAsyncTask() {
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					String name = Thread.currentThread().getName();
					Log.i("peter", "thread name =" + name);
					Thread.currentThread().sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		}.execute();
	}

}
