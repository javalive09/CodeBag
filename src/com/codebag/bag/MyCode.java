package com.codebag.bag;

import android.view.View;
import android.widget.FrameLayout;

public class MyCode{

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
	
}
