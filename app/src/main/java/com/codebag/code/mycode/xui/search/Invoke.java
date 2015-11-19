package com.codebag.code.mycode.xui.search;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void showAnim() {
		
		showView(R.layout.searchlayout);
		
		getActivity().getWindow().getDecorView().post(new Runnable() {
			
			@Override
			public void run() {
				AnimatorSet set = new AnimatorSet();
				final View root = getActivity().findViewById(R.id.root);
				ObjectAnimator rootAnim = ObjectAnimator.ofFloat(root, "rotation", 0, 360);
				rootAnim.setDuration(2000);
				rootAnim.setRepeatCount(ObjectAnimator.INFINITE);
				rootAnim.setInterpolator(new LinearInterpolator());
				
				View search = getActivity().findViewById(R.id.search);
				ObjectAnimator searchAnim = ObjectAnimator.ofFloat(search, "rotation", 360, 0);
				searchAnim.setRepeatCount(ObjectAnimator.INFINITE);
				searchAnim.setInterpolator(new LinearInterpolator());
				searchAnim.setDuration(2000);
				set.playTogether(rootAnim, searchAnim);
				set.start();
			}
		});
	}
	
	@Entry
	public void showAnim2() {
		showView(R.layout.searchlayout);
		final View root = getActivity().findViewById(R.id.root);
		final View search = root.findViewById(R.id.search);
		search.post(new Runnable() {

			@Override
			public void run() {
				final int width = search.getWidth();
		        final int rootH = root.getWidth();
		        final int R = (rootH - width) / 2;
				ValueAnimator tValue = ValueAnimator.ofFloat(0, (float) (2.0f * Math.PI));
				tValue.setDuration(2000);
				tValue.setRepeatCount(ValueAnimator.INFINITE);
				tValue.setInterpolator(new LinearInterpolator());
				tValue.addUpdateListener(new AnimatorUpdateListener() {

		            @Override
		            public void onAnimationUpdate(ValueAnimator animation) {
		                // 圆的参数方程 x = R*sin(t) y = R*cos(t)
		                float t = (Float) animation.getAnimatedValue();
		                int x = (int) (R * Math.sin(t)) + R;
		                int y = (int) (R * Math.cos(t)) + R;
		                
		                int cell = width;
		                int left = x;
		                int top = y;
		                search.layout(left, top, left + cell, top + cell);
		            }
		        });
				tValue.start();
			}
			
		});

	}
	
	@Entry
	public void sinPi() {
		TextView tv = new TextView(getActivity());
		tv.setText("sin (0) =" + (int)Math.sin(0) + "\n"
				+ "sin (pi/2) =" + (int)Math.sin(Math.PI / 2) + "\n"
				+ "sin (pi) =" + (int)Math.sin(Math.PI) + "\n"
				+ "sin (pi/2*3) =" + (int)Math.sin(Math.PI / 2 * 3) + "\n"
				+ "sin (2pi) =" + (int)Math.sin(Math.PI * 2) + "\n"
				);
		tv.setTextColor(Color.BLACK);
		showView(tv);
	}
	
}
