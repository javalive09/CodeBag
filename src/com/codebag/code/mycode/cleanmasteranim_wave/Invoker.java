package com.codebag.code.mycode.cleanmasteranim_wave;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.DisplayUtil;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showViewCakeProgressBar() {
		int mPly = DisplayUtil.dip2px(getContext(), 4);
		CakeProgressBar c = new CakeProgressBar(getContext());
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(c);
	}
	
	@Entry
	public void showViewCakeProgressBar_xml() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getContext(), 4);
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(bar);
	}
	
	@Entry
	public void animCakeProgressBar() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getContext(), 4);
		c.setData(500, mPly, 3);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(bar);
	}
	
	@Entry
	public void animCakeProgressBar_xml() {
		int mPly = DisplayUtil.dip2px(getContext(), 4);
		CakeProgressBar c = new CakeProgressBar(getContext());
		c.setData(500, mPly, 3);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(c);
	}
	
	
	@Entry
	public void animCakeWaveView() {
		CakeWaveView v = new CakeWaveView(getContext());
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setColor(waveColor, cirCleColor);
		v.setData(500, 11);
		v.startAnimination(30);
		showView(v);
	}
	
	@Entry
	public void animCakeWaveView_xml() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View cake = inflater.inflate(R.layout.cakeview, null);
		CakeWaveView v = (CakeWaveView) cake.findViewById(R.id.cakeview);
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.startAnimination(30);
		showView(cake);
	}
	
	@Entry
	public void showCakeWaveView() {
		CakeWaveView v = new CakeWaveView(getContext());
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.setProgress(30);
		showView(v);
	}
	
	@Entry
	public void showCakeWaveView_xml() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View cake = inflater.inflate(R.layout.cakeview, null);
		CakeWaveView v = (CakeWaveView) cake.findViewById(R.id.cakeview);
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		int waveColor = Color.parseColor("#33ffffff");
		int cirCleColor = Color.parseColor("#24a0ff");
		v.setData(500, 11);
		v.setColor(waveColor, cirCleColor);
		v.setProgress(30);
		showView(cake);
	}
	
	@Entry
	public void showSampleClip() {
		SampleView v = new SampleView(getContext());
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		showView(v);
	}
	
	@Entry
	public void animWaveView() {
		WaveView v = new WaveView(getContext());
		v.startAnim(50);
		showView(v);
	}
	
	
	@Entry
	public void showWaveView() {
		WaveView v = new WaveView(getContext());
		v.setProgress(50);
		showView(v);
	}
	
}
