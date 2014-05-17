package com.codebag.code.mycode.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.TextView;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Log;
import com.codebag.code.mlib.myService;

public class TestServiceLife extends CaseListView {

	private Service mService = null;
	
	public TestServiceLife(Context context) {
		super(context);
	}

	public void run_startservice() {
		Intent intent = new Intent(getContext(),myService.class);
		getContext().startService(intent);
	}
	public void run_stopservice() {
		Intent intent = new Intent(getContext(),myService.class);
		getContext().stopService(intent);
	}
	
	public void run_bindservice() {
		Intent intent = new Intent(getContext(),myService.class);
		getContext().bindService(intent, mServicecon, Service.BIND_AUTO_CREATE);
	}
	
	private ServiceConnection mServicecon = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				
			}
	};
	
	public void run_unbindservice() {
		getContext().unbindService(mServicecon);
	}
	
	public void run_showLogView() {
		TextView log = new TextView(getContext());
		log.setText(Log.getLog());
		showView(log);
	}
	
	public void run_clearLog() {
		Log.clearLog();
	}
}
