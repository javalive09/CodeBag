package com.codebag.code.mycode.view.drawable.clipdrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
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
		Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.head);
		BitmapDrawable d = new BitmapDrawable(getActivity().getResources(), b);
		cd = new ClipDrawable(d, Gravity.CENTER, ClipDrawable.HORIZONTAL);
	}

	@Entry
	public void showClipDrawable() {

		ImageView iv = new ImageView(getActivity()) {

            @Override
            protected void onAttachedToWindow() {
                super.onAttachedToWindow();
                showClip();
            }
            
            
		    
		};
		iv.setBackgroundDrawable(cd);
		level = 0;
		cd.setLevel(level);
		showView(iv);
	}
	
	
	
	private void showClip() {
		getActivity().runOnUiThread(new Runnable() {
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
			
		});
	}
}
