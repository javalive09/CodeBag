package com.codebag.code.mycode.view.audioplayerrun;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

public class Invoker extends CaseListView {

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
	
}
