package com.javalive09.sample.project.cleanmaster.cleanmasteranim_wave;

import android.graphics.Color;

import com.javalive09.codebag.Entry;

public class Invoker2 extends Entry {

	public void showCardWaveBar() {
		CardWaveBar bar = new CardWaveBar(getViewActivity());
		bar.setData(0, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.setProgress(80);
		showView(bar);
	}
	
	public void animCardWaveBar() {
		CardWaveBar bar = new CardWaveBar(getViewActivity());
		bar.setData(0, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.startAnimination(80);
		showView(bar);
	}
	

}
