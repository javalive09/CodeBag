package com.codebag.code.mycode.view.audioplayerrun;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

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
}
