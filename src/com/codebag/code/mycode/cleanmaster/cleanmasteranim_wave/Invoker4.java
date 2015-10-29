package com.codebag.code.mycode.cleanmaster.cleanmasteranim_wave;

import android.graphics.Color;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker4 extends MyCode {

	public Invoker4(InovkedViewActivity context) {
		super(context);
	}

	@Entry
	public void showCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getActivity());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.setProgress(30);
		showView(v);
	}
	
	@Entry
	public void animCakeWaveView() {
		CakeWaveView2 v = new CakeWaveView2(getActivity());
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 20);
		v.setColor(waveColor, cirCleColor);
		v.startAnimination(30);
		showView(v);
	}
}
