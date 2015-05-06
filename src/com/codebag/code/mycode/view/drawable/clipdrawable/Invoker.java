package com.codebag.code.mycode.view.drawable.clipdrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

/**
 * 横向展开动画效果
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#Clip
 */
public class Invoker extends MyCode {

	int level = 0;
	ClipDrawable cd = null;
	
	public Invoker(MainActivity context) {
		super(context);
	}

	@Entry
	public void showClipDrawable() {
		Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.head);
		BitmapDrawable d = new BitmapDrawable(getActivity().getResources(), b);
		cd = new ClipDrawable(d, Gravity.CENTER, ClipDrawable.HORIZONTAL);
		
		ImageView iv = new ImageView(getActivity());
		iv.post(new Runnable() {

			@Override
			public void run() {
				clipHandler.sendEmptyMessage(0);
			}
			
		});
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv, match_parent);
	}
	
	Handler clipHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what < 10000) {
				cd.setLevel(msg.what);
				sendEmptyMessage(msg.what + 200);
			}else {
				cd.setLevel(10000);
			}
		}
		
	};
	
	@Entry
	public void showClipDrawable_xml() {
		cd = (ClipDrawable) getActivity().getResources().getDrawable(R.drawable.clip_drawable);
		ImageView iv = new ImageView(getActivity());
		iv.post(new Runnable() {

			@Override
			public void run() {
				clipHandler.sendEmptyMessage(0);
			}
			
		});
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv, match_parent);
	}
	
}
