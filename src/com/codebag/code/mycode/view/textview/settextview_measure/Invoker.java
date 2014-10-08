package com.codebag.code.mycode.view.textview.settextview_measure;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends MyCode {

	TextView view;
	
	int measureCount;
	
	int requestCount;
	
	public Invoker(MainActivity context) {
		super(context);
		view = new TextView(context) {

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
				Log.addLog(this, "measureCount = " + measureCount++);
			}
			
			public void requestLayout() {
				Log.addLog(this, "requestCount = " + requestCount++);
			}
			
		};
		view.setText("invoker = ");
		view.setTextSize(30);
		view.setBackgroundColor(Color.WHITE);
	}
	
	@Entry
	public void setText() {
//		showView(view, wrapH_fillW_Params(Gravity.CENTER));
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
