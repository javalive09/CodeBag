package com.javalive09.sample.view.drawable.drawablecontainer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * android 帧动画，也可以用animation-list 配置xml来做
 * 旋转圆形loading 图标
 */
public class AnimationDrawable_ extends Entry implements OnClickListener{
	
	AnimationDrawable ad; 
	
	public void show() {
		ad = new AnimationDrawable();
		Bitmap bitmap0 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi0);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi2);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi3);
		Bitmap bitmap4 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi4);
		Bitmap bitmap5 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi5);
		
		ad.addFrame(new BitmapDrawable(bitmap0), 200);
		ad.addFrame(new BitmapDrawable(bitmap1), 200);
		ad.addFrame(new BitmapDrawable(bitmap2), 200);
		ad.addFrame(new BitmapDrawable(bitmap3), 200);
		ad.addFrame(new BitmapDrawable(bitmap4), 200);
		ad.addFrame(new BitmapDrawable(bitmap5), 200);
		
		anim();
	}

	public void show_xml() {
		ad = (AnimationDrawable) getActivity().getResources().getDrawable(R.drawable.animation_drawable);
		anim();
	}
	
	private void anim() {
		ad.setOneShot(false);
		
		FrameLayout fl = new FrameLayout(getActivity());
		ImageView iv = new ImageView(getActivity());
		iv.setBackgroundDrawable(ad);
		
		  FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
		
		fl.addView(iv, params);
		
		Button start = new Button(getActivity());
		start.setText("start");
		start.setId(0);
		start.setOnClickListener(this);
		
		Button stop = new Button(getActivity());
		stop.setText("stop");
		stop.setId(1);
		stop.setOnClickListener(this);
		
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.BOTTOM|Gravity.LEFT;
		
		fl.addView(start, params2);
		
        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params3.gravity = Gravity.BOTTOM|Gravity.RIGHT;
		fl.addView(stop, params3);
		fl.setBackgroundColor(Color.WHITE);
		showView(fl);
	}
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case 0:
			if(ad != null && !ad.isRunning()) {
				ad.start();
			}
			break;
		case 1:	
			if(ad != null && ad.isRunning()) {
				ad.stop();
			}
			break;
		}
	}
	
	
}
