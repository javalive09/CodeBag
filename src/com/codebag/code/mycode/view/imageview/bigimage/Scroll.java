package com.codebag.code.mycode.view.imageview.bigimage;

import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Scroll extends MyCode {

	ImageView iv;
	FrameLayout fl;
	
	public Scroll(InovkedViewActivity act) {
		super(act);
		iv = new ImageView(act){

			@Override
			public boolean onTouchEvent(MotionEvent event) {
				fl.scrollTo(50, 0);
				return super.onTouchEvent(event);
			}
			
		};
		iv.setImageResource(R.drawable.ic_launcher);
	}

	@Entry
	public void showView() {
		fl = new FrameLayout(getActivity());
		fl.setBackgroundColor(Color.WHITE);
		LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		p.gravity = Gravity.CENTER;
		fl.addView(iv, p);
		
		showView(fl);
	}
	
	
}
