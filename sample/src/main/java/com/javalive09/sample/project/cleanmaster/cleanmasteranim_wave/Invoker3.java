package com.javalive09.sample.project.cleanmaster.cleanmasteranim_wave;

import android.graphics.Color;

import com.javalive09.codebag.Entry;

public class Invoker3 extends Entry {

	public void showCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getViewActivity());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.setProgress(30);
		showView(v);
	}
	
	public void animCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getViewActivity());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 20);
		v.setColor(waveColor, cirCleColor);
		v.startAnimination(30);
		showView(v);
	}
}
