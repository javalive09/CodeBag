package com.codebag.code.mycode.view.drawable.insetdrawable;

import android.view.View;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 
 * 适用于给drawable 加边距 类似于加padding
 * 
 *http://developer.android.com/guide/topics/resources/drawable-resource.html#Inset
 *
 *
 */
public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		View v = View.inflate(getActivity(), R.layout.drawable_inset_drawable, null);
		showView(v);
	}

}
