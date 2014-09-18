package com.codebag.code.mycode.test.servicelife;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

/**
 * service生命周期
 * 
 * @author peter
 *
 */
public class TestServiceLife extends MyCode {

	
	public TestServiceLife(MainActivity context) {
		super(context);
	}

	@Entry()
	public void startservice() {
		Intent intent = new Intent(getActivity(),myService.class);
		getActivity().startService(intent);
	}
	
	@Entry()
	public void stopservice() {
		Intent intent = new Intent(getActivity(),myService.class);
		getActivity().stopService(intent);
	}
	
	@Entry()
	public void bindservice() {
		Intent intent = new Intent(getActivity(),myService.class);
		getActivity().bindService(intent, mServicecon, Service.BIND_AUTO_CREATE);
	}
	
	private ServiceConnection mServicecon = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				
			}
	};
	
	@Entry()
	public void unbindservice() {
		getActivity().unbindService(mServicecon);
	}
	
}
