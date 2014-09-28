package com.codebag.bag;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

public class MyCode{
	
	public static final int NEW_ACTIVITY = 0XFFFFFF;

	MainActivity mActivity;
	
	public MyCode(MainActivity act) {
		mActivity = act;
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
		mActivity.finish();
		mActivity.startActivity(intent);
	}
	
}
