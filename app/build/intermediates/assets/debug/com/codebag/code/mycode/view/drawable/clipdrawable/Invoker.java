package com.codebag.code.mycode.view.drawable.clipdrawable;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
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
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 横向展开动画效果
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#Clip
 */
public class Invoker extends MyCode {

	int level = 0;
	ClipDrawable cd = null;
	
	ValueAnimator va;
	
	public Invoker(InovkedViewActivity context) {
		super(context);
	}

	@Entry
	public void showClipDrawable() {
		Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.head);
		BitmapDrawable d = new BitmapDrawable(getActivity().getResources(), b);
		cd = new ClipDrawable(d, Gravity.LEFT, ClipDrawable.HORIZONTAL);
		
		ImageView iv = new ImageView(getActivity());
		iv.post(new Runnable() {

			@Override
			public void run() {
//				clipHandler.sendEmptyMessage(0);
				va.start();
			}
			
		});
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv, match_parent);
		
		va = ValueAnimator.ofInt(0, 10000);
		va.setDuration(1000);
		va.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int level = (Integer) animation.getAnimatedValue();
				cd.setLevel(level);
			}
		});
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
		va = ValueAnimator.ofInt(0, 10000);
		va.setDuration(1000);
		va.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int level = (Integer) animation.getAnimatedValue();
				cd.setLevel(level);
			}
		});
		iv.post(new Runnable() {

			@Override
			public void run() {
//				clipHandler.sendEmptyMessage(0);
				va.start();
			}
			
		});
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv, match_parent);
	}
	
}