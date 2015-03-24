package com.codebag.bag;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

public class MyCode{//
	
	public static final int NEW_ACTIVITY = 0XFFFFFF;
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
		mActivity.showView(view);
	}
	
	public void showView(View view, FrameLayout.LayoutParams params) {
		mActivity.showView(view, params);
	}
	
	public void startActivity(Intent intent) {//启动activity时，重定向
		mActivity.startActivity(intent);
	}
	
	
}
