package com.codebag.bag;

import com.codebag.bag.main.InovkedViewActivity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

public class MyCode{//
	public FrameLayout.LayoutParams wrap_content;
	public FrameLayout.LayoutParams match_parent;
	InovkedViewActivity mActivity;
	
	public MyCode(InovkedViewActivity act) {
		mActivity = act;
	}

	public InovkedViewActivity getActivity() {
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
