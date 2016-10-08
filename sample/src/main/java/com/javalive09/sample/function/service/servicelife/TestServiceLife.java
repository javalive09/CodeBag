package com.javalive09.sample.function.service.servicelife;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.javalive09.codebag.Entry;

/**
 * service生命周期
 * 
 * @author peter
 *
 */
public class TestServiceLife extends Entry {

	
	public void startservice() {
		Intent intent = new Intent(getActivity(),myService.class);
		getActivity().startService(intent);
	}
	
	public void stopservice() {
		Intent intent = new Intent(getActivity(),myService.class);
		getActivity().stopService(intent);
	}
	
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
	
	public void unbindservice() {
		getActivity().unbindService(mServicecon);
	}
	
}
