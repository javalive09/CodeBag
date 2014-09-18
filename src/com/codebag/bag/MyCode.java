package com.codebag.bag;

import com.codebag.R;

import android.view.View;

public class MyCode{

	MainActivity mActivity;
	
	public MyCode(MainActivity act) {
		mActivity = act;
	}

	public MainActivity getActivity() {
		return mActivity;
	}
	
	public void showView(View view) {
		String name = Thread.currentThread().getStackTrace()[2].getMethodName();
		mActivity.setView(name, R.drawable.file, view);
	}
	
}
