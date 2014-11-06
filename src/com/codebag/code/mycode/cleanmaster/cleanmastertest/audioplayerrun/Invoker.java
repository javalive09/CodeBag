package com.codebag.code.mycode.cleanmaster.cleanmastertest.audioplayerrun;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends MyCode {

	private boolean running = false;

	public Invoker(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void getAllPlayerList() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"1"); 
		intent.setDataAndType(uri, "audio/*");
		
		PackageManager packageManager = getActivity().getPackageManager();
		List<ResolveInfo> playerList = packageManager.queryIntentActivities(intent, 0);
		for (ResolveInfo info : playerList) {
			String name = info.activityInfo.packageName;
			Log.addLog("peter", this, name);
		}
	}
	
	/**
	 * 非系统播放器，不起作用
	 */
	@Deprecated
	@Entry
	public void getCurrentPlayerInfo() {
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("com.android.music.metachanged");

        intentFilter.addAction("com.android.music.queuechanged");

        intentFilter.addAction("com.android.music.playbackcomplete");

        intentFilter.addAction("com.android.music.playstatechanged");
		 
        getActivity().registerReceiver(new BroadcastReceiver() {
			 
			@Override
			public void onReceive(Context context, Intent intent) {
			 
			String action = intent.getAction();
			 
			String cmd = intent.getStringExtra("command");
			 
			String artist = intent.getStringExtra("artist");
			 
			String album = intent.getStringExtra("album");
			 
			String track = intent.getStringExtra("track");
			
			Log.addLog("peter", "peter 123456 ", intent.toString());
			 
			}}, intentFilter);

	}
	
	
	/**
	 * 设置多个type 用; 隔开 如“audio/*;video/*;image/*”
	 */
	@Entry
	public void showPlayerList() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1"); 
		intent.setDataAndType(uri, "audio/*");
		
		getActivity().startActivity(intent);
	}
	
	@Entry
	public void startAudio() {
		getActivity().startService(new Intent(getActivity(), MainService.class));
	}

	@Entry
	public void stopAudio() {
		getActivity().stopService(new Intent(getActivity(), MainService.class));
	}

	@Entry
	public void isMusicActive() {
		AudioManager mAm = (AudioManager) getActivity().getSystemService(
				Context.AUDIO_SERVICE);
		boolean isActive = mAm.isMusicActive();
		Log.addLog("peter", this, "isActive = " + isActive);
	}

	@Entry
	public void runCheckMusicActiveThread() {
		running = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (running) {

					AudioManager mAm = (AudioManager) getActivity()
							.getSystemService(Context.AUDIO_SERVICE);
					boolean isActive = mAm.isMusicActive();
					Log.addLog("peter", this, "isActive = " + isActive);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	@Entry
	public void closeCheckMusicActiveThread() {
		running = false;
	}


}
