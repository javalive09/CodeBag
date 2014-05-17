package com.codebag.code.mlib;

import java.io.FileDescriptor;

import com.codebag.bag.Log;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class myService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		Log.addLog("onBind()----");
		return new IBinder(){

			@Override
			public String getInterfaceDescriptor() throws RemoteException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean pingBinder() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isBinderAlive() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public IInterface queryLocalInterface(String descriptor) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void dump(FileDescriptor fd, String[] args)
					throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dumpAsync(FileDescriptor fd, String[] args)
					throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean transact(int code, Parcel data, Parcel reply,
					int flags) throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void linkToDeath(DeathRecipient recipient, int flags)
					throws RemoteException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
	}

	@Override
	public void onCreate() {
		Log.addLog("onCreate()----");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.addLog("onStartCommand()----");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.addLog("onDestroy()----");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.addLog("onUnbind()----");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		Log.addLog("onRebind()----");
		super.onRebind(intent);
	}

	
}
