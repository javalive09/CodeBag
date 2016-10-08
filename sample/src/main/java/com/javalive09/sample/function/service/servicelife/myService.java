package com.javalive09.sample.function.service.servicelife;

import java.io.FileDescriptor;

import com.javalive09.codebag.LogUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class myService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		LogUtil.i(  "onBind()----");
		return new IBinder(){

			@Override
			public String getInterfaceDescriptor() throws RemoteException {
				return null;
			}

			@Override
			public boolean pingBinder() {
				return false;
			}

			@Override
			public boolean isBinderAlive() {
				return false;
			}

			@Override
			public IInterface queryLocalInterface(String descriptor) {
				return null;
			}

			@Override
			public void dump(FileDescriptor fd, String[] args)
					throws RemoteException {
				
			}

			@Override
			public boolean transact(int code, Parcel data, Parcel reply,
					int flags) throws RemoteException {
				return false;
			}

			@Override
			public void linkToDeath(DeathRecipient recipient, int flags)
					throws RemoteException {
				
			}

			@Override
			public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
				return false;
			}

			@Override
			public void dumpAsync(FileDescriptor fd, String[] args)
					throws RemoteException {
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
