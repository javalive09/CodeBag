package com.javalive09.sample.view.drawable.insetdrawable;

import android.view.View;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * 
 * 适用于给drawable 加边距 类似于加padding
 * 
 *http://developer.android.com/guide/topics/resources/drawable-resource.html#Inset
 *
 *
 */
public class Invoke extends Entry {

	public void show() {
		View v = View.inflate(getViewActivity(), R.layout.drawable_inset_drawable, null);
		showView(v);
	}

}
