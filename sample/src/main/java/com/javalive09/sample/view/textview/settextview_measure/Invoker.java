package com.javalive09.sample.view.textview.settextview_measure;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

public class Invoker extends Entry {

	TextView view;
	
	int measureCount;
	
	int requestCount;
	
	public Invoker() {
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
		showView(view);
	}
	
	public void setText() {
		mHandler.sendEmptyMessageDelayed(0, 100);
	}
	
	private Handler mHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			view.setText("invoke =" + msg.what++);
			mHandler.sendEmptyMessageDelayed(msg.what, 100);
		}
		
	};

}
