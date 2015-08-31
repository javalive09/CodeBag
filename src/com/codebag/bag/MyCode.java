package com.codebag.bag;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

public class MyCode{//
	
	public FrameLayout.LayoutParams wrap_content;
	public FrameLayout.LayoutParams match_parent;
	MainActivity mActivity;
	
	public MyCode(MainActivity act) {
		mActivity = act;
		wrap_content = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		match_parent = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
	}

	public MainActivity getActivity() {
		return mActivity;
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
