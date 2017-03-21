package com.javalive09.sample.view.textview.settextview_measure;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.DetailActivity;

public class Invoker extends Entry {

	TextView view;
	
	int measureCount;
	
	int requestCount;
	
	public void setText() {
		view = new TextView(getActivity()) {

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
				LogUtil.i( "measureCount = " + measureCount++);
			}

			public void requestLayout() {
				super.requestLayout();
				LogUtil.i( "requestCount = " + requestCount++);
			}

		};
		view.setText("invoker = ");
		view.setTextSize(30);
		view.setTextColor(Color.BLACK);
		view.setBackgroundColor(Color.WHITE);
		final ValueAnimator mAnimator = ValueAnimator.ofInt(0, 10);
		mAnimator.setDuration(10 * 100);
		mAnimator.setRepeatCount(ValueAnimator.INFINITE);
		mAnimator.setInterpolator(new LinearInterpolator());
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int value = (int) animation.getAnimatedValue();
				view.setText("invoke =" + value);
			}
		});
		view.post(new Runnable() {
			@Override
			public void run() {
				mAnimator.start();
			}
		});

		showView(view, new DetailActivity.ActivityCallback() {
			@Override
			public void onDetachedFromWindow() {
				super.onDetachedFromWindow();
				mAnimator.cancel();
			}
		});
	}

}
