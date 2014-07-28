package com.codebag.code.mycode.cleanmastertest.audioplayerrun;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

public class Invoker extends CaseListView {

	private boolean running = false;
	
	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void startAudio() {
		getContext().startService(new Intent(getContext(), MainService.class));
	}

	@Entry
	public void stopAudio() {
		getContext().stopService(new Intent(getContext(), MainService.class));
	}
	
	@Entry
	public void isMusicActive() {
		AudioManager mAm = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
		boolean isActive = mAm.isMusicActive();
		Log.addLog(this, "isActive = " + isActive);
	}
	
	@Entry
	public void runCheckMusicActiveThread() {
		running = true;
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(running) {
					
					AudioManager mAm = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
					boolean isActive = mAm.isMusicActive();
					Log.showSystemLog(this, "isActive = " + isActive);
					
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
