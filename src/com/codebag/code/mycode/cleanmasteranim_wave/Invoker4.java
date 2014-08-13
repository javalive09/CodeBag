package com.codebag.code.mycode.cleanmasteranim_wave;

import android.content.Context;
import android.graphics.Color;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker4 extends CaseListView {

	public Invoker4(Context context) {
		super(context);
	}

	@Entry
	public void showCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getContext());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.setProgress(30);
		showView(v);
	}
	
	@Entry
	public void animCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getContext());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 20);
		v.setColor(waveColor, cirCleColor);
		v.startAnimination(30);
		showView(v);
	}
}
