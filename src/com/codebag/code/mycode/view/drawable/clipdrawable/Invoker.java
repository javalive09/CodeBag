package com.codebag.code.mycode.view.drawable.clipdrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.view.Gravity;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

/**
 * 横向展开动画效果
 */
public class Invoker extends CaseListView {

	int level = 0;
	ClipDrawable cd = null;
	
	public Invoker(Context context) {
		super(context);
		Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		BitmapDrawable d = new BitmapDrawable(getResources(), b);
		cd = new ClipDrawable(d, Gravity.CENTER, ClipDrawable.HORIZONTAL);
	}

	@Entry
	public void showClipDrawable() {

		ImageView iv = new ImageView(getContext());
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv);
		showClip();
	}
	
	private void showClip() {
		postDelayed(new Runnable() {
			@Override
			public void run() {
				if(level < 10000) {
					level += 200;
					cd.setLevel(level);
					showClip();
				}else {
					cd.setLevel(10000);
				}
			}
			
		}, 10);
	}
}
