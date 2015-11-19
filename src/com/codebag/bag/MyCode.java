package com.codebag.bag;

import com.codebag.bag.main.InovkedViewActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MyCode{//
	public FrameLayout.LayoutParams wrap_content;
	public FrameLayout.LayoutParams match_parent;
	InovkedViewActivity mActivity;
	
	public MyCode(InovkedViewActivity act) {
		mActivity = act;
		wrap_content = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, 
				FrameLayout.LayoutParams.WRAP_CONTENT);
		match_parent = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, 
				FrameLayout.LayoutParams.MATCH_PARENT);
	}

	public InovkedViewActivity getActivity() {
		return mActivity;
	}
	
	public void showTxt(String text) {
		TextView tv = new TextView(mActivity);
		tv.setTextColor(Color.BLACK);
		tv.setText(text);
		showView(tv);
	}
	
	public void showView(View view) {
		StackTraceElement[] em = Thread.currentThread().getStackTrace();
		String methodName = em[3].getMethodName();
		mActivity.showMethodView(view, methodName, null);
	}
	
	public void showView(int resId) {
		StackTraceElement[] em = Thread.currentThread().getStackTrace();
		String methodName = em[3].getMethodName();
		mActivity.showMethodView(resId, methodName);
	}
	
	public void showView(View view, FrameLayout.LayoutParams params) {
		StackTraceElement[] em = Thread.currentThread().getStackTrace();
		String methodName = em[3].getMethodName();
		mActivity.showMethodView(view, methodName, params);
	}
	
	public void startActivity(Intent intent) {//启动activity时，重定向
		mActivity.startActivity(intent);
	}
	
	public View findViewById(int id) {
		return mActivity.findViewById(id);
	}
	
	
}
