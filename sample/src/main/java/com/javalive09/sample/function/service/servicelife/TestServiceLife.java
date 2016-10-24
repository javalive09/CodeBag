package com.javalive09.sample.function.service.servicelife;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.ShowViewActivity;

/**
 * service生命周期
 * 
 * @author peter
 *
 */
public class TestServiceLife extends Entry {

	
	public void startservice() {
		Intent intent = new Intent(getApplicationContext(),myService.class);
		getApplicationContext().startService(intent);
	}
	
	public void stopservice() {
		Intent intent = new Intent(getApplicationContext(),myService.class);
		getApplicationContext().stopService(intent);
	}
	
	public void bindservice() {
		Intent intent = new Intent(getApplicationContext(),myService.class);
		getApplicationContext().bindService(intent, mServicecon, Service.BIND_AUTO_CREATE);
	}

	private int a = 1;

	private boolean binded = false;
	private Boolean b = Boolean.TRUE;

	private ServiceConnection mServicecon = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				binded = false;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				binded = true;
				b = Boolean.TRUE;
			}
	};
	
	public void unbindservice() {
		if(b.booleanValue()) {
			getApplicationContext().unbindService(mServicecon);
		}
	}

	public void test() {
		LogUtil.e(a + "");
		LogUtil.e(mServicecon.toString());
	}

}
