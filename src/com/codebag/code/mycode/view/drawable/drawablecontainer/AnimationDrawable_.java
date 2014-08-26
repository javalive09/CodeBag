package com.codebag.code.mycode.view.drawable.drawablecontainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

/**
 * android 帧动画，也可以用animation-list 配置xml来做
 * 旋转圆形loading 图标
 */
public class AnimationDrawable_ extends CaseListView implements OnClickListener{
	
	AnimationDrawable ad = new AnimationDrawable();
	
	public AnimationDrawable_(Context context) {
		super(context);
		
		Bitmap bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi0);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi2);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi3);
		Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi4);
		Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi5);
		
		ad.addFrame(new BitmapDrawable(bitmap0), 200);
		ad.addFrame(new BitmapDrawable(bitmap1), 200);
		ad.addFrame(new BitmapDrawable(bitmap2), 200);
		ad.addFrame(new BitmapDrawable(bitmap3), 200);
		ad.addFrame(new BitmapDrawable(bitmap4), 200);
		ad.addFrame(new BitmapDrawable(bitmap5), 200);
		
		ad.setOneShot(false);
		
	}
	
	@Entry
	public void show() {
		FrameLayout fl = new FrameLayout(getContext());
		ImageView iv = new ImageView(getContext());
		iv.setBackgroundDrawable(ad);
		
		fl.addView(iv, wrapContentParams(Gravity.CENTER | Gravity.CENTER_HORIZONTAL));
		
		Button start = new Button(getContext());
		start.setText("start");
		start.setId(0);
		start.setOnClickListener(this);
		
		Button stop = new Button(getContext());
		stop.setText("stop");
		stop.setId(1);
		stop.setOnClickListener(this);
		
		fl.addView(start, wrapContentParams(Gravity.BOTTOM|Gravity.LEFT));
		fl.addView(stop, wrapContentParams(Gravity.BOTTOM|Gravity.RIGHT));
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
