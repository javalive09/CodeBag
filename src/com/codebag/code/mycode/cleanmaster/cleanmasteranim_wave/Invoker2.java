package com.codebag.code.mycode.cleanmaster.cleanmasteranim_wave;

import android.content.Context;
import android.graphics.Color;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker2 extends CaseListView {

	public Invoker2(Context context) {
		super(context);
	}
	
	@Entry
	public void showCardWaveBar() {
		CardWaveBar bar = new CardWaveBar(getContext());
		bar.setData(0, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.setProgress(80);
		showView(bar);
	}
	
	@Entry
	public void animCardWaveBar() {
		CardWaveBar bar = new CardWaveBar(getContext());
		bar.setData(0, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.startAnimination(80);
		showView(bar);
	}
	
	@Entry
	public void showCardCakeBar() {
		CardCakeBar bar = new CardCakeBar(getContext());
		bar.setData(20, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.setProgress(80);
		showView(bar);
	}
	@Entry
	public void animCardCakeBar() {
		CardCakeBar bar = new CardCakeBar(getContext());
		bar.setData(20, 500, 0);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		bar.setColor(waveColor, cirCleColor);
		bar.startAnimination(80);
		showView(bar);
	}
	

}
