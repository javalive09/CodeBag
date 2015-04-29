package com.codebag.code.mycode.view.drawable.drawablecontainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.View.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

/**
 * android中动态的修改背景drawable，用于自定义不同状态下的view的不同的背景。也可以用level-list配置xml来实现
 * 
 * 如浏览器中的刷新按钮背景。wifi信号强度图标。
 * 
 * 注意可以有范围控制minLevel, maxLevel如：wifi强度1-20用第一张drawable 20-40用第二张drawable
 */
public class LevelListDrawable_ extends MyCode implements OnClickListener{
	
	LevelListDrawable ad = new LevelListDrawable();
	
	public LevelListDrawable_(MainActivity context) {
		super(context);
		
		Bitmap bitmap0 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi0);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi2);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi3);
		Bitmap bitmap4 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi4);
		Bitmap bitmap5 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.wifi5);
		
		ad.addLevel(0, 0, new BitmapDrawable(bitmap0));
		ad.addLevel(1, 1, new BitmapDrawable(bitmap1));
		ad.addLevel(2, 2, new BitmapDrawable(bitmap2));
		ad.addLevel(3, 3, new BitmapDrawable(bitmap3));
		ad.addLevel(4, 4, new BitmapDrawable(bitmap4));
		ad.addLevel(5, 5, new BitmapDrawable(bitmap5));
		
	}
	
	@Entry
	public void showLevelListDrawable_xml() {
		View v = View.inflate(getActivity(), R.layout.drawable_level_list, null);
		v.setOnClickListener(this);
		ImageView iv = (ImageView) v.findViewById(R.id.image);
		ad = (LevelListDrawable) iv.getBackground();
		showView(v);
	}
	
	@Entry
	public void show() {
		FrameLayout fl = new FrameLayout(getActivity());
		ImageView iv = new ImageView(getActivity());
		iv.setBackgroundDrawable(ad);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
		fl.addView(iv, params);
		
		Button start = new Button(getActivity());
		start.setText("next");
		start.setId(0);
		start.setOnClickListener(this);
		
		FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params2.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
		fl.addView(start, params2);
		fl.setBackgroundColor(Color.WHITE);
		
		showView(fl, new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	@Override
	public void onClick(View v) {
		int level = ad.getLevel();
		level++;
		if (level < 6) {
			ad.setLevel(level);
		} else {
			ad.setLevel(0);
		}
	}
	
	
}
