package com.javalive09.sample.function.service.servicelife;

import com.javalive09.codebag.LogUtil;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class myService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		LogUtil.i(  "onBind()----");
		return new Binder(){

			public Service getService() {
				return myService.this;
			}

		};
	}

	@Override
	public void onCreate() {
		LogUtil.i(    "onCreate()----");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.i(    "onStartCommand()----");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		LogUtil.i(    "onDestroy()----");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		LogUtil.i(    "onUnbind()----");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		LogUtil.i(    "onRebind()----");
		super.onRebind(intent);
	}

	
}
