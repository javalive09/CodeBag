package com.codebag.code.mycode.view.drawable.drawablecontainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
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
 * android中动态的修改背景drawable，用于自定义不同状态下的view的不同的背景。也可以用level-list配置xml来实现
 */
public class LevelListDrawable_ extends CaseListView implements OnClickListener{
	
	LevelListDrawable ad = new LevelListDrawable();
	
	public LevelListDrawable_(Context context) {
		super(context);
		
		Bitmap bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi0);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi2);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi3);
		Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi4);
		Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.wifi5);
		
		ad.addLevel(0, 0, new BitmapDrawable(bitmap0));
		ad.addLevel(1, 1, new BitmapDrawable(bitmap1));
		ad.addLevel(2, 2, new BitmapDrawable(bitmap2));
		ad.addLevel(3, 3, new BitmapDrawable(bitmap3));
		ad.addLevel(4, 4, new BitmapDrawable(bitmap4));
		ad.addLevel(5, 5, new BitmapDrawable(bitmap5));
		
	}
	
	@Entry
	public void show() {
		FrameLayout fl = new FrameLayout(getContext());
		ImageView iv = new ImageView(getContext());
		iv.setBackgroundDrawable(ad);
		
		fl.addView(iv, wrapContentParams(Gravity.CENTER | Gravity.CENTER_HORIZONTAL));
		
		Button start = new Button(getContext());
		start.setText("next");
		start.setId(0);
		start.setOnClickListener(this);
		
		fl.addView(start, wrapContentParams(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL));
		fl.setBackgroundColor(Color.WHITE);
		showView(fl);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case 0:
			int level = ad.getLevel();
			level++;
			if(level < 6) {
				ad.setLevel(level);
			}else {
				ad.setLevel(0);
			}
			break;
		}
	}
	
	
}
