package com.codebag.code.mycode.view.textview.settextview_measure;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends MyCode {

	TextView view;
	
	int measureCount;
	
	int requestCount;
	
	public Invoker(InovkedViewActivity context) {
		super(context);
		view = new TextView(context) {

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
				Log.addLog("peter", this, "measureCount = " + measureCount++);
			}
			
			public void requestLayout() {
				Log.addLog("peter", this, "requestCount = " + requestCount++);
			}
			
		};
		view.setText("invoker = ");
		view.setTextSize(30);
		view.setBackgroundColor(Color.BLACK);
	}
	
	@Entry
	public void setText() {
		showView(view);
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
